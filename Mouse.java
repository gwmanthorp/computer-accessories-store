public class Mouse extends Product {

    // Instance variables specific to Mouse class
    private int noOfButtons;
    private DeviceType mouseType;

    // Constructor to initialize Mouse object
    public Mouse(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock,
                 double originalCost, double retailPrice, ProductCategory category, int noOfButtons, DeviceType mouseType) {
        // Call to superclass constructor (Product) using super keyword
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        
        // Initialize Mouse-specific fields
        this.noOfButtons = noOfButtons;
        this.mouseType = mouseType;
    }

    // Overridden toString method to customize string representation of Mouse object
    // For outputting to the user in a list 
    @Override
    public String toString() {
        // Constructing the formatted output string
        String output = Integer.toString(getBarcode()) + " - £" + Double.toString(getRetailPrice()) + " - " +
                getBrand() + " " + capitalize(mouseType.name()) + " " + capitalize(getCategory().name()) +
                " (" + capitalize(getColor()) + ", " + capitalize(getConnectivity().name()) + ", " +
                Integer.toString(noOfButtons) + " buttons" + ") - " + "Qty left in stock: " +
                Integer.toString(getQuantityInStock());
        return output;
    }

    // Utility method to capitalize the first letter of a string
    public String capitalize(String input) {
        input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        return input;
    }

    // Getter method for retrieving the number of buttons on the mouse
    public int getNoOfButtons() {
        return noOfButtons;
    }

    // Method to generate a string in a specific format for exporting to a text file
    // Would be better to add to Product class but constrained by requirements
    public String textFileFormat() {
        return Integer.toString(getBarcode()) + ", " + getCategory().name().toLowerCase() + ", " +
                mouseType.name().toLowerCase() + ", " + getBrand() + ", " + getColor() + ", " +
                getConnectivity().name().toLowerCase() + ", " + Integer.toString(getQuantityInStock()) +
                ", " + Double.toString(getOriginalCost()) + ", " + Double.toString(getRetailPrice()) +
                ", " + Integer.toString(noOfButtons);
    }
}
