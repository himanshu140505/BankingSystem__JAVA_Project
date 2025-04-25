package BankingSystem__JAVA_Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L; // Add serialVersionUID for serialization
    private String accountNumber;
    private String accountHolderName;
    private String pin;
    private double balance;
    private double initialBalance; // Store the initial balance
    private List<String> transactionHistory = new ArrayList<>();

    public Account(String accountNumber, String accountHolderName, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.balance = initialBalance;
        this.initialBalance = initialBalance; // Initialize the initial balance
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            return true;
        }
        return false;
    }

    public boolean checkPin(String inputPin) {
        return pin.equals(inputPin);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
