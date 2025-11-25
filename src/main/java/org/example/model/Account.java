package org.example.model;

import org.example.exception.InsufficientFundsException;

public abstract class Account {

    private String accountID;

    private User owner;

    private double balance;

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

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void deposit(double amount) {
        double totalBalance = balance + amount;
        setBalance(totalBalance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if(amount>balance) {
            throw new InsufficientFundsException();
        }
        double totalBalance = balance - amount;
        setBalance(totalBalance);
    }
}
