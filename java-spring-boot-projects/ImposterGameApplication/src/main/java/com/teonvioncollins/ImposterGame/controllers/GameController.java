package com.teonvioncollins.ImposterGame.controllers;

import com.teonvioncollins.ImposterGame.models.GameModel;
import com.teonvioncollins.ImposterGame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import com.teonvioncollins.ImposterGame.services.CreateSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class GameController {

    @Autowired
    private CreateSession createSession;

    @PostMapping("/host-game")
    public String hostGame(Model model) {
        model.addAttribute("code", createSession.createSession());
        return "host";
    }

    @PostMapping("/host-join")
    public String hostJoin(@RequestParam int code, @RequestParam int maxPlayers, @RequestParam String name, Model model) {

        createSession.lockSession(code, maxPlayers);

        Player player = createSession.joinSession(code, name);

        model.addAttribute("code", code);
        model.addAttribute("playerId", player.getPlayerId());

        return "waiting";
    }

    @PostMapping("/join-game")
    public String joinGame(@RequestParam int code, @RequestParam String name, Model model) {

        Player player = createSession.joinSession(code, name);

        if (player == null) {
            model.addAttribute("error", "Invalid Game Code. Try Again");
            return "join";
        }

        model.addAttribute("code", code);
        model.addAttribute("playerId", player.getPlayerId());
        return "waiting";

    }

    @PostMapping("/status")
    @ResponseBody
    public Map<String, Object> status(@RequestParam int code) {

        GameModel session = createSession.getSession(code);

        if (session == null) {
            return Map.of("status", "ERROR");
        }

        int joined = session.getPlayers().size();
        int max = session.getMaxPlayers();

        if (joined < max) {
            return Map.of(
                    "status", "WAIT",
                    "players", session.getPlayers().stream()
                            .map(Player::getName)
                            .toList(),
                    "joined", joined,
                    "max", max
            );
        }

        long delayMs = 3000;
        long now = System.currentTimeMillis();

        if (now - session.getReadyAt() < delayMs) {
            return Map.of(
                    "status", "WAIT",
                    "players", session.getPlayers().stream()
                            .map(Player::getName)
                            .toList(),
                    "joined", joined,
                    "max", max,
                    "full", true
            );
        }

        return Map.of("status", "START");
    }

    @PostMapping("/generator")
    public String generateGame(@RequestParam int code, @RequestParam String playerId, Model model) {

        GameModel session = createSession.getSession(code);

        if (session == null) {
            return "join";
        }

        Player player = session.getPlayers().stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(null);

        if (player == null) {
            return "join";
        }

        model.addAttribute("name", player.getName());
        model.addAttribute("role", player.getRole());
        model.addAttribute("category", session.getCategory());

        if (!player.getRole().startsWith("IMPOSTER")) {
            model.addAttribute("question", session.getQuestion());
        } else {
            model.addAttribute("question", "");
        }
        return "generator";
    }

}

