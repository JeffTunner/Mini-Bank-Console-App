package org.example.model;

import java.util.Date;
import java.util.UUID;

public class Transaction {

    private UUID id;

    private Date timestamp;

    public UUID fromAccountID;

    public UUID toAccountID;

    public double amount;

    public enum type{}

    public Transaction(UUID id, Date stamp, UUID fromAccountID, UUID toAccountID, double amount) {
        this.id = id;
        this.timestamp = stamp;
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public UUID getFromAccountID() {
        return fromAccountID;
    }

    public UUID getToAccountID() {
        return toAccountID;
    }

    public UUID getId() {
        return id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFromAccountID(UUID fromAccountID) {
        this.fromAccountID = fromAccountID;
    }

    public void setToAccountID(UUID toAccountID) {
        this.toAccountID = toAccountID;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
