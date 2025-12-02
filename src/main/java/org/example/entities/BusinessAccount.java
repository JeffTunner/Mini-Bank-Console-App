package org.example.entities;

import java.util.Date;
import java.util.UUID;

public class BusinessAccount extends Account{


    public BusinessAccount(String accountId, User owner, double balance, String createdAt, AccountType type) {
        super(accountId, owner, balance, createdAt, type);
    }

    public BusinessAccount() {
        super(null, null, 0, null, null);
    }


    @Override
    public void withdraw(double amount) {
        double fee = 200.00;
        double minBalance = 10000.00;
        double balance = this.getBalance();
        if(amount > this.getBalance()) {
            System.out.println("Insufficient Funds!!!");
        } else if (balance - amount < minBalance) {
            System.out.println("Cannot Withdraw, Min Balance exceeded!!!");
        } else {
            double newBalance = balance - amount - fee;
            this.setBalance(newBalance);
            Transaction transaction = new Transaction( UUID.randomUUID().toString(), this.getAccountId(), this.getAccountId(), amount, new Date().toString(), TransactionType.WITHDRAW);
            this.addTransaction(transaction);
            System.out.println("Amount " +amount + " withdrawn from " +this.getAccountId());
        }
    }

    @Override
    public void deposit(double amount) {
        double fee = 200.00;
        double balance = this.getBalance();
        if(amount < 0) {
            System.out.println("Amount cannot be Negative!!!");
        } else {
            double newBalance = (balance + amount) - (fee + applyTax(amount));
            this.setBalance(newBalance);
            Transaction transaction = new Transaction( UUID.randomUUID().toString(), this.getAccountId(), this.getAccountId(), amount, new Date().toString(), TransactionType.DEPOSIT);
            addTransaction(transaction);
            System.out.println("Amount " +amount + " deposited to " +this.getAccountId());
        }
    }

    @Override
    public void silentDeposit(double amount) {
        double fee = 200.00;
        double balance = this.getBalance();
        double newBalance = (balance + amount) - (fee + applyTax(amount));
        this.setBalance(newBalance);
    }

    @Override
    public boolean silentWithdraw(double amount) {
        double fee = 200.00;
        double minBalance = 10000.00;
        double balance = this.getBalance();
        if(amount > this.getBalance()) {
            System.out.println("Insufficient Funds!!!");
            return false;
        } else if (balance - amount < minBalance) {
            System.out.println("Cannot Withdraw, Min Balance exceeded!!!");
            return false;
        } else {
            double newBalance = balance - amount - fee;
            this.setBalance(newBalance);
            return true;
        }
    }

    private double applyTax(double amount) {
        double rate = 8.5;
        return amount*rate;
    }
}
