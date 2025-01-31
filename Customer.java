import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

// Customer class inherits from User
public class Customer extends User {
    // DefaultListModel to store products in the basket
    private DefaultListModel<Product> basket;

    // Constructor for Customer class
    public Customer(int userID, String username, String name, Address address, UserRole role) {
        // Call to parent class constructor (User)
        super(userID, username, name, address, role);
        // Initialize basket as DefaultListModel of Products
        this.basket = new DefaultListModel<>();
    }

    // Add a product to the basket
    public void addToBasket(Product product) {
        this.basket.addElement(product);
    }

    // Remove a product from the basket
    public void removeFromBasket(Product product) {
        this.basket.removeElement(product);
    }

    // Clear all products from the basket
    public void clearBasket() {
        this.basket.clear();
    }

    // Get the basket of products
    public DefaultListModel<Product> getBasket() {
        return basket;
    }

    // Calculate the total cost of products in the basket
    public double getAmount() {
        double amount = 0;
        for (int i = 0; i < basket.getSize(); i++) {
            Product product = basket.getElementAt(i); // Get each product in the basket
            amount += product.getRetailPrice(); // Add retail price of each product to amount
        }
        return amount; // Return total amount
    }

    // Check if adding more of a specific product is allowed based on current stock
    public boolean maxAdded(Product product) {
        int currentQuantityAvailableToAddToBasket = product.getQuantityInStock();

        // Check each product in the basket
        for (int i = 0; i < basket.getSize(); i++) {
            Product productTemp = basket.getElementAt(i);
            // If product already exists in basket, reduce available quantity
            if (product.equals(productTemp)) {
                currentQuantityAvailableToAddToBasket -= 1;
            }
        }
        // Return true if more can be added to the basket
        if (currentQuantityAvailableToAddToBasket > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Check if all items in the basket are available in sufficient quantity
    public boolean allItemsAvailable() {
        for (int i = 0; i < basket.getSize(); i++) {
            Product product = basket.getElementAt(i);
            int count = countOccurrences(product); // Count occurrences of each product
            // If stock is insufficient for any product, show a warning message
            if (product.getQuantityInStock() < count) {
                JOptionPane.showMessageDialog(null, "Product - " + product.getBarcode() + " - Not enough in stock to allow for your purchase", "Stock Issue", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true; // All items are available in sufficient quantity
    }

    // Count occurrences of a specific product in the basket
    public int countOccurrences(Product product) {
        int count = 0;
        int size = basket.getSize();

        // Count occurrences by comparing each product in the basket
        for (int i = 0; i < size; i++) {
            Product productTemp = basket.getElementAt(i);
            if (product.equals(productTemp)) {
                count++;
            }
        }

        return count; // Return the count of occurrences
    }
}
