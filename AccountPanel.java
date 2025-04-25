package BankingSystem_JAVA_Project;
import java.awt.*;
import java.awt.event.*;

public class AccountPanel extends Panel implements ActionListener {
    private Label welcomeLabel, balanceLabel, messageLabel;
    private TextField amountField;
    private Button depositBtn, withdrawBtn, logoutBtn;

    private BankingApplet applet;
    private Bank bank;
    private Account account;

    public AccountPanel(BankingApplet applet, Bank bank) {
        this.applet = applet;
        this.bank = bank;

        setLayout(new GridLayout(6, 1, 10, 10));

        welcomeLabel = new Label("Welcome!");
        add(welcomeLabel);

        balanceLabel = new Label("Balance: ₹0.00");
        add(balanceLabel);

        amountField = new TextField();
        amountField.setPlaceholder("Enter amount");
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

    // Called by applet after login to set the current account
    public void setAccount(Account acc) {
        this.account = acc;
        welcomeLabel.setText("Welcome, " + acc.getName());
        updateBalance();
        messageLabel.setText("");
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: ₹" + account.getBalance());
    }

    public void actionPerformed(ActionEvent e) {
        String amtText = amountField.getText().trim();
        double amount;

        if (e.getSource() == logoutBtn) {
            applet.switchTo("login");
            return;
        }

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

        if (e.getSource() == depositBtn) {
            account.deposit(amount);
            messageLabel.setText("Deposited ₹" + amount);
        } else if (e.getSource() == withdrawBtn) {
            if (account.withdraw(amount)) {
                messageLabel.setText("Withdrawn ₹" + amount);
            } else {
                messageLabel.setText("Not enough balance.");
            }
        }

        updateBalance();
        amountField.setText("");
    }
}
