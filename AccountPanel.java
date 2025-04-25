package BankingSystem__JAVA_Project;

import BankingSystem__JAVA_Project.BankingApplet;
import BankingSystem__JAVA_Project.Bank;
import BankingSystem__JAVA_Project.Account;

import java.awt.*;
import java.awt.event.*;

public class AccountPanel extends Panel {
    private BankingApplet applet;
    private Bank bank;
    private Label balanceLabel;
    private TextField amountField;
    private Account currentAccount;

    public AccountPanel(BankingApplet applet, Bank bank) {
        this.applet = applet;
        this.bank = bank;

        setLayout(new GridLayout(4, 2));

        balanceLabel = new Label("Balance: ");
        add(balanceLabel);

        amountField = new TextField();
        add(amountField);

        Button depositButton = new Button("Deposit");
        depositButton.addActionListener(e -> deposit());
        add(depositButton);

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.addActionListener(e -> withdraw());
        add(withdrawButton);

        Button logoutButton = new Button("Logout");
        logoutButton.addActionListener(e -> applet.showPanel("Login"));
        add(logoutButton);
    }

    public void setAccount(Account account) {
        this.currentAccount = account;
        refreshBalance();
    }

    public Account getAccount() {
        return currentAccount;
    }

    public void refreshBalance() {
        if (currentAccount != null) {
            balanceLabel.setText("Balance: " + currentAccount.getBalance());
        }
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                currentAccount.deposit(amount);
                refreshBalance();
            } else {
                System.out.println("Amount must be greater than 0.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid amount entered.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                if (currentAccount.withdraw(amount)) {
                    refreshBalance();
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Amount must be greater than 0.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid amount entered.");
        }
    }
}
