package org.example.model;

import org.example.exception.InsufficientFundsException;

public class SavingsAccount extends Account{

    private double interestRate;

    public SavingsAccount(double interestRate, String accountID, User owner, double balance) {
        super(accountID, owner, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if(amount>getBalance()) {
            throw new InsufficientFundsException();
        }
        double totalBalance = getBalance() - amount;
        setBalance(totalBalance);
    }

    public void applyInterest(double amount) {
        double totalBalance = (amount * interestRate) + amount;
    }
}
