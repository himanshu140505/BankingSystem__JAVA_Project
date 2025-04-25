package BankingSystem_JAVA_Project;

import javax.swing.*;
import java.awt.*;

public class BankingApplet extends JApplet {

    private CardLayout cardLayout;
    private JPanel cards;
    private Bank bank;
    private AccountPanel accountPanel;
    private LoginPanel loginPanel;
    private CreateAccountPanel createAccountPanel;

    @Override
    public void init() {
        // Initialize the bank object (load accounts from file)
        bank = new Bank();

        // Initialize CardLayout and main card container
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Initialize panels and pass required references
        loginPanel = new LoginPanel(this, bank);
        accountPanel = new AccountPanel(this, bank);
        createAccountPanel = new CreateAccountPanel(this, bank);

        // Add panels to cards
        cards.add(loginPanel, "login");
        cards.add(accountPanel, "account");
        cards.add(createAccountPanel, "createAccount");

        // Set the layout and show login screen
        setLayout(new BorderLayout());
        add(cards, BorderLayout.CENTER);
        cardLayout.show(cards, "login");
    }

    // Switch to specific panel
    public void switchTo(String panelName) {
        cardLayout.show(cards, panelName);
    }

    // Set the current account in AccountPanel after login
    public void setAccountPanel(Account account) {
        accountPanel.setAccount(account);
    }

    public Bank getBank() {
        return bank;
    }

    // Handle login from LoginPanel
    public void handleLogin(int accountNumber, String pin) {
        Account account = bank.getAccount(accountNumber);
        if (account != null && account.checkPin(pin)) {
            setAccountPanel(account);
            switchTo("account");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid account number or PIN!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle account creation from CreateAccountPanel
    public void handleCreateAccount(String name, String pin, double initialDeposit) {
        if (Utils.isValidName(name) && Utils.isValidPin(pin) && Utils.isValidAmount(initialDeposit)) {
            int accountNumber = bank.createAccount(name, pin, initialDeposit);
            if (accountNumber != -1) {
                JOptionPane.showMessageDialog(this, "Account created! ID: " + accountNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
                switchTo("login");
            } else {
                JOptionPane.showMessageDialog(this, "Error creating account.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check name, PIN, and deposit.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle deposit from AccountPanel
    public void handleDeposit(double amount) {
        if (Utils.isValidAmount(amount)) {
            Account account = accountPanel.getAccount();
            if (bank.deposit(account.getAccountNumber(), amount)) {
                JOptionPane.showMessageDialog(this, "Deposit successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                accountPanel.refreshBalance();
            } else {
                JOptionPane.showMessageDialog(this, "Deposit failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Handle withdrawal from AccountPanel
    public void handleWithdraw(double amount) {
        if (Utils.isValidAmount(amount)) {
            Account account = accountPanel.getAccount();
            if (bank.withdraw(account.getAccountNumber(), amount)) {
                JOptionPane.showMessageDialog(this, "Withdrawal successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                accountPanel.refreshBalance();
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds or invalid withdrawal.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Optional: add custom UI paint if needed
    }
}
