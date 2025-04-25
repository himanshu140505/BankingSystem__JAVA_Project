package BankingSystem__JAVA_Project;

import BankingSystem__JAVA_Project.BankingApplet;
import BankingSystem__JAVA_Project.Bank;

import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends Panel implements ActionListener {
    private TextField accountNumberField, pinField;
    private Button loginButton, backButton;
    private Label messageLabel;
    private BankingApplet applet;
    private Bank bank;

    public LoginPanel(BankingApplet applet, Bank bank) {
        this.applet = applet;
        this.bank = bank;

        setLayout(new GridLayout(4, 2));

        Label accountLabel = new Label("Account Number:");
        add(accountLabel);

        accountNumberField = new TextField();
        add(accountNumberField);

        Label pinLabel = new Label("PIN (4 digits):");
        add(pinLabel);

        pinField = new TextField();
        pinField.setEchoChar('*');
        add(pinField);

        loginButton = new Button("Login");
        loginButton.addActionListener(this);
        add(loginButton);

        backButton = new Button("Back");
        backButton.addActionListener(this);
        add(backButton);

        messageLabel = new Label("");
        messageLabel.setForeground(Color.RED);
        add(messageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String accountNumber = accountNumberField.getText().trim();
            String pin = pinField.getText().trim();

            if (accountNumber.isEmpty() || pin.isEmpty()) {
                messageLabel.setText("Please enter both fields.");
                return;
            }

            Account account = bank.getAccount(accountNumber);

            if (account != null && account.checkPin(pin)) {
                applet.getAccountPanel().setAccount(account);
                applet.showPanel("Account"); // Transition to Account panel after login
            } else {
                messageLabel.setText("Invalid account number or PIN.");
            }
        } else if (e.getSource() == backButton) {
            applet.showPanel("Login");
        }
    }
}
