package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String accountId;
    private User owner;
    private double balance;
    private String createdAt;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();

    public Account(String accountId, User owner, double balance, String createdAt) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    // SETTERS
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = (ArrayList<Transaction>) transactionHistory;
    }

    // GETTERS
    public String getAccountId() {
        return accountId;
    }

    public User getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        setBalance(this.balance + amount);
    }

    public void withdraw(double amount) {
        setBalance(this.balance - amount);
    }

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }
}
