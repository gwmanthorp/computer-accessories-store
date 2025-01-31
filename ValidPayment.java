import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPayment {

    // Method to validate if the given email is in a valid format
    public boolean isValidEmail(String email) {
        // Regular expression to match valid email formats
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        
        // Compiling the pattern with the email regex
        Pattern pattern = Pattern.compile(emailRegex);
        
        // Creating a matcher object for the provided email string
        Matcher matcher = pattern.matcher(email);
        
        // Returning true if the email matches the regex pattern, otherwise false
        return matcher.matches();
    }
    
    // Method to validate card information based on length of card number and security code
    public boolean isValidCardInfo(String cardNum, String securityCode) {
        // Regex to check if string contains only digits
        String numericPattern = "^[0-9]+$";

        // Checking if card number is 6 digits, security code is 3 digits, and both are numeric
        if (cardNum.matches(numericPattern) && securityCode.matches(numericPattern) &&
            cardNum.length() == 6 && securityCode.length() == 3) {
            return true;  // Card information is valid
        } else {
            return false;  // Card information is invalid
        }
    }

}
