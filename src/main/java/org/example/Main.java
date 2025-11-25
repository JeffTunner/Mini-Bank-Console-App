package org.example;

import org.example.model.User;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        User user = new User(new UUID(256, 256), "Abhinav", "test@gmail.com", "123456789");

        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getID());

    }
}