package org.example.entities;

import java.util.Date;
import java.util.UUID;

public class CurrentAccount extends Account{

    public CurrentAccount(String accountId, User owner, double balance, String createdAt, AccountType type) {
        super(accountId, owner, balance, createdAt, type);
    }

    @Override
    public boolean silentWithdraw(double amount) {
        double overdraftLimit = -10000.00;
        double balance = this.getBalance();
        if(balance-amount < overdraftLimit) {
            System.out.println("Overdraft Limited Exceeded!!!");
            return false;
        }else {
            double newBalance = balance - amount;
            this.setBalance(newBalance);
            return true;
        }
    }

    @Override
    public void withdraw(double amount) {
        double overdraftLimit = -10000.00;
        double balance = this.getBalance();
        if(balance-amount < overdraftLimit) {
            System.out.println("Overdraft Limited Exceeded!!!");
        }else {
            double newBalance = balance - amount;
            this.setBalance(newBalance);
            Transaction transaction = new Transaction( UUID.randomUUID().toString(), this.getAccountId(), this.getAccountId(), amount, new Date().toString(), TransactionType.WITHDRAW);
            this.addTransaction(transaction);
            System.out.println("Amount " +amount + " withdrawn from " +this.getAccountId());
        }
    }
}
