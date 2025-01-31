public class Keyboard extends Product {

    // Properties specific to Keyboard
    private KeyboardLayout keyboardLayout;
    private DeviceType keyboardType;

    // Constructor to initialize a Keyboard object
    public Keyboard(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock,
                    double originalCost, double retailPrice, ProductCategory category, KeyboardLayout keyboardLayout,
                    DeviceType keyboardType) {
        // Call superclass (Product) constructor to initialize inherited properties
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        
        // Initialize Keyboard-specific properties
        this.keyboardLayout = keyboardLayout;
        this.keyboardType = keyboardType;
    }

    // Override toString method to provide a custom string representation of Keyboard
    // This is for adding to the list for the output to the user 
    @Override
    public String toString() {
        // Construct a string representation of the Keyboard object
        String output = Integer.toString(getBarcode()) + " - £" + Double.toString(getRetailPrice()) + " - " +
                getBrand() + " " + capitalize(keyboardType.name()) + " " + capitalize(getCategory().name()) + " (" +
                capitalize(getColor()) + ", " + capitalize(getConnectivity().name()) + ", " + keyboardLayout.name() +
                ") - " + "Qty left in stock: " + Integer.toString(getQuantityInStock());
        return output;
    }

    // Utility method to capitalize the first letter of a string
    public String capitalize(String input) {
        input = input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
        return input;
    }

    // Method to format Keyboard data for writing to a text file
    // Would be better to add to Product class but constrained by requirements
    public String textFileFormat() {
        return Integer.toString(getBarcode()) + ", " + getCategory().name().toLowerCase() + ", " +
                keyboardType.name().toLowerCase() + ", " + getBrand() + ", " + getColor() + ", " +
                getConnectivity().name().toLowerCase() + ", " + Integer.toString(getQuantityInStock()) + ", " +
                Double.toString(getOriginalCost()) + ", " + Double.toString(getRetailPrice()) + ", " +
                keyboardLayout.name();
    }
}
