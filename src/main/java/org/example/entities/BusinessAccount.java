package org.example.entities;

public class BusinessAccount extends Account{


    public BusinessAccount(String accountId, User owner, double balance, String createdAt, AccountType type) {
        super(accountId, owner, balance, createdAt, type);
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
        }
    }

    private double applyTax(double amount) {
        double rate = 8.5;
        return amount*rate;
    }
}
