package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Account;
import org.example.entities.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionService {

    private static final String PATH = "src/main/java/org/example/localDB/transactions.json";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private ArrayList<Transaction> transactions;

    public TransactionService() {
        loadTransactions();
    }

    public void loadTransactions() {
        File file = new File(PATH);
        try {
            transactions = objectMapper.readValue(file, new TypeReference<ArrayList<Transaction>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTransactions() {
        File file = new File(PATH);
        try {
            objectMapper.writeValue(file, transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions();
    }

}
