package org.example.entities;

import java.util.List;

public class Account {

    private String accountId;
    private User owner;
    private double balance;
    private String createdAt;
    private List<Transaction> transactionHistory;

    public Account(String accountId, User owner, double balance, String createdAt, List<Transaction> transactionHistory) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = balance;
        this.createdAt = createdAt;
        this.transactionHistory = transactionHistory;
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
        this.transactionHistory = transactionHistory;
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

    public double deposit(double amount) {
        return this.balance + amount;
    }

    public double withdraw(double amount) {
        return this.balance - amount;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }
}
