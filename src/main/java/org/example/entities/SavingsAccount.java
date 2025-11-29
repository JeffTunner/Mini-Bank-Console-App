package org.example.entities;

import java.util.Date;
import java.util.UUID;

public class SavingsAccount extends Account{

    private double rate;

    public SavingsAccount(String accountId, User owner, double balance, String createdAt, AccountType type, double rate) {
        super(accountId, owner, balance, createdAt, type);
        this.rate = rate;

    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return this.rate;
    }

    @Override
    public boolean silentWithdraw(double amount) {
        double monthlyLimit = 10000.00;
        double minBalance = 500.00;
        if(amount > this.getBalance()) {
            System.out.println("Insufficient Funds!!!");
            return false;
        }else if(amount > monthlyLimit) {
            System.out.println("Monthly Limit Exceeded!!!");
            return false;
        }else if(this.getBalance() - amount < minBalance) {
            System.out.println("Cannot withdraw, Minimum Balance Exceeded!!!");
            return false;
        } else {
            double newBalance = this.getBalance() - amount;
            this.setBalance(newBalance);
            return true;
        }
    }

    @Override
    public void withdraw(double amount) {
        double monthlyLimit = 10000.00;
        double minBalance = 500.00;
        if(amount > this.getBalance()) {
            System.out.println("Insufficient Funds!!!");
        }else if(amount > monthlyLimit) {
            System.out.println("Monthly Limit Exceeded!!!");
        }else if(this.getBalance() - amount < minBalance) {
            System.out.println("Cannot withdraw, Minimum Balance Exceeded!!!");
        } else {
            double newBalance = this.getBalance() - amount;
            this.setBalance(newBalance);
            Transaction transaction = new Transaction( UUID.randomUUID().toString(), this.getAccountId(), this.getAccountId(), amount, new Date().toString(), TransactionType.WITHDRAW);
            this.addTransaction(transaction);
            System.out.println("Amount " +amount + " withdrawn from " +this.getAccountId());
        }
    }

    public void applyInterest() {
        double balance = this.getBalance();
        double interest = 0.0;
        if(this.rate <= 0) {
            System.out.println("Invalid Rate!!!");
        }else {
            interest = (balance*this.rate)/100;
            deposit(interest);
        }
    }
}
