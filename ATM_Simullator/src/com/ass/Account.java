package com.ass;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private String userName;
    private Integer accountNumber;
    private Integer pin;
    private Integer balance;
    private Map<String, String> transactionHistory;

    public Account(Integer accountNumber, Integer pin, Integer balance, String userName) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.userName = userName;
        this.transactionHistory = new HashMap<>();
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Integer getPin() {
        return pin;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getUserName(){
        return userName;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void deposit(Integer amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.put("Deposit", "$" + amount);
        }
    }

    public boolean withdraw(Integer amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.put("Withdraw", "$" + amount);
            return true;
        }
        return false;
    }

    public void transfer(Account toAccount, Integer amount) {
        if (this.withdraw(amount)) {
            toAccount.deposit(amount);
            transactionHistory.put("Transfer", "To " + toAccount.getAccountNumber() + ": $" + amount);
        }
    }

    public Map<String, String> getTransactionHistory() {
        return transactionHistory;
    }
}
