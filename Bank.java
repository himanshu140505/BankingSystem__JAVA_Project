package BankingSystem__JAVA_Project;

import java.util.*;
import java.io.IOException;

public class Bank {
    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    // Save all accounts to persistent storage
    public void saveAccounts() {
        try {
            DataStorage.saveAccounts(accounts, "accounts.txt");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    // Load all accounts from persistent storage
    public void loadAccounts() {
        try {
            accounts = DataStorage.loadAccounts("accounts.txt");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    // Retrieve an account by account number
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Create a new account with name, pin, and initial deposit
    public String createAccount(String accountHolderName, String pin, double initialBalance) {
        String accountNumber = UUID.randomUUID().toString().substring(0, 8); // Generate unique account number
        accounts.put(accountNumber, new Account(accountNumber, accountHolderName, pin, initialBalance));
        saveAccounts();
        return accountNumber;
    }

    // Deposit money into an account
    public boolean deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
            saveAccounts();
            return true;
        }
        return false;
    }

    // Withdraw money from an account
    public boolean withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            boolean success = account.withdraw(amount);
            if (success) {
                saveAccounts();
            }
            return success;
        }
        return false;
    }
}
