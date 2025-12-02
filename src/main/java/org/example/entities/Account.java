package org.example.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "savings"),
        @JsonSubTypes.Type(value = CurrentAccount.class, name = "current"),
        @JsonSubTypes.Type(value = BusinessAccount.class, name = "business")
})
public abstract class Account {

    private String accountId;
    private User owner;
    private double balance;
    private String createdAt;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();
    private AccountType type;

     Account(String accountId, User owner, double balance, String createdAt, AccountType type) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = balance;
        this.createdAt = createdAt;
        this.type = type;
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

    public void setTransactionHistory(ArrayList<Transaction> transactionHistory) {
        this.transactionHistory = (ArrayList<Transaction>) transactionHistory;
    }

    public void setType(AccountType type) {
        this.type = type;
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

    public AccountType getType() {
        return type;
    }

    // METHODS

    public void deposit(double amount) {
         if(amount < 0) {
             System.out.println("Amount Cannot be Negative!!!");
         } else {
             this.setBalance((this.getBalance() + amount));
             Transaction transaction = new Transaction( UUID.randomUUID().toString(), this.getAccountId(), this.getAccountId(), amount, new Date().toString(), TransactionType.DEPOSIT);
             addTransaction(transaction);
             System.out.println("Amount " +amount + " deposited to " +accountId);
         }
    }

    public void silentDeposit(double amount) {
         this.setBalance(this.getBalance() + amount);
    }

    public abstract boolean silentWithdraw(double amount);

    public abstract void withdraw(double amount);

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }

    public void printDetails() {
        System.out.println("Account ID: " +this.getAccountId() +
                " \nOwner Name: " +this.getOwner().getName() +
                " \nBalance: " +this.getBalance() +
                " \nType: " +this.getType() +
                " \nCreated At: " +this.getCreatedAt());
    }

}
