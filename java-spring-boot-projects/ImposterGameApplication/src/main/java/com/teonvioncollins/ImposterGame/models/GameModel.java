package com.teonvioncollins.ImposterGame.models;

import java.util.*;

public class GameModel {

    public int code;
    public int maxPlayers;
    public boolean locked = false;
    List<Player> players = new ArrayList<>();
    public boolean imposterAssigned = false;
    private String category;
    private String question;
    private int playersLeft;
    private long readyAt = 0;
    private int totalRounds;
    private int currentRound = 1;
    private int groupWins = 0;
    private int imposterWins = 0;

    private GamePhase phase = GamePhase.WAITING;

    private Map<String, String> votes = new HashMap<>();

    public GameModel(int code) {
        this.code = code;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public boolean isImposterAssigned() {
        return imposterAssigned;
    }

    public void setImposterAssigned(boolean imposterAssigned) {
        this.imposterAssigned = imposterAssigned;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getReadyAt() {
        return readyAt;
    }

    public void setReadyAt(long readyAt) {
        this.readyAt = readyAt;
    }

    public void lock(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.locked = true;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean canJoin() {
        return !locked || players.size() < maxPlayers;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int playersLeft() {
        return maxPlayers - players.size();
    }

    public GamePhase getPhase() {
        return phase;
    }

    public void setPhase(GamePhase phase) {
        this.phase = phase;
    }

    public Map<String, String> getVotes() {
        return votes;
    }

    public void setVotes(Map<String, String> votes) {
        this.votes = votes;
    }

    public Player getImposter() {
        return players.stream().filter(p -> p.getRole().startsWith("IMPOSTER"))
                .findFirst().orElse(null);
    }

    public int correctVotes() {
        Player imposter = getImposter();
        if (imposter == null) return 0;

        return (int) votes.values().stream()
                .filter(votedFor -> votedFor.equals(imposter.getPlayerId()))
                .count();
    }

    public int totalVotes() {
        return votes.size();
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getGroupWins() {
        return groupWins;
    }

    public void setGroupWins(int groupWins) {
        this.groupWins = groupWins;
    }

    public int getImposterWins() {
        return imposterWins;
    }

    public void setImposterWins(int imposterWins) {
        this.imposterWins = imposterWins;
    }

    public void scoreRound() {
        Player imposter = getImposter();
        if (imposter == null) return;

        long correctCount = votes.values().stream()
                .filter(v -> v.equals(imposter.getPlayerId()))
                .count();

        long totalPlayers = players.size();
        boolean imposterLoses = correctCount > totalPlayers / 2;

        players.forEach(p -> p.setRoundScore(0));

        if (correctCount == 0) {
            imposter.setRoundScore(5);
            imposter.addScore(5);
            imposter.setTotalScore(imposter.getScore());
            imposterWins++;
            return;
        }

        if (!imposterLoses) {
            imposter.setRoundScore(3);
            imposter.addScore(3);

            players.stream()
                    .filter(p -> votes.get(p.getPlayerId()) != null &&
                            votes.get(p.getPlayerId()).equals(imposter.getPlayerId()) &&
                            !p.equals(imposter))
                    .forEach(p -> {
                        p.setRoundScore(5);
                        p.addScore(5);
                    });

            players.forEach(p -> p.setTotalScore(p.getScore()));
            imposterWins++;
            return;
        }

        players.stream()
                .filter(p -> votes.get(p.getPlayerId()) != null &&
                        votes.get(p.getPlayerId()).equals(imposter.getPlayerId()) &&
                        !p.equals(imposter))
                .forEach(p -> {
                    p.setRoundScore(5);
                    p.addScore(5);
                });

        players.forEach(p -> p.setTotalScore(p.getScore()));
        groupWins++;
    }

    public void resetForNextRound() {
        this.votes.clear();
        this.imposterAssigned = false;
        this.category = null;
        this.question = null;
        this.phase = GamePhase.WAITING;

        players.forEach(p -> {
            p.resetRoundScore();
            p.setRole("INNOCENT 😅");
        });
    }
}