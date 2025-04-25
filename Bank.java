package BankingSystem_JAVA_Project;

import java.util.*;

public class Bank {
    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    // Save all accounts to persistent storage
    public void saveAccounts() {
        DataStorage.saveAccounts(new ArrayList<>(accounts.values()));
    }

    // Retrieve an account by account number
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Create a new account with name, pin, and initial deposit
    public boolean createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, new Account(accountNumber, accountHolderName, initialBalance));
            return true;
        }
        return false;
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
