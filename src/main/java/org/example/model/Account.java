package org.example.model;

import org.example.exception.InsufficientFundsException;

public abstract class Account {

    private final String accountID;

    private final User owner;

    private final double balance;

    public Account(String accountID, User owner, double initial ) {
        this.accountID = accountID;
        this.owner = owner;
        this.balance = initial;
    }

    public String getAccountID() {
        return accountID;
    }

    public User getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        double totalBalance = balance + amount;
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;
}
