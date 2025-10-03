package com.teonvioncollins.atmapp.models;

public class AtmModel {

    private double balance;

    public AtmModel(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
