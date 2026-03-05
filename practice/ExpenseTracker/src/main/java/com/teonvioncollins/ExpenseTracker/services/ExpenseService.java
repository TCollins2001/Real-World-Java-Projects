package com.teonvioncollins.ExpenseTracker.services;

import com.teonvioncollins.ExpenseTracker.models.ExpenseModel;
import com.teonvioncollins.ExpenseTracker.repos.ExpenseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepo expenseRepo;

    public ExpenseService(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    public void addExpense(String itemDesc, double itemAmount) {
        expenseRepo.save(new ExpenseModel(itemDesc, itemAmount));
    }

    public List<ExpenseModel> getAllExpenses() {
        return expenseRepo.findAll();
    }

    public double getTotalExpenses() {
        return expenseRepo.findAll().stream().mapToDouble(ExpenseModel::getItemAmount).sum();
    }

    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
