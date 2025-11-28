package org.example.service;

import org.example.entities.Account;
import org.example.entities.Transaction;
import org.example.entities.TransactionType;
import org.example.entities.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Bank {

    List<Account> accounts;

    public void createAccount(String accountId, User owner, double balance, String createdAt, List<Transaction> transactionHistory) {
        Account newAccount = new Account(accountId, owner, balance, createdAt, transactionHistory);
        accounts.add(newAccount);
        System.out.println("Account successfully created!!!");
    }

    public void findAccount(String accountId) {
        boolean found = false;
        for(Account account: accounts) {
            if(account.getAccountId() == accountId) {
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
            if(account.getAccountId() == accountId) {
                if(amount < 0) {
                    System.out.println("Amount cannot be Negative!!!");
                    break;
                }
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
            if(account.getAccountId() == accountId) {
                if(amount < 0) {
                    System.out.println("Amount cannot be Negative!!!");
                    break;
                } else if (amount > account.getBalance()) {
                    System.out.println("INSUFFICIENT FUNDS!!!");
                }
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
        boolean transfer = false;
        for(Account account: accounts) {
            if(account.getAccountId() == fromId) {
                if(amount < 0) {
                    System.out.println("Amount cannot be Negative!!!");
                    break;
                } else if (amount > account.getBalance()) {
                    System.out.println("INSUFFICIENT FUNDS!!! \nCannot transfer!!!");
                }
                account.withdraw(amount);
                Transaction transaction = new Transaction( UUID.randomUUID().toString(), fromId, toId, amount, new Date().toString(), TransactionType.TRANSFER);
                account.addTransaction(transaction);
                transfer = true;
                break;
            }
        }

        for(Account account: accounts) {
            if(account.getAccountId() == toId) {
                if(amount < 0) {
                    System.out.println("Amount cannot be Negative!!!");
                    break;
                }
                account.deposit(amount);
                Transaction transaction = new Transaction( UUID.randomUUID().toString(), fromId, toId, amount, new Date().toString(), TransactionType.TRANSFER);
                account.addTransaction(transaction);
                transfer = true;
                break;
            }
        }

        if(transfer) {
            System.out.println("TRANSFER SUCCESSFUL!!!");
        } else {
            System.out.println("INVALID ID!!!");
        }
    }

    public void showAccountDetails(String accountId) {
        boolean found = false;
        Account showAccount = null;
        for(Account account: accounts) {
            if(account.getAccountId() == accountId) {
                showAccount = account;
                found = true;
                break;
            }
        }
        if(found) {
            System.out.println("Account Details: ");
            System.out.println("AccountID: " +showAccount.getAccountId() +
                    " \n Owner Name: " +showAccount.getOwner().getName() +
                    " \n Balance: " +showAccount.getBalance() +
                    "\n Created At: " +showAccount.getCreatedAt());
        } else {
            System.out.println("INVALID ACCOUNT ID!!!");
        }
    }

    public void showTransactionHistory(String accountId) {
        boolean found = false;
        Account showAccount = null;
        for(Account account: accounts) {
            if(account.getAccountId() == accountId) {
                showAccount = account;
                found = true;
                break;
            }
        }
        if(found) {
            System.out.println("Transaction History of "+accountId);
            System.out.println(showAccount.getTransactionHistory());
        } else {
            System.out.println("INVALID ACCOUNT ID!!!");
        }
    }

    public void deleteAccount(String accountId) {
        boolean found = false;
        for(Account account: accounts) {
            if(account.getAccountId() == accountId) {
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
