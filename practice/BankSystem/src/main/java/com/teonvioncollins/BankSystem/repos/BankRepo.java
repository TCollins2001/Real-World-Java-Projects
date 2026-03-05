package com.teonvioncollins.BankSystem.repos;

import com.teonvioncollins.BankSystem.models.BankModel;
import com.teonvioncollins.BankSystem.services.BankService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<BankModel, Long> {

    BankModel findByAccountType(String accountType);
}
