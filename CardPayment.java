
// CardPayment class implements the PaymentMethod interface
public class CardPayment implements PaymentMethod {
    // Instance variables
    private int cardNum; // Card number
    @SuppressWarnings("unused")
	private int securityCode; // Security code
    private Receipt receipt; // Receipt object to store payment details

    // Constructor to initialize a CardPayment object
    public CardPayment(String cardNum, String securityCode, double amount, Address fullAddress) {
        // Convert cardNum and securityCode from String to int
        this.cardNum = Integer.parseInt(cardNum);
        this.securityCode = Integer.parseInt(securityCode);
        // Process the payment and generate a receipt
        this.receipt = processPayment(amount, fullAddress);
    }

    // Override the processPayment method from the PaymentMethod interface
    @Override
    public Receipt processPayment(double amount, Address fullAddress) {
        // Create a new Receipt object with the specified amount, address, and card number
        Receipt receipt = new Receipt(amount, fullAddress, this.cardNum);
        return receipt; // Return the created receipt
    }

    // Getter method to retrieve the receipt generated from the payment
    public Receipt getReceipt() {
        return receipt; // Return the receipt associated with this payment
    }
}
