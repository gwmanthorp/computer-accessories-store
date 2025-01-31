
public interface PaymentMethod {
	// Interface to be implemented by the paypal and card payment classes
	Receipt processPayment(double amount, Address fullAddress);
}