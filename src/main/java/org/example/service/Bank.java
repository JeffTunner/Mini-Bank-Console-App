package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.*;

import java.io.IOException;
import java.util.*;

public class Bank {

    Scanner sc = new Scanner(System.in);

    AccountService accountService = new AccountService();

    TransactionService transactionService = new TransactionService();

    public Bank() throws IOException {
    }

    public void createAccount(String accountId, User owner, double balance, String createdAt, AccountType type) {
        switch (type) {
            case SAVINGS:
                System.out.print("Enter Interest Rate: ");
                double rate = sc.nextDouble();
                SavingsAccount savingsAccount = new SavingsAccount(accountId, owner, balance, createdAt, type, rate);
                accountService.addAccount(savingsAccount);
                System.out.println("Account with AccountId: " +accountId + " successfully created!!!");
                break;
            case CURRENT:
                CurrentAccount currentAccount = new CurrentAccount(accountId, owner, balance, createdAt, type);
                accountService.addAccount(currentAccount);
                System.out.println("Account with AccountId: " +accountId + " successfully created!!!");
                break;
            case BUSINESS:
                BusinessAccount businessAccount = new BusinessAccount(accountId, owner, balance, createdAt, type);
                accountService.addAccount(businessAccount);
                System.out.println("Account with AccountId: " +accountId + " successfully created!!!");
                break;
            default:
                System.out.println("Invalid TYPE of Account!!!");
                break;
        }
    }

    public void findAccount(String accountId) {
        boolean found = accountService.searchAccount(accountId);
        if(found) {
            System.out.println("Account: "+accountId + " FOUND!!!");
        } else {
            System.out.println("Account ID: "+accountId + " did not match with any accounts.");
        }
    }

    public void deposit(String accountId, double amount) {
        boolean found = false;
        for(Account account: accountService.getAccounts()) {
            if(account.getAccountId().equals(accountId)) {
                account.deposit(amount);
                accountService.saveAccounts();
                found = true;
                break;
            }
        }
        if(!found) {
                System.out.println("Invalid Account ID!!!");
        }
    }

    public void withdraw(String accountId, double amount) {
        boolean found = false;
        for(Account account: accountService.getAccounts()) {
            if(account.getAccountId().equals(accountId)) {
                account.withdraw(amount);
                accountService.saveAccounts();
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Invalid Account ID!!!");
        }
    }

    public void transfer(String fromId, String toId, double amount) {
        int transfer = 0;
        Account from = null;
        Account to = null;
        for(Account account: accountService.getAccounts()) {
            if(account.getAccountId().equals(fromId)) {
                account.silentWithdraw(amount);
                accountService.saveAccounts();
                from = account;
                transfer++;
            }
        }

        for(Account account: accountService.getAccounts()) {
            if(account.getAccountId().equals(toId)) {
                account.silentDeposit(amount);
                accountService.saveAccounts();
                to = account;
                transfer++;
            }
        }

        if(transfer == 2) {
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), fromId, toId, amount, new Date().toString(), TransactionType.TRANSFER);
            assert from != null;
            from.addTransaction(transaction);
            assert to != null;
            to.addTransaction(transaction);
            accountService.saveAccounts();
            System.out.println("TRANSFER SUCCESSFUL!!!");
        } else {
            System.out.println("INVALID ID!!!");
        }
    }

    public void showAccountDetails(String accountId) {
        boolean found = false;
        for(Account account: accountService.getAccounts()) {
            if(account.getAccountId().equals(accountId)) {
                account.printDetails();
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Invalid Account ID!!!");
        }
    }

    public void showTransactionHistory(String accountId) {
        boolean found = false;
        Account showAccount = null;
        for(Account account: accountService.getAccounts()) {
            if(account.getAccountId().equals(accountId)) {
                showAccount = account;
                found = true;
                break;
            }
        }
        if(found) {
            System.out.println("Transaction History of "+accountId);
            for(Transaction transaction: showAccount.getTransactionHistory()) {
                transaction.transactionDetails();
            }
        } else {
            System.out.println("INVALID ACCOUNT ID!!!");
        }
    }

    public void deleteAccount(String accountId) {
        boolean found = false;
        for(Account account: accountService.getAccounts()) {
            if(account.getAccountId().equals(accountId)) {
                accountService.deleteAccount(accountId);
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

    public void applyInterest(String accountId) {
        boolean found = false;
        for(Account account: accountService.getAccounts()) {
            if(account.getAccountId().equals(accountId)) {
                if(account instanceof SavingsAccount) {
                    SavingsAccount savingsAccount = (SavingsAccount) account;
                    savingsAccount.applyInterest();
                    accountService.saveAccounts();
                    found = true;
                    break;
                }
                break;
            }
        }
        if(!found) {
            System.out.println("Invalid Account ID!!!");
        }
    }

}
