package com.teonvioncollins.ImposterGame.models;

import java.util.UUID;

public class Player {

    private String name;
    private String playerId;
    private String role;

    private boolean isHost;

    private int score = 0;

    private int roundScore = 0;

    private int totalScore = 0;

    public Player() {}

    public Player(String name) {
        this.name = name;
        this.playerId = UUID.randomUUID().toString();
        this.role = "INNOCENT \uD83D\uDE05";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        this.isHost = host;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void resetRoundScore() {
        this.roundScore = 0;
    }
}

