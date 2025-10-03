package com.teonvioncollins.atmapp.services;

import com.teonvioncollins.atmapp.models.AtmModel;
import org.springframework.stereotype.Service;

@Service
public class AtmService {

    private static final AtmModel am = new AtmModel(0.00);

    public static double getBalance() {
        return am.getBalance();
    }

    public static void makeDeposit(double deposit) {
        if (deposit > 0) {
            am.setBalance(am.getBalance() + deposit);
        }
    }

    public static void makeWithdraw(double withdraw) {
        if (withdraw > 0) {
            am.setBalance(am.getBalance() - withdraw);
        }
    }
}
