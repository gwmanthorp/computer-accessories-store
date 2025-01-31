import java.text.DecimalFormat;
import java.time.LocalDate;

// Receipt class represents a receipt for a transaction with payment details and delivery address
public class Receipt {
    private double amount;         // Total amount of the transaction
    private Address fullAddress;   // Address object representing the delivery address
    private int cardNumber;        // Credit card number used for payment
    private String email;          // Email used for PayPal payment
    DecimalFormat df = new DecimalFormat("#.##"); // Decimal formatter for amount display

    // Constructor for credit card payment
    public Receipt(double amount, Address fullAddress, int cardNumber) {
        this.amount = amount;
        this.fullAddress = fullAddress;
        this.cardNumber = cardNumber;
    }

    // Constructor for PayPal payment
    public Receipt(double amount, Address fullAddress, String email) {
        this.amount = amount;
        this.fullAddress = fullAddress;
        this.email = email;
    }

    // Generate string representation of payment details for PayPal transactions
    public String paypalToString() {
        return "£" + df.format(amount) + " paid by PayPal using " + this.email + " on " + LocalDate.now() + ", and the delivery address is " + fullAddress.toString();
    }

    // Generate string representation of payment details for credit card transactions
    public String cardToString() {
        return "£" + df.format(amount) + " paid by Credit Card using " + Integer.toString(cardNumber) + " on " + LocalDate.now() + ", and the delivery address is " + fullAddress.toString();
    }
}
