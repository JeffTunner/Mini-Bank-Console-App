package org.example.model;

import java.util.UUID;

public class User {

    public UUID userID;

    public String username;

    public String email;

    public String password;

    public User(UUID id, String name, String email, String password) {
        this.userID = id;
        this.username = name;
        this.email = email;
        this.password = password;
    }

    public UUID getID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setID(UUID id) {
        this.userID = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
