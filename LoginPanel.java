package BankingSystem_JAVA_Project;

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

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new Label("Account Number:"));
        accountNumberField = new TextField();
        add(accountNumberField);

        add(new Label("PIN (4 digits):"));
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

        // Fill remaining cell in layout if needed
        add(new Label(""));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String accountNumberStr = accountNumberField.getText().trim();
            String pin = pinField.getText().trim();

            if (accountNumberStr.isEmpty() || pin.isEmpty()) {
                messageLabel.setText("Please enter both fields.");
                return;
            }

            int accountNumber;
            try {
                accountNumber = Integer.parseInt(accountNumberStr);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid account number.");
                return;
            }

            Account account = bank.getAccount(accountNumber);

            if (account != null && account.checkPin(pin)) {
                applet.getAccountPanel().setAccount(account); // Set current account
                applet.switchTo("account");                   // Show account panel
            } else {
                messageLabel.setText("Invalid account number or PIN.");
            }
        } else if (e.getSource() == backButton) {
            applet.switchTo("createAccount");
        }
    }
}
