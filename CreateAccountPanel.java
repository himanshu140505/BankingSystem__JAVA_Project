package BankingSystem_JAVA_Project;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CreateAccountPanel extends Panel implements ActionListener {
    TextField nameField, pinField, depositField;
    Button createBtn, backBtn;
    Label msgLabel;
    BankingApplet applet;
    Bank bank;

    public CreateAccountPanel(BankingApplet applet, Bank bank) {
        this.applet = applet;
        this.bank = bank;

        setLayout(new GridLayout(6, 2));

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
        add(msgLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createBtn) {
            String name = nameField.getText();
            String pin = pinField.getText();
            String depositStr = depositField.getText();

            if (name.isEmpty() || pin.isEmpty() || depositStr.isEmpty()) {
                msgLabel.setText("Fill all fields.");
                return;
            }

            double deposit = 0;
            try {
                deposit = Double.parseDouble(depositStr);
            } catch (Exception ex) {
                msgLabel.setText("Invalid deposit.");
                return;
            }

            int accNo = bank.createAccount(name, pin, deposit);
            msgLabel.setText("Account created! ID: " + accNo);
        }

        if (e.getSource() == backBtn) {
            applet.switchTo("login");
        }
    }
}
