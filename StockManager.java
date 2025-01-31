import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class StockManager {
    // Model to hold the products available in stock
    private DefaultListModel<Product> stockModel;
    // Model to hold all products (unfiltered)
    private DefaultListModel<Product> allStock;
    private DefaultListModel<String> adminStock;
    
    public StockManager() {
        String line;
        String csvSplitBy = ",";
        String csvFile = "Stock.txt";
        
        // Initialize the list model to hold all products
        this.allStock = new DefaultListModel<>();
        this.adminStock = new DefaultListModel<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Reading data from the CSV file to populate the stock
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                
                // Extract product details from CSV line
                int barcode = Integer.parseInt(data[0]);
                String productCategory = data[1].trim().toUpperCase();
                String brand = data[3].trim();
                String color = data[4].trim();
                String connectivity = data[5].trim().toUpperCase();
                int quantityInStock = Integer.parseInt(data[6].trim());
                double originalCost = Double.parseDouble(data[7].trim());
                double retailPrice = Double.parseDouble(data[8].trim());
                
                // Determine product category and create corresponding product object
                ProductCategory category = ProductCategory.valueOf(productCategory.toUpperCase());
                ConnectivityType connType = ConnectivityType.valueOf(connectivity.toUpperCase());
                
                if (productCategory.equals("KEYBOARD")) {                 
                    KeyboardLayout layout = KeyboardLayout.valueOf(data[9].trim().toUpperCase());
                    DeviceType keyboardType = DeviceType.valueOf(data[2].trim().toUpperCase());
                    
                    Keyboard keyboard = new Keyboard(barcode, brand, color, connType, quantityInStock, 
                                                      originalCost, retailPrice, category, layout, keyboardType);
                    
                    addProduct(keyboard); // Add keyboard to stock
                } else if (productCategory.equals("MOUSE")) {
                    int noOfButtons = Integer.parseInt(data[9].trim());
                    DeviceType mouseType = DeviceType.valueOf(data[2].trim().toUpperCase());
                    
                    Mouse mouse = new Mouse(barcode, brand, color, connType, quantityInStock,
                                            originalCost, retailPrice, category, noOfButtons, mouseType);
                    
                    addProduct(mouse); // Add mouse to stock
                }
                
                // Initialize stockModel and copy allStock to it
                this.stockModel = new DefaultListModel<>();
                copyListModel(this.allStock, this.stockModel);
            }
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Could not read from text file", "File Reading Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Method to add a product to the stock
    public void addProduct(Product product) {
        for (int i = 0; i < allStock.size(); i++) {
            Product productToCompare = allStock.get(i);
            if (product.getRetailPrice() < productToCompare.getRetailPrice()) {
                this.allStock.add(i, product); // Insert product at appropriate price point
                this.adminStock.add(i, product + " - Original Cost: £" + Double.toString(product.getOriginalCost()));
                return;
            }
        }
        this.allStock.addElement(product); // Add product to end of the list if no suitable position found
        this.adminStock.addElement(product + " - Original Cost: £" + Double.toString(product.getOriginalCost()));
    }
    
    // Method to sort products in allStock by retail price
	private void sortProductsByRetailPrice() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < allStock.size(); i++) {
            productList.add(allStock.getElementAt(i));
        }

        // Comparator to sort products by retail price
        Comparator<Product> priceComparator = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getRetailPrice(), p2.getRetailPrice());
            }
        };

        Collections.sort(productList, priceComparator); // Sort productList by retail price

        allStock.clear(); // Clear allStock

        // Add sorted elements back to allStock
        for (Product product : productList) {
            allStock.addElement(product);
        }
    }
    
    // Method to copy contents from source to destination list model
    private void copyListModel(DefaultListModel<Product> source, DefaultListModel<Product> destination) {
        destination.clear();
        for (int i = 0; i < source.size(); i++) {
            destination.addElement(source.getElementAt(i));
        }
    }
    
    // Method to get the model containing the filtered or sorted stock
    public ListModel<Product> getStockModel() {
        return stockModel;
    }
    
    public ListModel<String> getAdminStock() {
        return adminStock;
    }
    
    // Method to clear any applied filters and reset the stock model
    public void clearFilters() {
        this.stockModel.clear();
        copyListModel(this.allStock, this.stockModel); // Reset stockModel to allStock
    }
    
    // Method to filter products by number of mouse buttons
    public void searchByMouseBtns(int noOfBtns) {
        this.stockModel.clear(); // Clear current stockModel
        
        for (int i = 0; i < allStock.size(); i++) {
            Product product = allStock.get(i);
            if (product.getCategory().equals(ProductCategory.MOUSE)) {
                Mouse mouse = (Mouse)product; // Downcast to Mouse
                if (mouse.getNoOfButtons() == noOfBtns) {
                    stockModel.addElement(mouse); // Add matching mouse to stockModel
                }
            }
        }
        
        // Display warning if no matching mice found
        if (stockModel.getSize() == 0) {
            clearFilters();
            JOptionPane.showMessageDialog(null, "No mice with " + noOfBtns + " buttons are in stock",
                                          "Invalid number of buttons", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Method to search for a product by barcode
    public void searchByBarcode(int barcode) {
        this.stockModel.clear(); // Clear current stockModel
        
        if (String.valueOf(barcode).length() == 6) {
            for (int i = 0; i < allStock.getSize(); i++) {
                Product product = allStock.getElementAt(i);
                if (product.getBarcode() == barcode) {
                    this.stockModel.addElement(product); // Add matching product to stockModel
                    return;
                }
            }
            clearFilters();
            JOptionPane.showMessageDialog(null, "Barcode doesn't exist", "Invalid barcode", JOptionPane.WARNING_MESSAGE);
        } else if (barcode == -1) {
            clearFilters();
            JOptionPane.showMessageDialog(null, "Empty barcode", "Invalid barcode", JOptionPane.WARNING_MESSAGE);       
        } else {
            clearFilters();
            JOptionPane.showMessageDialog(null, "Barcode doesn't exist", "Invalid barcode", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Method to check if a barcode already exists in the stock
    public boolean duplicateBarcode(int barcode) {
        for (int i = 0; i < allStock.getSize(); i++) {
            Product product = allStock.getElementAt(i);
            if (product.getBarcode() == barcode) {
                return true; // Barcode already exists
            }
        }
        return false; // Barcode does not exist
    }
    
    // Method to add quantity to existing product by barcode
    public boolean doesBarcodeExist(int barcode, int quantityToAdd) {
        if (quantityToAdd < 1) {
            JOptionPane.showMessageDialog(null, "Quantity needs to be greater than 0", "Invalid quantity", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
	        if (String.valueOf(barcode).length() == 6) {
	            for (int i = 0; i < allStock.getSize(); i++) {
	                Product product = allStock.getElementAt(i);
	                if (product.getBarcode() == barcode) {
	                    product.setQuantityInStock(product.getQuantityInStock() + quantityToAdd); // Update quantity
	                    return true;
	                }
	            }
	        }
        }
        JOptionPane.showMessageDialog(null, "Barcode doesn't exist", "Invalid barcode", JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    // Method to retrieve the list model containing all stock (unfiltered)
    public ListModel<Product> getAllStock() {
        return allStock;
    }
}
