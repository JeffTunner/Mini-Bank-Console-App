package org.example.entities;

public class Transaction {

    private String transactionId;
    private String fromAccId;
    private String toAccId;
    private double amount;
    private String timestamp;
    private TransactionType type;

    public Transaction(String transactionId, String fromAccId, String toAccId, double amount, String timestamp, TransactionType type) {
        this.transactionId = transactionId;
        this.fromAccId = fromAccId;
        this.toAccId = toAccId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
    }

    // GETTERS
    public String getTransactionId() {
        return transactionId;
    }

    public String getFromAccId() {
        return fromAccId;
    }

    public String getToAccId() {
        return toAccId;
    }

    public double getAmount() {
        return amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    // SETTERS
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setFromAccId(String fromAccId) {
        this.fromAccId = fromAccId;
    }

    public void setToAccId(String toAccId) {
        this.toAccId = toAccId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
