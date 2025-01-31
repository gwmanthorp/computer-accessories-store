// Abstract base class for all products
public abstract class Product {
    // Attributes of a product
    private int barcode;
    private String brand;
    private String color;
    private ConnectivityType connectivity;
    private int quantityInStock;
    private double originalCost;
    private double retailPrice;
    private ProductCategory category;

    // Constructor to initialize a product
    public Product(int barcode, String brand, String color, ConnectivityType connectivity, 
                   int quantityInStock, double originalCost, double retailPrice, ProductCategory category) {
        // Assign values to attributes
        this.barcode = barcode;
        this.brand = brand;
        this.color = color;
        this.connectivity = connectivity;
        this.quantityInStock = quantityInStock;
        this.originalCost = originalCost;
        this.retailPrice = retailPrice;
        this.category = category;
    }

    // Getter methods for accessing attributes
    public int getBarcode() {
        return this.barcode;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getColor() {
        return this.color;
    }

    public ConnectivityType getConnectivity() {
        return this.connectivity;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public double getOriginalCost() {
        return this.originalCost;
    }

    public double getRetailPrice() {
        return this.retailPrice;
    }

    public ProductCategory getCategory() {
        return this.category;
    }

    // Setter method to update quantity in stock
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    // Abstract method to be implemented by subclasses
    public abstract String toString();
}
