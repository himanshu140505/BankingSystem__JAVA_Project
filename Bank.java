package BankingSystem_JAVA_Project;
import java.util.*;

public class Bank {
    private List<Account> accounts;

    public Bank() {
        // Load accounts from file upon initialization
        accounts = DataStorage.loadAccounts();
    }

    // Save all accounts to the file
    public void saveAccounts() {
        DataStorage.saveAccounts(accounts);
    }

    // Get an account by account number
    public Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null; // No account found
    }

    // Create a new account
    public boolean createAccount(String name, String pin) {
        int newAccountNumber = generateNewAccountNumber();
        Account newAccount = new Account(newAccountNumber, name, pin, 0.0); // initial balance = 0
        accounts.add(newAccount);
        saveAccounts(); // Save after adding a new account
        return true;
    }

    // Deposit money into an account
    public boolean deposit(int accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
            saveAccounts(); // Save the updated account info
            return true;
        }
        return false; // Invalid account or deposit amount
    }

    // Withdraw money from an account
    public boolean withdraw(int accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            return account.withdraw(amount); // withdraw from account and return success status
        }
        return false; // Invalid account or withdrawal amount
    }

    // Helper method to generate a new unique account number (just an example)
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
