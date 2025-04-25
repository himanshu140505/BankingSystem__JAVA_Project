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

    public AccountPanel getAccountPanel() {
        return accountPanel;
    }
}
