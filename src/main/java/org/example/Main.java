package org.example;

import org.example.model.User;
import org.example.service.BankService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UUID userid = UUID.randomUUID();
        String name;
        String email;
        String password;
        String accountid = UUID.randomUUID().toString();
        double balance;

        System.out.println("Welcome to the bank!");
        System.out.println("Enter details.");
        name = sc.nextLine();
        email = sc.nextLine();
        password = sc.nextLine();
        balance = sc.nextDouble();

        User owner = new User(userid, name, email, password);

        System.out.println("Creating an account for you");
        BankService service = new BankService();
        service.createAccount(accountid,owner, balance);
        System.out.println("Account created");
        System.out.println("Enter amount to deposit");
        double amount = sc.nextDouble();
        service.deposit(accountid, amount);
        System.out.println("Your balance" + service.displayBalance(accountid));
        System.out.println("Enter amount to withdraw");
        double withdrawAmount = sc.nextDouble();
        service.withdraw(accountid, withdrawAmount);
        System.out.println("Your balance" + service.displayBalance(accountid));

    }
}