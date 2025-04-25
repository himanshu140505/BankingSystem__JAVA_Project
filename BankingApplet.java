package BankingSystem__JAVA_Project;

import BankingSystem__JAVA_Project.LoginPanel;
import BankingSystem__JAVA_Project.CreateAccountPanel;
import BankingSystem__JAVA_Project.AccountPanel;
import BankingSystem__JAVA_Project.Bank;

import javax.swing.*;
import java.awt.*;

public class BankingApplet extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private CreateAccountPanel createAccountPanel;
    private AccountPanel accountPanel;
    private Bank bank;

    public BankingApplet() {
        bank = new Bank();
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this, bank);
        createAccountPanel = new CreateAccountPanel(this, bank);
        accountPanel = new AccountPanel(this, bank);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(createAccountPanel, "CreateAccount");
        mainPanel.add(accountPanel, "Account");

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        setTitle("Banking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        showPanel("Login");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public AccountPanel getAccountPanel() {
        return accountPanel;
    }

    public static void main(String[] args) {
        new BankingApplet();
    }
}
