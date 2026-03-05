package com.teonvioncollins.ExpenseTracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExpenseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String itemDesc;

    public double itemAmount;

    public ExpenseModel() {}

    public ExpenseModel(Long id, String itemDesc, double itemAmount) {
        this.id = id;
        this.itemDesc = itemDesc;
        this.itemAmount = itemAmount;
    }

    public ExpenseModel(String itemDesc, double itemAmount) {
        this.itemDesc = itemDesc;
        this.itemAmount = itemAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(double itemAmount) {
        this.itemAmount = itemAmount;
    }
}
