package BankingSystem__JAVA_Project;

import java.awt.*;
import java.awt.event.*;

public class WelcomePanel extends Panel {
    private BankingApplet applet;

    public WelcomePanel(BankingApplet applet) {
        this.applet = applet;

        setLayout(new BorderLayout());

        // Add a header with the project name and owner names
        Label headerLabel = new Label("💰 Welcome to the Banking System 💰", Label.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        // Add buttons for Create Account and Login
        Panel buttonPanel = new Panel(new GridLayout(2, 1, 10, 10));
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 16));
        createAccountButton.addActionListener(e -> applet.showPanel("CreateAccount"));
        buttonPanel.add(createAccountButton);

        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> applet.showPanel("Login"));
        buttonPanel.add(loginButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add a footer with the project owner names
        Label footerLabel = new Label("© Himanshu & Anjali", Label.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        add(footerLabel, BorderLayout.SOUTH);
    }
}
