package org.example;

import org.example.entities.AccountType;
import org.example.entities.Transaction;
import org.example.entities.User;
import org.example.service.Bank;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("WELCOME TO THE BANK MANAGEMENT SYSTEM!!!");
            System.out.println("Choose your Service by entering the corresponding number");
            System.out.println("1 -> Create an Account");
            System.out.println("2 -> Find an Account");
            System.out.println("3 -> Deposit");
            System.out.println("4 -> Withdraw");
            System.out.println("5 -> Transfer");
            System.out.println("6 -> Show Account Details");
            System.out.println("7 -> Show Transaction History");
            System.out.println("8 -> Delete an Account");
            System.out.println("9 -> EXIT");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    String accountId = UUID.randomUUID().toString();
                    String createdAt = new Date().toString();
                    double balance = 0.0;
                    AccountType type = null;
                    System.out.println("Enter name of Owner: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    User owner = new User(name);
                    System.out.println("Enter type of account: 1) SAVINGS  2) CURRENT  3) BUSINESS ");
                    int t = sc.nextInt();
                    if(t == 1) {type = AccountType.SAVINGS;}
                    else if(t == 2) {type = AccountType.CURRENT;}
                    else if(t == 3) {type = AccountType.BUSINESS;}
                    bank.createAccount(accountId, owner, balance, createdAt, type);
                    break;
                case 2:
                    System.out.println("Enter Account Id to search: ");
                    sc.nextLine();
                    String id = sc.nextLine();
                    bank.findAccount(id);
                    break;
                case 3:
                    System.out.println("Enter Account Id and Amount you want to deposit: ");
                    sc.nextLine();
                    String accId = sc.nextLine();
                    double amt = sc.nextDouble();
                    bank.deposit(accId, amt);
                    break;
                case 4:
                    System.out.println("Enter Account Id and Amount you want to withdraw: ");
                    sc.nextLine();
                    String aId = sc.nextLine();
                    double wAmt = sc.nextDouble();
                    bank.withdraw(aId, wAmt);
                    break;
                case 5:
                    System.out.println("Enter from Account Id, to Account Id, and a amount ");
                    sc.nextLine();
                    String from = sc.nextLine();
                    String to = sc.nextLine();
                    double transferAmt = sc.nextDouble();
                    bank.transfer(from, to, transferAmt);
                    break;
                case 6:
                    System.out.println("Enter Account ID: ");
                    sc.nextLine();
                    String showId = sc.nextLine();
                    bank.showAccountDetails(showId);
                    break;
                case 7:
                    System.out.println("Enter Account ID: ");
                    sc.nextLine();
                    String transId = sc.nextLine();
                    bank.showTransactionHistory(transId);
                    break;
                case 8:
                    System.out.println("Enter ID to delete: ");
                    sc.nextLine();
                    String deleteId = sc.nextLine();
                    bank.deleteAccount(deleteId);
                    break;
                case 9:
                    System.out.println("THANK YOU FOR VISITING!!!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("INVALID CHOICE!!!");
                    break;
            }
        }
    }
}