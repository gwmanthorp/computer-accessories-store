import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class UserManager {
    private DefaultListModel<Customer> customerModel; // Model to hold Customer objects
    private DefaultListModel<Admin> adminModel;      // Model to hold Admin objects

    /**
     * Constructor for UserManager class.
     * Reads user data from a CSV file and initializes customerModel and adminModel.
     */
    public UserManager() {
        String line;
        String csvSplitBy = ",";
        String csvFile = "UserAccounts.txt";

        // Initialize the customer and admin models
        this.customerModel = new DefaultListModel<>();
        this.adminModel = new DefaultListModel<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                // Extract user data from CSV fields
                int userID = Integer.parseInt(data[0]);
                String username = data[1].trim();
                String name = data[2].trim();
                Address address = new Address(Integer.parseInt(data[3].trim()), data[4].trim(), data[5].trim());
                UserRole role = UserRole.valueOf(data[6].trim().toUpperCase());

                // Create appropriate user object based on UserRole
                if (role.equals(UserRole.ADMIN)) {
                    Admin admin = new Admin(userID, username, name, address, role);
                    addAdmin(admin); // Add admin to adminModel
                } else if (role.equals(UserRole.CUSTOMER)) {
                    Customer customer = new Customer(userID, username, name, address, role);
                    addCustomer(customer); // Add customer to customerModel
                }
            }
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Could not read from text file", "File Reading Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Getter for adminModel.
    public DefaultListModel<Admin> getAdminModel(){
        return adminModel;
    }

    // Getter for customerModel.
    public DefaultListModel<Customer> getCustomerModel(){
        return customerModel;
    }

    // Adds an Admin object to the adminModel.
    public void addAdmin(Admin admin) {
        this.adminModel.addElement(admin);
    }

    // Adds a Customer object to the customerModel.
    public void addCustomer(Customer customer) {
        this.customerModel.addElement(customer);
    }
}
