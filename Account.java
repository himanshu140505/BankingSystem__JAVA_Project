package BankingSystem_JAVA_Project;

public class Account {
    private final int accountNumber;
    private String name;
    private String pin;
    private double balance;

    public Account(int accountNumber, String name, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = Math.max(0, balance); // Ensure balance is non-negative
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName.trim();
        }
    }

    public boolean checkPin(String enteredPin) {
        return pin != null && pin.equals(enteredPin);
    }

    public void setPin(String newPin) {
        if (newPin != null && newPin.length() >= 4) {
            this.pin = newPin;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccountNumber=" + accountNumber +
                ", Name='" + name + '\'' +
                ", Balance=₹" + String.format("%.2f", balance) +
                '}';
    }
}
