package BankingSystem__JAVA_Project;

import BankingSystem__JAVA_Project.BankingApplet;
import BankingSystem__JAVA_Project.Bank;
import BankingSystem__JAVA_Project.Utils;

import java.awt.*;
import java.awt.event.*;

public class CreateAccountPanel extends Panel implements ActionListener {

    private TextField nameField, pinField, depositField;
    private Button createBtn, backBtn;
    private Label msgLabel;
    private BankingApplet applet;
    private Bank bank;

    public CreateAccountPanel(BankingApplet applet, Bank bank) {
        this.applet = applet;
        this.bank = bank;

        setLayout(new GridLayout(6, 2, 10, 10));

        add(new Label("Name:"));
        nameField = new TextField();
        add(nameField);

        add(new Label("PIN (4 digits):"));
        pinField = new TextField();
        pinField.setEchoChar('*');
        add(pinField);

        add(new Label("Initial Deposit:"));
        depositField = new TextField();
        add(depositField);

        createBtn = new Button("Create Account");
        createBtn.addActionListener(this);
        add(createBtn);

        backBtn = new Button("Back");
        backBtn.addActionListener(this);
        add(backBtn);

        msgLabel = new Label("");
        msgLabel.setForeground(Color.RED);
        add(msgLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createBtn) {
            String name = nameField.getText().trim();
            String pin = pinField.getText().trim();
            String depositStr = depositField.getText().trim();

            if (name.isEmpty() || pin.isEmpty() || depositStr.isEmpty()) {
                msgLabel.setText("Fill all fields.");
                return;
            }

            if (!Utils.isValidName(name)) {
                msgLabel.setText("Invalid name.");
                return;
            }

            if (!Utils.isValidPin(pin)) {
                msgLabel.setText("PIN must be 4 digits.");
                return;
            }

            double deposit;
            try {
                deposit = Double.parseDouble(depositStr);
                if (!Utils.isValidAmount(depositStr)) { // Pass depositStr (String) instead of deposit (double)
                    msgLabel.setText("Deposit must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException ex) {
                msgLabel.setText("Invalid deposit amount.");
                return;
            }

            String accNo = bank.createAccount(name, pin, deposit);
            msgLabel.setForeground(Color.BLUE);
            msgLabel.setText("Account created! ID: " + accNo);

            // Clear fields after creation
            nameField.setText("");
            pinField.setText("");
            depositField.setText("");
        }
        if (e.getSource() == backBtn) {
            applet.showPanel("Login"); // Replace switchTo with showPanel
        }
    }
}
