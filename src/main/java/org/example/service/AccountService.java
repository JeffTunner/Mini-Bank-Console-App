package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Account;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccountService {

    private static final String PATH = "src/main/java/org/example/localDB/accounts.json";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private ArrayList<Account> accounts;

    public AccountService() throws IOException {
        loadAccounts();
    }

    public void loadAccounts() throws IOException {
        File file = new File(PATH);
        try {
            accounts = objectMapper.readValue(file, new TypeReference<ArrayList<Account>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAccounts() {
        File file = new File(PATH);
        try{
            objectMapper.writeValue(file, accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account) {
        accounts.add(account);
        saveAccounts();
    }

    public void deleteAccount(String accountId) {
        for(Account account1: accounts) {
            if(account1.getAccountId().equals(accountId)) {
                accounts.remove(account1);
                saveAccounts();
                break;
            }
        }
    }

    public boolean searchAccount(String accountId) {
        for (Account account: accounts) {
            if(account.getAccountId().equals(accountId)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
