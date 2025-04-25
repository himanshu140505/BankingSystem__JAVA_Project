package BankingSystem__JAVA_Project;

public class Utils {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber.matches("\\d+");
    }

    public static boolean isValidPin(String str) {
        return str.length() == 4 && isNumeric(str);
    }

    public static String formatCurrency(double amount) {
        return String.format("%,.2f", amount);
    }

    public static boolean isValidAmount(String amount) {
        try {
            double value = Double.parseDouble(amount);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
