package org.example.model;

public class SavingsAccount extends Account{

    private double interestRate;

    public SavingsAccount(double interestRate, String accountID, User owner, double balance) {
        super(accountID, owner, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        double totalBalance = getBalance() - amount;
    }

    public void applyInterest(double amount) {
        double totalBalance = (amount * interestRate) + amount;
    }
}
