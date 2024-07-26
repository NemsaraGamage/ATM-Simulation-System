package com.ass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Setting up accounts
        List<Account> accounts = new ArrayList<>(Arrays.asList(
                new Account(123123, 1111, 1000, "Nick"),
                new Account(222222, 2222, 2000, "James"),
                new Account(333333, 3333, 3000, "Jays")
        ));


        // Find account with matching account number and PIN
        Account currentAccount = null;

        while (true) {

            // Login section
            System.out.println("Welcome To ATM Simulation System ");
            System.out.println("------------------------------");
            System.out.println("1. Login");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.println("------------------------------");
            System.out.print("Enter your choice: ");
            String mainMenuOp = input.nextLine();

//            User Interface Section
            if (mainMenuOp.equalsIgnoreCase("login") || mainMenuOp.equals("1")) {
                System.out.println(" ");

                System.out.print("Enter Your 6 Digit Account Number: ");
                Integer accNo = input.nextInt();

                System.out.print("Enter Your 4 Digit PIN Number: ");
                Integer accPin = input.nextInt();

                // Consume the newline character
                input.nextLine();

                for (Account acc : accounts) {
                    if (acc.getAccountNumber().equals(accNo) && acc.getPin().equals(accPin)) {
                        currentAccount = acc;
                        break;
                    }
                }

                if (currentAccount != null) {
                    System.out.println(" ");
                    System.out.println("Welcome back " + "* " + currentAccount.getUserName() + " *");

                    boolean loggedIn = true;
                    while (loggedIn) {
                        // List of user functionality section
                        System.out.print("------------------------------\n" +
                                "1. Check Balance \n" +
                                "2. Deposit Money \n" +
                                "3. Withdraw Money \n" +
                                "4. Transfer Money \n" +
                                "5. View Transaction History \n" +
                                "6. Logout \n" +
                                "------------------------------\n" +
                                "Enter your choice: ");
                        Integer userAccOp = input.nextInt();

                        System.out.println(" ");

                        input.nextLine();

                        // Checking the user's account balance
                        if (userAccOp.equals(1)) {
                            System.out.println("******* Check Balance *******");
                            System.out.println("Your Current Balance Amount: $" + currentAccount.getBalance());
                            System.out.println(" ");
                        }
                        // Deposit Money functionality
                        else if (userAccOp.equals(2)) {
                            System.out.println("******* Deposit Money *******");
                            System.out.print("Enter Amount to Deposit: ");
                            Integer amount = input.nextInt();
                            currentAccount.deposit(amount);
                            System.out.println("---- Amount Deposited Successfully. ----");
                            System.out.println(" ");
                        }
                        // Withdraw Money functionality
                        else if (userAccOp.equals(3)) {
                            System.out.println("******* Withdraw Money *******");
                            System.out.print("Enter Amount to Withdraw: ");
                            Integer amount = input.nextInt();
                            if (currentAccount.withdraw(amount)) {
                                System.out.println("---- Amount Withdrawn Successfully. ----");
                            } else {
                                System.out.println("---- Insufficient Balance. ----");
                            }
                            System.out.println(" ");
                        }
                        // Transfer Money functionality
                        else if (userAccOp.equals(4)) {
                            System.out.println("******* Transfer Money *******");
                            System.out.print("Enter Recipient Account Number: ");
                            Integer recipientAccNo = input.nextInt();
                            System.out.print("Enter Amount to Transfer: ");
                            Integer amount = input.nextInt();

                            Account recipientAccount = null;
                            for (Account acc : accounts) {
                                if (acc.getAccountNumber().equals(recipientAccNo)) {
                                    recipientAccount = acc;
                                    break;
                                }
                            }

                            if (recipientAccount != null) {
                                currentAccount.transfer(recipientAccount, amount);
                                System.out.println("---- Amount Transferred Successfully. ----");
                            } else {
                                System.out.println("---- Recipient Account not Found. ----");
                            }
                            System.out.println(" ");
                        }
                        // View Transaction History functionality
                        else if (userAccOp.equals(5)) {
                            System.out.println("******* Transaction History *******");
                            currentAccount.getTransactionHistory().forEach((k, v) -> System.out.println(k + ": " + v));
                            System.out.println(" ");
                        }
                        // Exiting the sequence
                        else if (userAccOp.equals(6)) {
                            loggedIn = false;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }

                } else {
                    System.out.println("--------- Incorrect Credentials ---------");
                    System.out.println(" ");
                }

            }
//            Admin Interface Section
            else if (mainMenuOp.equalsIgnoreCase("admin") || mainMenuOp.equals("2")){
                System.out.println(" ");

                System.out.print("Enter Your Admin UserName: ");
                String adminName = input.nextLine();

                System.out.print("Enter Your Admin Password: ");
                String adminPass = input.nextLine();

                if (adminName.equals("admin") && adminPass.equals("admin") || adminName.equals("a") && adminPass.equals("a")) {
                    System.out.println(" ");
                    System.out.println("Welcome" + "* " + "ADMIN" + " *");

                    boolean loggedIn = true;

                    while (loggedIn){
                        // List of admin functionality section
                        System.out.print("------------------------------\n" +
                                "1. Register New Account \n" +
                                "2. Update Account Details \n" +
                                "3. Manage Accounts \n" +
                                "4. Handle User Data \n" +
                                "5. Logout \n" +
                                "------------------------------\n" +
                                "Enter your choice: ");
                        Integer adminOp = input.nextInt();

                        System.out.println(" ");

                        input.nextLine();

                        // Registering new account
                        if (adminOp.equals(1)) {
                            System.out.println("******* Registering New Account *******");

                            System.out.print("Enter New Account Number: ");
                            Integer newAccNo = input.nextInt();

                            System.out.print("Enter New Account PIN: ");
                            Integer newPin = input.nextInt();

                            System.out.print("Enter Initial Balance: ");
                            Integer newBalance = input.nextInt();

                            input.nextLine();

                            System.out.print("Enter User Name: ");
                            String newUserName = input.nextLine();

                            Account newAccount = new Account(newAccNo, newPin, newBalance, newUserName);
                            accounts.add(newAccount);

                            System.out.println("---- New Account Registered Successfully. ----");
                            System.out.println(" ");
                        }
                        // Updating Account Details
                        else if (adminOp.equals(2)) {
                            System.out.println("******* Updating Account Details *******");

                            System.out.print("Enter Account Number to Update: ");
                            Integer updateAccNo = input.nextInt();

                            Account accountToUpdate = null;

                            // Checking if the entered acc is valid                            
                            for (Account acc : accounts) {
                                if (acc.getAccountNumber().equals(updateAccNo)) {
                                    accountToUpdate = acc;
                                    break;
                                }
                            }

                            // If the acc is valid and not empty
                            if (accountToUpdate != null) {

                                System.out.print("Enter New PIN: ");
                                Integer newPin = input.nextInt();

                                System.out.print("Enter New balance: ");
                                Integer newBalance = input.nextInt();

                                input.nextLine();

                                System.out.print("Enter New User Name: ");
                                String newUserName = input.nextLine();

                                accountToUpdate.setPin(newPin);
                                accountToUpdate.setBalance(newBalance);
                                accountToUpdate.setUserName(newUserName);

                                System.out.println("---- Successfully Updated ----");
                                System.out.println(" ");
                            } else {
                                System.out.println("---- Account not Found. ----");
                                System.out.println(" ");
                            }
                        }
                        // Managing  Account
                        else if (adminOp.equals(3)){
                            System.out.println("******* Managing  Account ******");

                            System.out.println("------------------------------");
                            System.out.println("1. Existing Account");
                            System.out.println("2. View Account Details");
                            System.out.println("3. Delete Account");
                            System.out.println("------------------------------");
                            System.out.print("Enter your choice: ");
                            String manageAccOp = input.nextLine();

                            // Accounting checking 
                            if (manageAccOp.equals("1")){
                                System.out.println(" ");
                                System.out.println("******* Account Validation ******");
                                System.out.print("Enter Account to Check: ");
                                Integer manageAccNo = input.nextInt();

                                Account manageAcc = null;

                                // Checking if the entered acc is valid
                                for (Account acc : accounts) {
                                    if (acc.getAccountNumber().equals(manageAccNo)) {
                                        manageAcc = acc;
                                        break;
                                    }
                                }

                                // If the acc is valid
                                if (manageAcc != null) {
                                    System.out.println("---- Account Is Valid! ----");
                                    System.out.println(" ");
                                } else {
                                    System.out.println("---- Account not Found. ----");
                                    System.out.println(" ");
                                }
                            }
                            // View Account Details 
                            else if (manageAccOp.equals("2")){
                                System.out.println(" ");
                                System.out.println("******* Account Details ******");
                                System.out.print("Enter Account to View: ");
                                Integer manageAccNo = input.nextInt();

                                Account manageAcc = null;

                                // Checking if the entered acc is valid
                                for (Account acc : accounts) {
                                    if (acc.getAccountNumber().equals(manageAccNo)) {
                                        manageAcc = acc;
                                        break;
                                    }
                                }

                                // If the acc is valid and not empty
                                if (manageAcc != null) {

                                    System.out.println("---- Account Name: "+manageAcc.getUserName()+" ----");
                                    System.out.println("---- Account PIN: "+manageAcc.getPin()+" ----");
                                    System.out.println("---- Account Balance: "+manageAcc.getBalance()+" ----");

                                    System.out.println(" ");
                                } else {
                                    System.out.println("---- Account not Found. ----");
                                    System.out.println(" ");
                                }
                            }
                            // Delete Account 
                            else if (manageAccOp.equals("3")){
                                System.out.println(" ");
                                System.out.println("******* Delete Account ******");

                                System.out.print("Enter Account Number to Delete: ");
                                Integer deleteAccNo = input.nextInt();

                                Account accountToDelete = null;
                                for (Account acc : accounts) {
                                    if (acc.getAccountNumber().equals(deleteAccNo)) {
                                        accountToDelete = acc;
                                        break;
                                    }
                                }

                                if (accountToDelete != null) {
                                    accounts.remove(accountToDelete);
                                    System.out.println("---- Account deleted successfully. ----");
                                    System.out.println(" ");
                                } else {
                                    System.out.println("---- Account not found. ----");
                                    System.out.println(" ");
                                }
                            }
                        }
                        // Handling User Data
                        else if (adminOp.equals(4)){
                            System.out.println(" ");
                            System.out.println("******* Handling User Data ******");

                            System.out.printf("%-15s | %-5s | %-10s | %-10s\n", "Account Number", "PIN", "Balance", "User Name");
                            System.out.println("------------------------------------------------------");
                            for (Account acc : accounts) {
                                System.out.printf("%-15s | %-5s | %-10s | %-10s\n", acc.getAccountNumber(), acc.getPin(), "$" + acc.getBalance(), acc.getUserName());
                            }
                            System.out.println(" ");
                        }
                        // Exiting the sequence
                        else if (adminOp.equals(5)) {
                            loggedIn = false;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                } else{
                    System.out.println("---- Invalid Credentials ----");
                    System.out.println(" ");
                }

            }
//            Exit the program
            else if (mainMenuOp.equalsIgnoreCase("exit") || mainMenuOp.equals("3")) {
                System.out.println("Thank you for Banking with us...");
                System.exit(0);
            } else {
                System.out.println("Invalid Option. Please try Again.");
            }
        }
    }
}
