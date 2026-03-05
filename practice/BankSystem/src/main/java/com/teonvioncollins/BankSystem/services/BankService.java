package com.teonvioncollins.BankSystem.services;

import com.teonvioncollins.BankSystem.models.BankModel;
import com.teonvioncollins.BankSystem.repos.BankRepo;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private final BankRepo bankRepo;


    public BankService(BankRepo bankRepo) {
        this.bankRepo = bankRepo;
    }

    public void depositFunds(String accountType, double amount) {
        BankModel account = bankRepo.findByAccountType(accountType);
        account.setBalance(account.getBalance() + amount);
        bankRepo.save(account);
    }

    public void withdrawFunds(String accountType, double amount) {
        BankModel account = bankRepo.findByAccountType(accountType);
        account.setBalance(account.getBalance() - amount);
        bankRepo.save(account);
    }
}
