package com.teonvioncollins.ImposterGame.models;

import java.util.UUID;

public class Player {

    private String name;
    private String playerId;
    private String role;

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

}

