package com.teonvion.bankapp.services;

import com.teonvion.bankapp.models.BankModel;
import org.springframework.stereotype.Service;

@Service
public class BankLogic {
    private static final BankModel bank = new BankModel(0.00);

    public static double getBalance() {
        return bank.getUserBalance();
    }

    public void makeDeposit(double userDeposit) {
        if (userDeposit > 0) {
            bank.setUserBalance(bank.getUserBalance() + userDeposit);
        }
    }

        public void makeWithdraw(double userWithdraw) {
            if (userWithdraw > 0 && userWithdraw <= bank.getUserBalance()) {
                bank.setUserBalance(bank.getUserBalance() - userWithdraw);
            }
        }
    }
