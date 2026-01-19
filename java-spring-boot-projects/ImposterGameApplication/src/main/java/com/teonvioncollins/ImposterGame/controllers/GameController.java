package com.teonvioncollins.ImposterGame.controllers;

import com.teonvioncollins.ImposterGame.models.GameModel;
import com.teonvioncollins.ImposterGame.models.GamePhase;
import com.teonvioncollins.ImposterGame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import com.teonvioncollins.ImposterGame.services.CreateSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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

    @GetMapping("/host-new")
    public String hostNew(Model model) {
        model.addAttribute("code", createSession.createSession());
        return "host";
    }

    @PostMapping("/host-join")
    public String hostJoin(@RequestParam int code, @RequestParam int maxPlayers, @RequestParam String name,
                           @RequestParam("rounds") int rounds, Model model) {

        createSession.lockSession(code, maxPlayers, rounds);

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

    @PostMapping("/start-vote")
    public String startVote(@RequestParam int code, @RequestParam String playerId) {
        GameModel session = createSession.getSession(code);

        if (session == null) {
            return "redirect:/";
        }

        Player player = session.getPlayers().stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(null);

        if (player == null || !player.isHost()) {
            return "redirect:/generator?code=" + code + "&playerId=" + playerId;
        }

        session.setPhase(GamePhase.VOTING);
        return "redirect:/generator?code=" + code + "&playerId=" + playerId;

    }

    @GetMapping("/generator")
    public String generateGame(@RequestParam int code, @RequestParam String playerId, @RequestParam(required = false) String ready, Model model) {

        boolean readyFlag = "true".equals(ready);
        model.addAttribute("forceWaiting", readyFlag);

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

        boolean hasVoted = session.getVotes().containsKey(playerId)
                || session.getPhase() == GamePhase.REVEAL_READY
                || session.getPhase() == GamePhase.REVEAL;

        model.addAttribute("hasVoted", hasVoted);

        model.addAttribute("mvps", List.of());


        if (session.getPhase() == GamePhase.REVEAL) {

            Player imposter = session.getImposter();
            Map<String, String> votes = session.getVotes();
            List<Player> players = session.getPlayers();

            List<Player> mvps = players.stream()
                    .filter(p -> votes.containsKey(p.getPlayerId()))
                    .filter(p -> votes.get(p.getPlayerId()).equals(imposter.getPlayerId()))
                    .toList();

            model.addAttribute("mvps", mvps);

            int correct = session.correctVotes();
            int total = session.totalVotes();

            boolean imposterLoses = correct > total / 2;

            model.addAttribute("imposterName", imposter.getName());
            model.addAttribute("votes", session.getVotes());
            model.addAttribute("correctVotes", correct);
            model.addAttribute("totalVotes", total);
            model.addAttribute("imposterLoses", imposterLoses);
            model.addAttribute("imposterId", imposter.getPlayerId());
        }

        model.addAttribute("code", code);
        model.addAttribute("name", player.getName());
        model.addAttribute("role", player.getRole());
        model.addAttribute("category", session.getCategory());
        model.addAttribute("isHost", player.isHost());
        model.addAttribute("phaseName", session.getPhase().name());
        model.addAttribute("players", session.getPlayers());
        model.addAttribute("playerId", playerId);

        if (!player.getRole().startsWith("IMPOSTER")) {
            model.addAttribute("question", session.getQuestion());
        } else {
            model.addAttribute("question", "");
        }

        model.addAttribute("votes", session.getVotes());
        model.addAttribute("players", session.getPlayers());

        model.addAttribute("currentRound", session.getCurrentRound());
        model.addAttribute("totalRounds", session.getTotalRounds());

        if (session.getCurrentRound() == session.getTotalRounds()) {
            int highest = session.getPlayers().stream()
                    .mapToInt(Player::getTotalScore)
                    .max()
                    .orElse(0);

            var winners = session.getPlayers().stream()
                    .filter(p -> p.getTotalScore() == highest)
                    .map(Player::getName)
                    .toList();

            model.addAttribute("winners", winners);
        }

        return "generator";
    }

    @PostMapping("/phase")
    @ResponseBody
    public Map<String, String> getPhase(@RequestParam int code) {
        GameModel session = createSession.getSession(code);
        if (session == null) {
            return Map.of("phase", "ERROR");
        }
        return Map.of("phase", session.getPhase().name());
    }

    @PostMapping("/submit-vote")
    public String submitVote(@RequestParam int code, @RequestParam String playerId, @RequestParam String voteFor) {

        GameModel session = createSession.getSession(code);

        if (session == null) {
            return "redirect:/";
        }

        if (session.getPhase() != GamePhase.VOTING) {
            return "redirect:/generator?code=" + code + "&playerId=" + playerId;
        }

        if (session.getVotes().containsKey(playerId)) {
            return "redirect:/generator?code=" + code + "&playerId=" + playerId;
        }

        session.getVotes().put(playerId, voteFor);

        long totalPlayers = session.getPlayers().size();
        long totalVotes = session.getVotes().size();

        if (totalVotes == totalPlayers) {
            session.setPhase(GamePhase.REVEAL_READY);
        }

        if (session.getPhase() == GamePhase.REVEAL_READY) {
            return "redirect:/generator?code=" + code + "&playerId=" + playerId + "&ready=true";
        }

        return "redirect:/generator?code=" + code + "&playerId=" + playerId;

    }


    @PostMapping("/reveal")
    public String reveal(@RequestParam int code, @RequestParam String playerId) {

        GameModel session = createSession.getSession(code);

        if (session == null) {
            return "redirect:/";
        }

        Player player = session.getPlayers().stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(null);

        if (player == null || !player.isHost()) {
            return "redirect:/generator?code=" + code + "&playerId=" + playerId;
        }

        session.scoreRound();

        session.setPhase(GamePhase.REVEAL);
        return "redirect:/generator?code=" + code + "&playerId=" + playerId;

    }


    @PostMapping("/next-round")
    public String nextRound(@RequestParam int code, @RequestParam String playerId) {

        GameModel session = createSession.getSession(code);

        if (session == null) {
            return "redirect:/";
        }

        Player player = session.getPlayers().stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(null);

        if (player == null || !player.isHost()) {
            return "redirect:/generator?code=" + code + "&playerId=" + playerId;
        }

        session.setCurrentRound(session.getCurrentRound() + 1);
        session.resetForNextRound();

        createSession.assignImposter(session);

        String category = createSession.chooseRandomCategory();
        String question = createSession.chooseRandomQuestion(category);

        session.setCategory(category);
        session.setQuestion(question);

        session.setPhase(GamePhase.WAITING);

        return "redirect:/generator?code=" + code + "&playerId=" + playerId;

    }
}

