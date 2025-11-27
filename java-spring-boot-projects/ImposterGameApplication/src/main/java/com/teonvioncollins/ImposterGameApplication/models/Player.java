package com.teonvioncollins.ImposterGameApplication.models;

public class Player {

    public String name;
    public int newPlayerNumber;
    public String playerId;
    public String role;


    public Player(String name, int newPlayerNumber, String playerId){
        this.name = name;
        this.newPlayerNumber = newPlayerNumber;
        this.playerId = playerId;
        this.role = "INNOCENT \uD83D\uDE05";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNewPlayerNumber() {
        return newPlayerNumber;
    }

    public void setNewPlayerNumber(int newPlayerNumber) {
        this.newPlayerNumber = newPlayerNumber;
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
