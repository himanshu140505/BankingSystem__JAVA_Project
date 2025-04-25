package BankingSystem_JAVA_Project;
import java.io.*;
import java.util.*;

public class DataStorage {

    private static final String FILE_NAME = "accounts.txt";

    // Save all accounts to file
    public static void saveAccounts(List<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account account : accounts) {
                writer.write(account.getAccountNumber() + "," + account.getName() + "," + account.getPin() + "," + account.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load all accounts from file
    public static List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int accountNumber = Integer.parseInt(parts[0]);
                String name = parts[1];
                String pin = parts[2];
                double balance = Double.parseDouble(parts[3]);

                Account account = new Account(accountNumber, name, pin, balance);
                accounts.add(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Find account by account number
    public static Account getAccount(List<Account> accounts, int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
