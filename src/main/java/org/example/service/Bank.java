package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.*;

import java.util.*;

public class Bank {

    Scanner sc = new Scanner(System.in);

    ArrayList<Account> accounts = new ArrayList<>();

    public void createAccount(String accountId, User owner, double balance, String createdAt, AccountType type) {
        switch (type) {
            case SAVINGS:
                System.out.print("Enter Interest Rate: ");
                double rate = sc.nextDouble();
                SavingsAccount savingsAccount = new SavingsAccount(accountId, owner, balance, createdAt, type, rate);
                accounts.add(savingsAccount);
                System.out.println("Account with AccountId: " +accountId + " successfully created!!!");
                break;
            case CURRENT:
                CurrentAccount currentAccount = new CurrentAccount(accountId, owner, balance, createdAt, type);
                accounts.add(currentAccount);
                System.out.println("Account with AccountId: " +accountId + " successfully created!!!");
                break;
            case BUSINESS:
                BusinessAccount businessAccount = new BusinessAccount(accountId, owner, balance, createdAt, type);
                accounts.add(businessAccount);
                System.out.println("Account with AccountId: " +accountId + " successfully created!!!");
                break;
            default:
                System.out.println("Invalid TYPE of Account!!!");
                break;
        }
    }

    public void findAccount(String accountId) {
        boolean found = false;
        for(Account account: accounts) {
            if(account.getAccountId().equals(accountId)) {
                found = true;
                break;
            }
        }
        if(found) {
            System.out.println("Account: "+accountId + " FOUND!!!");
        } else {
            System.out.println("Account ID: "+accountId + " did not match with any accounts.");
        }
    }

    public void deposit(String accountId, double amount) {
        boolean found = false;
        for(Account account: accounts) {
            if(account.getAccountId().equals(accountId)) {
                account.deposit(amount);
                Transaction transaction = new Transaction( UUID.randomUUID().toString(), accountId, accountId, amount, new Date().toString(), TransactionType.DEPOSIT);
                account.addTransaction(transaction);
                found = true;
                break;
            }
        }
        if(found) {
            System.out.println("Amount " +amount + " deposited to " +accountId);
        } else {
            System.out.println("Invalid Account ID!!!");
        }
    }

    public void withdraw(String accountId, double amount) {
        boolean found = false;
        for(Account account: accounts) {
            if(account.getAccountId().equals(accountId)) {
                account.withdraw(amount);
                Transaction transaction = new Transaction( UUID.randomUUID().toString(), accountId, accountId, amount, new Date().toString(), TransactionType.WITHDRAW);
                account.addTransaction(transaction);
                found = true;
                break;
            }
        }
        if(found) {
            System.out.println("Amount " +amount + " withdrawn from " +accountId);
        } else {
            System.out.println("Invalid Account ID!!!");
        }
    }

    public void transfer(String fromId, String toId, double amount) {
        int transfer = 0;
        for(Account account: accounts) {
            if(account.getAccountId().equals(fromId)) {
                account.withdraw(amount);
                Transaction transaction = new Transaction( UUID.randomUUID().toString(), fromId, toId, amount, new Date().toString(), TransactionType.TRANSFER);
                account.addTransaction(transaction);
                transfer++;
            }
        }

        for(Account account: accounts) {
            if(account.getAccountId().equals(toId)) {
                account.deposit(amount);
                Transaction transaction = new Transaction( UUID.randomUUID().toString(), fromId, toId, amount, new Date().toString(), TransactionType.TRANSFER);
                account.addTransaction(transaction);
                transfer++;
            }
        }

        if(transfer == 2) {
            System.out.println("TRANSFER SUCCESSFUL!!!");
        } else {
            System.out.println("INVALID ID!!!");
        }
    }

    public void showAccountDetails(String accountId) {
        for(Account account: accounts) {
            if(account.getAccountId().equals(accountId)) {
                account.printDetails();
                break;
            } else {
                System.out.println("INVALID ACCOUNT ID!!!");
            }
        }
    }

    public void showTransactionHistory(String accountId) {
        boolean found = false;
        Account showAccount = null;
        for(Account account: accounts) {
            if(account.getAccountId().equals(accountId)) {
                showAccount = account;
                found = true;
                break;
            }
        }
        if(found) {
            System.out.println("Transaction History of "+accountId);
            for(Transaction transaction: showAccount.getTransactionHistory()) {
                System.out.println("Transaction ID: " +transaction.getTransactionId() +
                        " \n From: " +transaction.getFromAccId() +
                        " \n To: " +transaction.getToAccId() +
                        " \n Amount: " +transaction.getAmount() +
                        " \n TimeStamp: " +transaction.getTimestamp() +
                        " \n Type: " +transaction.getType());
            }
        } else {
            System.out.println("INVALID ACCOUNT ID!!!");
        }
    }

    public void deleteAccount(String accountId) {
        boolean found = false;
        for(Account account: accounts) {
            if(account.getAccountId().equals(accountId)) {
                accounts.remove(account);
                found = true;
                break;
            }
        }
        if(found) {
            System.out.println("Account "+accountId + " DELETED!!!");
        } else {
            System.out.println("INVALID ACCOUNT ID!!!");
        }
    }

}
