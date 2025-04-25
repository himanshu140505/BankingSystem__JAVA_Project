package BankingSystem_JAVA_Project;

import java.util.*;

public class Bank {
    private List<Account> accounts;

    public Bank() {
        // Load accounts from persistent storage on startup
        accounts = DataStorage.loadAccounts();
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
    }

    // Save all accounts to persistent storage
    public void saveAccounts() {
        DataStorage.saveAccounts(accounts);
    }

    // Retrieve an account by account number
    public Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    // Create a new account with name, pin, and initial deposit
    public int createAccount(String name, String pin, double initialDeposit) {
        if (initialDeposit < 0) return -1;

        int newAccountNumber = generateNewAccountNumber();
        Account newAccount = new Account(newAccountNumber, name, pin, initialDeposit);
        accounts.add(newAccount);
        saveAccounts();
        return newAccountNumber;
    }

    // Deposit money into an account
    public boolean deposit(int accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
            saveAccounts();
            return true;
        }
        return false;
    }

    // Withdraw money from an account
    public boolean withdraw(int accountNumber, double amount) {
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

    // Generate a new unique account number
    private int generateNewAccountNumber() {
        int maxAccountNumber = 1000;
        for (Account account : accounts) {
            if (account.getAccountNumber() > maxAccountNumber) {
                maxAccountNumber = account.getAccountNumber();
            }
        }
        return maxAccountNumber + 1;
    }
}
