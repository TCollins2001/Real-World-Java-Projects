package com.teonvioncollins.ImposterGameApplication.controllers;

import com.teonvioncollins.ImposterGameApplication.models.GameSession;
import com.teonvioncollins.ImposterGameApplication.models.Player;
import com.teonvioncollins.ImposterGameApplication.services.CreateSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    @Autowired
    private CreateSessionService createSessionService;

    @PostMapping("/host-game")
    public String hostGame(Model model) {
        GameSession session = createSessionService.createSession();
        System.out.println("CREATED SESSION CODE = " + session.getCode());
        model.addAttribute("code", session.getCode());

        return "host";
    }

    @PostMapping("/host-join")
    public String hostJoin(@RequestParam int code,
                           @RequestParam String name,
                           @RequestParam int maxPlayers,
                           Model model) {

        createSessionService.lockSession(code, maxPlayers);

        Player player = createSessionService.joinSession(code, name);

        model.addAttribute("code", code);
        model.addAttribute("playerId", player.getPlayerId());

        return "waiting";
    }


    @PostMapping("/generator")
    public String generator(@RequestParam int code,
                            @RequestParam String playerId,
                            Model model) {

        GameSession session = createSessionService.getSession(code);
        if (session == null) return "join";

        Player player = session.getPlayers().stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(null);

        if (player == null) return "join";

        model.addAttribute("code", code);
        model.addAttribute("role", player.getRole());
        model.addAttribute("category", session.getCategory());

        if (player.getRole().startsWith("IMPOSTER")) {
            model.addAttribute("question", "");
        } else {
            model.addAttribute("question", session.getQuestion());
        }

        return "generator";
    }


    @PostMapping("/join-game")
    public String joinGame(@RequestParam int code,
                           @RequestParam String name,
                           Model model) {

        Player player = createSessionService.joinSession(code, name);

        if (player == null) {
            model.addAttribute("error", "Invalid or full Game Code.");
            return "join";
        }

        model.addAttribute("code", code);
        model.addAttribute("playerId", player.getPlayerId());

        return "waiting";
    }

    @ResponseBody
    @PostMapping("/status")
    public String status(@RequestParam int code) {

        GameSession session = createSessionService.getSession(code);

        if (session == null)
            return "ERROR";

        if (session.getPlayers().size() < session.getMaxPlayers())
            return "WAIT";

        if (!session.isAssignedImposter()) {
            createSessionService.assignSingleImposter(session);
            session.setAssignedImposter(true);
        }

        return "START";
    }


}

