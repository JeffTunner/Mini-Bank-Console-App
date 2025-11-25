package org.example.service;

import org.example.exception.InsufficientFundsException;
import org.example.model.Account;
import org.example.model.SavingsAccount;
import org.example.model.Transaction;
import org.example.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {

    private Map<String, Account> accounts = new HashMap<>();
    List<Transaction> transactions = new ArrayList<>();

    public Account createAccount(String id, User owner, double initial) {
        Account newAccount = new Account(id, owner, initial){};
        accounts.put(id, newAccount);
        return newAccount;
    }

    public void deposit(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if(account.getAccountID().equals(accountId)) {
            account.deposit(amount);
        }
    }

    public double displayBalance(String id) {
        return accounts.get(id).getBalance();
    }

    public void withdraw(String accountId, double amount) throws InsufficientFundsException {
        Account account = accounts.get(accountId);
        if(account.getAccountID().equals(accountId)) {
            account.withdraw(amount);
        }
    }
}
