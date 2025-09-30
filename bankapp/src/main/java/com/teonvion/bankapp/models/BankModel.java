package com.teonvion.bankapp.models;

public class BankModel {

    private double userBalance;

    public BankModel(double userBalance) {
        this.userBalance = userBalance;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }
}
