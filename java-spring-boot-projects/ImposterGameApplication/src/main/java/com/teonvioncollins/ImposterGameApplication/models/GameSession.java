package com.teonvioncollins.ImposterGameApplication.models;

import java.util.List;

public class GameSession {

    private int code;
    private String category;
    private String question;
    private List<Player> players;
    private int maxPlayers = 0;
    private boolean locked = false;
    private boolean assignedImposter = false;

    public GameSession() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isAssignedImposter() {
        return assignedImposter;
    }

    public void setAssignedImposter(boolean assignedImposter) {
        this.assignedImposter = assignedImposter;
    }
}
