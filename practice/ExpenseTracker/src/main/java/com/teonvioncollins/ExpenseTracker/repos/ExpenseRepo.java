package com.teonvioncollins.ExpenseTracker.repos;
import com.teonvioncollins.ExpenseTracker.models.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<ExpenseModel, Long> {
}
