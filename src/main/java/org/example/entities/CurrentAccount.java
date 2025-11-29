package org.example.entities;

public class CurrentAccount extends Account{

    public CurrentAccount(String accountId, User owner, double balance, String createdAt, AccountType type) {
        super(accountId, owner, balance, createdAt, type);
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
        }
    }
}
