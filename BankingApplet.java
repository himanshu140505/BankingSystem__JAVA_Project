package BankingSystem_JAVA_Project;

import java.applet.Applet;
import java.awt.*;
import javax.swing.JOptionPane;

public class BankingApplet extends Applet {
    private CardLayout cardLayout;
    private Panel mainPanel;
    private LoginPanel loginPanel;
    private CreateAccountPanel createAccountPanel;
    private AccountPanel accountPanel;
    private Bank bank;

    public void init() {
        bank = new Bank();
        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        loginPanel = new LoginPanel(this, bank);
        createAccountPanel = new CreateAccountPanel(this, bank);
        accountPanel = new AccountPanel(this, bank);

        mainPanel.add("Login", loginPanel);
        mainPanel.add("CreateAccount", createAccountPanel);
        mainPanel.add("Account", accountPanel);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        cardLayout.show(mainPanel, "Login");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
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
            showPanel("Account");
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
                showPanel("Login");
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

    public AccountPanel getAccountPanel() {
        return accountPanel;
    }
}
