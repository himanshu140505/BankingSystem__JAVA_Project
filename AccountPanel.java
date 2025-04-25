package BankingSystem_JAVA_Project;

import java.awt.*;
import java.awt.event.*;

public class AccountPanel extends Panel implements ActionListener, FocusListener {
    private Label welcomeLabel, balanceLabel, messageLabel;
    private TextField amountField;
    private Button depositBtn, withdrawBtn, logoutBtn;

    private BankingApplet applet;
    private Account account;

    private final String PLACEHOLDER_TEXT = "Enter amount";
    private boolean isPlaceholderVisible = true;

    public AccountPanel(BankingApplet applet) {
        this.applet = applet;

        setLayout(new GridLayout(7, 1, 10, 10));

        welcomeLabel = new Label("Welcome!");
        add(welcomeLabel);

        balanceLabel = new Label("Balance: ₹0.00");
        add(balanceLabel);

        amountField = new TextField(PLACEHOLDER_TEXT);
        amountField.setForeground(Color.GRAY);
        amountField.addFocusListener(this);
        add(amountField);

        depositBtn = new Button("Deposit");
        withdrawBtn = new Button("Withdraw");
        logoutBtn = new Button("Logout");

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        logoutBtn.addActionListener(this);

        add(depositBtn);
        add(withdrawBtn);
        add(logoutBtn);

        messageLabel = new Label("");
        add(messageLabel);
    }

    // Called after login
    public void setAccount(Account acc) {
        this.account = acc;
        welcomeLabel.setText("Welcome, " + acc.getName());
        updateBalance();
        messageLabel.setText("");
        resetAmountField();
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: ₹" + String.format("%.2f", account.getBalance()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutBtn) {
            applet.switchTo("login");
            return;
        }

        String amtText = amountField.getText().trim();
        double amount;

        try {
            amount = Double.parseDouble(amtText);
        } catch (Exception ex) {
            messageLabel.setText("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            messageLabel.setText("Amount must be positive.");
            return;
        }

        boolean success = false;

        if (e.getSource() == depositBtn) {
            success = applet.getBank().deposit(account.getAccountNumber(), amount);
            messageLabel.setText(success ? "Deposited ₹" + amount : "Deposit failed.");
        } else if (e.getSource() == withdrawBtn) {
            success = applet.getBank().withdraw(account.getAccountNumber(), amount);
            messageLabel.setText(success ? "Withdrawn ₹" + amount : "Not enough balance.");
        }

        if (success) {
            account = applet.getBank().getAccount(account.getAccountNumber()); // refresh reference
            updateBalance();
        }

        resetAmountField();
    }

    // Placeholder handling
    @Override
    public void focusGained(FocusEvent e) {
        if (isPlaceholderVisible) {
            amountField.setText("");
            amountField.setForeground(Color.BLACK);
            isPlaceholderVisible = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (amountField.getText().isEmpty()) {
            resetAmountField();
        }
    }

    private void resetAmountField() {
        amountField.setText(PLACEHOLDER_TEXT);
        amountField.setForeground(Color.GRAY);
        isPlaceholderVisible = true;
    }
}
