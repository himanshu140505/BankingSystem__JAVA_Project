package BankingSystem-JAVA_Project;

public class Utils {

    // Validates if a string is a valid numeric value (for amounts or account numbers)
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);  // Try to parse as Double
            return true;
        } catch (NumberFormatException e) {
            return false;  // Not a valid number
        }
    }

    // Validates if a string is a valid account number format (e.g., numeric and 4 digits)
    public static boolean isValidAccountNumber(String str) {
        return str.length() == 4 && isNumeric(str);
    }

    // Validates if a string is a valid PIN format (e.g., numeric and 4 digits)
    public static boolean isValidPin(String str) {
        return str.length() == 4 && isNumeric(str);
    }

    // Formats a number into currency format (e.g., 1500 -> "1,500.00")
    public static String formatCurrency(double amount) {
        return String.format("%,.2f", amount);
    }

    // Checks if an amount is valid for deposit or withdrawal (positive and not zero)
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    // Validates a name to ensure it's not empty and contains only valid characters
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
