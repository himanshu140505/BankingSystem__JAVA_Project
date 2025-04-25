package BankingSystem_JAVA_Project;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BankingApplet extends Applet {
    private CardLayout cardLayout;
    private JPanel cards;
    private Bank bank;
    private AccountPanel accountPanel;
    private LoginPanel loginPanel;
    private CreateAccountPanel createAccountPanel;

    @Override
    public void init() {
        // Initialize the bank object (loading accounts from the file)
        bank = new Bank();
        
        // Set up the layout manager to switch between panels
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Initialize the panels
        loginPanel = new LoginPanel(this);
        accountPanel = new AccountPanel(this);
        createAccountPanel = new CreateAccountPanel(this);

        // Add panels to the cards container
        cards.add(loginPanel, "login");
        cards.add(accountPanel, "account");
        cards.add(createAccountPanel, "createAccount");

        // Set the initial card to the login screen
        cardLayout.show(cards, "login");

        // Add the cards panel to the Applet
        setLayout(new BorderLayout());
        add(cards, BorderLayout.CENTER);
    }

    // Switch to the specified panel
    public void switchTo(String panelName) {
        cardLayout.show(cards, panelName);
    }

    // Set the current account on the AccountPanel (after login)
    public void setAccountPanel(Account account) {
        accountPanel.setAccount(account);
    }

    // Get the bank instance
    public Bank getBank() {
        return bank;
    }

    // Handling user login (from LoginPanel)
    public void handleLogin(int accountNumber, String pin) {
        Account account = bank.getAccount(accountNumber);
        if (account != null && account.checkPin(pin)) {
            setAccountPanel(account);
            switchTo("account");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid account number or PIN!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle account creation (from CreateAccountPanel)
    public void handleCreateAccount(String name, String pin) {
        if (Utils.isValidName(name) && Utils.isValidPin(pin)) {
            boolean success = bank.createAccount(name, pin);
            if (success) {
                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                switchTo("login");
            } else {
                JOptionPane.showMessageDialog(this, "Error creating account.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check name and pin.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Custom painting code for Applet (optional)
    }

    // Handle deposit action (called from AccountPanel)
    public void handleDeposit(double amount) {
        if (Utils.isValidAmount(amount)) {
            boolean success = bank.deposit(accountPanel.getAccount().getAccountNumber(), amount);
            if (success) {
                JOptionPane.showMessageDialog(this, "Deposit successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Deposit failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Handle withdrawal action (called from AccountPanel)
    public void handleWithdraw(double amount) {
        if (Utils.isValidAmount(amount)) {
            boolean success = bank.withdraw(accountPanel.getAccount().getAccountNumber(), amount);
            if (success) {
                JOptionPane.showMessageDialog(this, "Withdrawal successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds or invalid withdrawal.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
