package com.teonvioncollins.ImposterGame.models;

import java.util.List;

import java.util.ArrayList;

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
}
