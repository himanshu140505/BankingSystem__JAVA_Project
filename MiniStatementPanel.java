package BankingSystem__JAVA_Project;

import java.awt.*;
import java.util.List;

public class MiniStatementPanel extends Panel {
    private BankingApplet applet;
    private TextArea statementArea;
    private Button backButton;

    public MiniStatementPanel(BankingApplet applet) {
        this.applet = applet;

        setLayout(new BorderLayout());

        statementArea = new TextArea();
        statementArea.setEditable(false);
        add(statementArea, BorderLayout.CENTER);

        backButton = new Button("Back");
        backButton.addActionListener(e -> applet.showPanel("Account"));
        add(backButton, BorderLayout.SOUTH);
    }

    public void displayStatement(String accountHolderName, String accountNumber, List<String> transactions, double initialBalance, double finalBalance) {
        statementArea.setText(""); // Clear previous statement
        statementArea.append("====================================\n");
        statementArea.append("               MINI-STATEMENT\n");
        statementArea.append("====================================\n");
        statementArea.append(String.format("Account Holder Name: %s\n", accountHolderName));
        statementArea.append(String.format("Account Number:      %s\n", accountNumber));
        statementArea.append("====================================\n");
        statementArea.append(String.format("Initial Balance:     %.2f\n", initialBalance));
        statementArea.append(String.format("Final Balance:       %.2f\n", finalBalance));
        statementArea.append("====================================\n");
        statementArea.append("Last 10 Transactions:\n");

        int start = Math.max(0, transactions.size() - 10); // Get the last 10 transactions
        for (int i = start; i < transactions.size(); i++) {
            statementArea.append(String.format("%d. %s\n", i - start + 1, transactions.get(i)));
        }
        statementArea.append("====================================\n");
    }
}
