// Define a class named PayPal that implements the PaymentMethod interface
public class PayPal implements PaymentMethod {
    
    // Private instance variables for PayPal class
    private String email;      // Email associated with the PayPal account
    private Receipt receipt;   // Receipt generated after processing payment
    
    // Constructor for initializing a PayPal object with specified email, amount, and address
    public PayPal(String email, double amount, Address fullAddress) {
        this.email = email;   // Set the email provided during object creation
        
        // Process the payment and generate a receipt
        this.receipt = processPayment(amount, fullAddress);
    }
    
    // Method to process a payment and generate a receipt
    @Override
    public Receipt processPayment(double amount, Address fullAddress) {
        // Create a new Receipt object with the given amount, address, and PayPal email
        Receipt receipt = new Receipt(amount, fullAddress, this.email);
        return receipt;   // Return the generated receipt
    }
    
    // Getter method to retrieve the receipt associated with this PayPal transaction
    public Receipt getReceipt() {
        return receipt;   // Return the receipt generated for this transaction
    }
}
