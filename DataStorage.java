package BankingSystem__JAVA_Project;

import java.io.*;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class DataStorage {

    private static final String FILE_NAME = "accounts.txt";

    // Save all accounts to file
    public static void saveAccounts(HashMap<String, Account> accounts, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(accounts);
        }
    }

    // Load all accounts from file
    public static HashMap<String, Account> loadAccounts(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (HashMap<String, Account>) ois.readObject();
        }
    }

    // Find account by account number
    public static Account getAccount(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
