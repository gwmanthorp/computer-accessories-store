import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField barcodeInputBox;
    private JTextField emailInput;
    private JTextField cardNumInput;
    private JTextField securityCodeInput;
    private JTextField barcodeToAdd;
    private JTextField brandToAdd;
    private JTextField colourToAdd;
    private JTextField originalCostToAdd;
    private JTextField retailPriceToAdd;
    private JTextField additionalInfoToAdd;
    private JTextField existingBarcode;
    StockManager stock = new StockManager();
    UserManager users = new UserManager();
    ValidPayment checkPayment = new ValidPayment();
    UpdateTextFile fileManager = new UpdateTextFile();
    private Admin currentAdmin;
    private Customer currentCustomer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Sets the current admin.
     * 
     * @param currentAdmin the current admin to set
     */
    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    /**
     * Sets the current customer.
     * 
     * @param currentCustomer the current customer to set
     */
    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    /**
     * Gets the current customer.
     * 
     * @return the current customer
     */
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /**
     * Gets the current admin.
     * 
     * @return the current admin
     */
    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
        // Setting up the main JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1020, 630);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Main Tabbed Pane
        JTabbedPane mainPane = new JTabbedPane(JTabbedPane.TOP);
        mainPane.setBounds(10, 10, 986, 573);
        contentPane.add(mainPane);

        // Admin Panel
        JPanel admin = new JPanel();
        mainPane.addTab("Admin", null, admin, null);
        admin.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 961, 526);
        admin.add(tabbedPane);

        // Admin Select Panel
        JPanel adminSelect = new JPanel();
        tabbedPane.addTab("User Select", null, adminSelect, null);
        adminSelect.setLayout(null);

        JLabel selectAdminLabel = new JLabel("User Select");
        selectAdminLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        selectAdminLabel.setBounds(424, 10, 106, 28);
        adminSelect.add(selectAdminLabel);

        JList<Admin> adminList = new JList<>();
        adminList.setModel(users.getAdminModel());
        adminList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JScrollPane scrAdminSelection = new JScrollPane(adminList);
        scrAdminSelection.setBounds(197, 48, 556, 350);
        adminSelect.add(scrAdminSelection);
        adminSelect.add(scrAdminSelection);

        JLabel currentUserAdminSelectLabel = new JLabel("Current User: ");
        currentUserAdminSelectLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        currentUserAdminSelectLabel.setBounds(10, 469, 581, 20);
        adminSelect.add(currentUserAdminSelectLabel);

        // View Products Panel
        JPanel viewProducts = new JPanel();
        tabbedPane.addTab("View Products", null, viewProducts, null);
        viewProducts.setLayout(null);

        JLabel viewProductsLabel = new JLabel("Product List");
        viewProductsLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        viewProductsLabel.setBounds(422, 10, 111, 28);
        viewProducts.add(viewProductsLabel);

        JList<String> viewProductsList = new JList<>();
        viewProductsList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JScrollPane scrProductDisplay = new JScrollPane(viewProductsList);
        scrProductDisplay.setBounds(80, 50, 800, 350);
        viewProducts.add(scrProductDisplay);

        JLabel currentUserShopLabel_1 = new JLabel("Current User: ");
        currentUserShopLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        currentUserShopLabel_1.setBounds(10, 469, 581, 20);
        viewProducts.add(currentUserShopLabel_1);

        // Add Product Panel
        JPanel addProduct = new JPanel();
        tabbedPane.addTab("Add Product", null, addProduct, null);
        addProduct.setLayout(null);

        JLabel addProductToStock = new JLabel("Add New Product To Stock");
        addProductToStock.setFont(new Font("Tahoma", Font.BOLD, 18));
        addProductToStock.setBounds(536, 10, 277, 28);
        addProduct.add(addProductToStock);

        JLabel lblEnterBarcode = new JLabel("Enter barcode:");
        lblEnterBarcode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterBarcode.setBounds(536, 60, 128, 28);
        addProduct.add(lblEnterBarcode);

        barcodeToAdd = new JTextField();
        barcodeToAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        barcodeToAdd.setBounds(658, 60, 142, 28);
        addProduct.add(barcodeToAdd);
        barcodeToAdd.setColumns(10);

        JLabel lblEnterCategory = new JLabel("Enter product category:");
        lblEnterCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterCategory.setBounds(536, 98, 195, 28);
        addProduct.add(lblEnterCategory);

        JComboBox<ProductCategory> productCategoryToAdd = new JComboBox<>(ProductCategory.values());
        productCategoryToAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
        productCategoryToAdd.setBounds(729, 98, 128, 28);
        addProduct.add(productCategoryToAdd);

        JLabel lblEnterDeviceType = new JLabel("Enter device type:");
        lblEnterDeviceType.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterDeviceType.setBounds(536, 136, 195, 28);
        addProduct.add(lblEnterDeviceType);

        JComboBox<DeviceType> deviceTypeToAdd = new JComboBox<>(DeviceType.values());
        deviceTypeToAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deviceTypeToAdd.setBounds(685, 137, 128, 28);
        addProduct.add(deviceTypeToAdd);

        JLabel lblEnterBrand = new JLabel("Enter brand:");
        lblEnterBrand.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterBrand.setBounds(536, 175, 102, 28);
        addProduct.add(lblEnterBrand);

        brandToAdd = new JTextField();
        brandToAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        brandToAdd.setColumns(10);
        brandToAdd.setBounds(639, 175, 142, 28);
        addProduct.add(brandToAdd);

        JLabel lblEnterColour = new JLabel("Enter colour:");
        lblEnterColour.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterColour.setBounds(536, 215, 115, 28);
        addProduct.add(lblEnterColour);

        colourToAdd = new JTextField();
        colourToAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        colourToAdd.setColumns(10);
        colourToAdd.setBounds(639, 215, 142, 28);
        addProduct.add(colourToAdd);

        JLabel lblEnterConnectivity = new JLabel("Enter connectivity:");
        lblEnterConnectivity.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterConnectivity.setBounds(536, 254, 195, 28);
        addProduct.add(lblEnterConnectivity);

        JComboBox<ConnectivityType> connectivityToAdd = new JComboBox<>(ConnectivityType.values());
        connectivityToAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
        connectivityToAdd.setBounds(685, 255, 128, 28);
        addProduct.add(connectivityToAdd);

        JLabel lblEnterQuantityOf = new JLabel("Enter quantity:");
        lblEnterQuantityOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterQuantityOf.setBounds(536, 293, 128, 28);
        addProduct.add(lblEnterQuantityOf);

        JSpinner quantityInput = new JSpinner();
        quantityInput.setFont(new Font("Tahoma", Font.PLAIN, 18));
        quantityInput.setBounds(658, 290, 44, 34);
        addProduct.add(quantityInput);

        JLabel lblEnterOriginalCost = new JLabel("Enter original cost:");
        lblEnterOriginalCost.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterOriginalCost.setBounds(536, 332, 166, 28);
        addProduct.add(lblEnterOriginalCost);

        originalCostToAdd = new JTextField();
        originalCostToAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        originalCostToAdd.setColumns(10);
        originalCostToAdd.setBounds(688, 332, 93, 28);
        addProduct.add(originalCostToAdd);

        JLabel lblEnterRetailPrice = new JLabel("Enter retail price:");
        lblEnterRetailPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterRetailPrice.setBounds(536, 370, 166, 28);
        addProduct.add(lblEnterRetailPrice);

        retailPriceToAdd = new JTextField();
        retailPriceToAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        retailPriceToAdd.setColumns(10);
        retailPriceToAdd.setBounds(676, 370, 93, 28);
        addProduct.add(retailPriceToAdd);

        JLabel lblEnterAdditionalInformation = new JLabel("Enter additional information:");
        lblEnterAdditionalInformation.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterAdditionalInformation.setBounds(536, 411, 233, 28);
        addProduct.add(lblEnterAdditionalInformation);

        additionalInfoToAdd = new JTextField();
        additionalInfoToAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        additionalInfoToAdd.setColumns(10);
        additionalInfoToAdd.setBounds(764, 411, 66, 28);
        addProduct.add(additionalInfoToAdd);

        JLabel currentUserShopLabel_2 = new JLabel("Current User: ");
        currentUserShopLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        currentUserShopLabel_2.setBounds(10, 469, 363, 20);
        addProduct.add(currentUserShopLabel_2);

        // Adding existing products by barcode
        JLabel lblAddExistingProduct = new JLabel("Add Existing Product To Stock By Barcode");
        lblAddExistingProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAddExistingProduct.setBounds(47, 10, 388, 28);
        addProduct.add(lblAddExistingProduct);

        JLabel lblEnterBarcode_1 = new JLabel("Enter barcode:");
        lblEnterBarcode_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterBarcode_1.setBounds(47, 60, 128, 28);
        addProduct.add(lblEnterBarcode_1);

        existingBarcode = new JTextField();
        existingBarcode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        existingBarcode.setColumns(10);
        existingBarcode.setBounds(169, 60, 142, 28);
        addProduct.add(existingBarcode);

        JLabel lblEnterQuantityOf_1 = new JLabel("Enter quantity:");
        lblEnterQuantityOf_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterQuantityOf_1.setBounds(47, 101, 128, 28);
        addProduct.add(lblEnterQuantityOf_1);

        JSpinner existingQuantity = new JSpinner();
        existingQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
        existingQuantity.setBounds(169, 98, 44, 34);
        addProduct.add(existingQuantity);

        // Button to add existing product to stock by barcode
        JButton addToStockByBarcode = new JButton("Add To Stock");
        addToStockByBarcode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCurrentAdmin() != null) {
                    try {
                        int barcode = Integer.parseInt(existingBarcode.getText());
                        int quantityToAdd = (int) existingQuantity.getValue();

                        if (stock.doesBarcodeExist(barcode, quantityToAdd)) {
                            fileManager.updateFile(stock.getAllStock());
                            viewProductsList.setModel(stock.getAdminStock());
                            JOptionPane.showMessageDialog(
                                null,
                                "Quantity of product: " + barcode + ", increased by " + quantityToAdd,
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    } catch (Exception b) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Empty barcode",
                            "Invalid barcode",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to add by barcode",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        addToStockByBarcode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addToStockByBarcode.setBounds(99, 150, 162, 28);
        addProduct.add(addToStockByBarcode);

        // Button to add new product to stock
        JButton addToStockBtn = new JButton("Add To Stock");
        addToStockBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCurrentAdmin() != null) {
                    try {
                        int barcode = Integer.parseInt(barcodeToAdd.getText());
                        ProductCategory category = (ProductCategory) productCategoryToAdd.getSelectedItem();
                        DeviceType deviceType = (DeviceType) deviceTypeToAdd.getSelectedItem();
                        String brand = brandToAdd.getText();
                        String color = colourToAdd.getText();
                        ConnectivityType connectivity = (ConnectivityType) connectivityToAdd.getSelectedItem();
                        int quantity = (int) quantityInput.getValue();
                        double originalCost = Double.parseDouble(originalCostToAdd.getText());
                        double retailPrice = Double.parseDouble(retailPriceToAdd.getText());
                        String additionalInfo = additionalInfoToAdd.getText();

                        if (stock.duplicateBarcode(barcode)) {
                            JOptionPane.showMessageDialog(
                                null,
                                "Barcode already exists",
                                "Invalid barcode",
                                JOptionPane.WARNING_MESSAGE
                            );
                        } else {
                            if (!brand.equals("") && !color.equals("") && !additionalInfo.equals("") && quantity > 0 && Integer.toString(barcode).length() == 6) {
                                if (category.equals(ProductCategory.MOUSE)) {
                                    if (deviceType.equals(DeviceType.FLEXIBLE)) {
                                        JOptionPane.showMessageDialog(
                                            null,
                                            "Mouse cannot be of type 'FLEXIBLE'",
                                            "Invalid device type",
                                            JOptionPane.WARNING_MESSAGE
                                        );
                                    } else {
                                        try {
                                            int noOfButtons = Integer.parseInt(additionalInfo);
                                            Mouse mouse = new Mouse(
                                                barcode,
                                                brand,
                                                color,
                                                connectivity,
                                                quantity,
                                                originalCost,
                                                retailPrice,
                                                category,
                                                noOfButtons,
                                                deviceType
                                            );

                                            stock.addProduct(mouse);
                                            viewProductsList.setModel(stock.getAdminStock());
                                            stock.clearFilters();
                                            fileManager.updateFile(stock.getAllStock());
                                            JOptionPane.showMessageDialog(
                                                null,
                                                "Successfully added to stock: " + mouse.toString(),
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE
                                            );
                                        } catch (IllegalArgumentException buttons) {
                                            JOptionPane.showMessageDialog(
                                                null,
                                                "Invalid buttons (additional info), integers only",
                                                "Invalid number of buttons",
                                                JOptionPane.WARNING_MESSAGE
                                            );
                                        }
                                    }
                                } else if (category.equals(ProductCategory.KEYBOARD)) {
                                    try {
                                        KeyboardLayout keyboardLayout = KeyboardLayout.valueOf(additionalInfo);
                                        Keyboard keyboard = new Keyboard(
                                            barcode,
                                            brand,
                                            color,
                                            connectivity,
                                            quantity,
                                            originalCost,
                                            retailPrice,
                                            category,
                                            keyboardLayout,
                                            deviceType
                                        );

                                        stock.addProduct(keyboard);
                                        viewProductsList.setModel(stock.getAdminStock());
                                        stock.clearFilters();
                                        fileManager.updateFile(stock.getAllStock());
                                        JOptionPane.showMessageDialog(
                                            null,
                                            "Successfully added to stock: " + keyboard.toString(),
                                            "Success",
                                            JOptionPane.INFORMATION_MESSAGE
                                        );
                                    } catch (IllegalArgumentException layout) {
                                        JOptionPane.showMessageDialog(
                                            null,
                                            "Invalid keyboard layout (additional info), UK or US only",
                                            "Invalid keyboard layout",
                                            JOptionPane.WARNING_MESSAGE
                                        );
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Invalid data entered",
                                    "Invalid data",
                                    JOptionPane.WARNING_MESSAGE
                                );
                            }
                        }
                    } catch (Exception n) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Invalid data entered",
                            "Invalid data",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to add product to stock",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        addToStockBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addToStockBtn.setBounds(593, 451, 162, 28);
        addProduct.add(addToStockBtn);

        // Customer Panel
        JPanel customer = new JPanel();
        mainPane.addTab("Customer", null, customer, null);
        customer.setLayout(null);

        JTabbedPane customerPane = new JTabbedPane(JTabbedPane.TOP);
        customerPane.setBounds(10, 10, 961, 526);
        customer.add(customerPane);

        // Customer Select Panel
        JPanel customerSelect = new JPanel();
        customerPane.addTab("User Select", null, customerSelect, null);
        customerSelect.setLayout(null);

        JLabel customerSelectLabel = new JLabel("User Select");
        customerSelectLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        customerSelectLabel.setBounds(424, 10, 106, 28);
        customerSelect.add(customerSelectLabel);

        JList<Customer> customerList = new JList<>();
        customerList.setModel(users.getCustomerModel());
        customerList.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JScrollPane scrCustomerSelection = new JScrollPane(customerList);
        scrCustomerSelection.setBounds(196, 48, 556, 350);
        customerSelect.add(scrCustomerSelection);

        JLabel currentUserCustomerSelectLabel = new JLabel("Current User: ");
        currentUserCustomerSelectLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        currentUserCustomerSelectLabel.setBounds(10, 469, 523, 20);
        customerSelect.add(currentUserCustomerSelectLabel);

        // Shop Panel
        JPanel shop = new JPanel();
        customerPane.addTab("Shop", null, shop, null);
        shop.setLayout(null);

        JLabel productsLabel = new JLabel("Products");
        productsLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        productsLabel.setBounds(36, 10, 147, 28);
        shop.add(productsLabel);

        JList<Product> productsList = new JList<>();
        productsList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JScrollPane scrShopProductDisplay = new JScrollPane(productsList);
        scrShopProductDisplay.setBounds(36, 48, 556, 350);
        shop.add(scrShopProductDisplay);

        JLabel filterLabel = new JLabel("Filter Products");
        filterLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        filterLabel.setBounds(620, 7, 182, 34);
        shop.add(filterLabel);

        JLabel barcodeLookupLabel = new JLabel("Barcode Lookup:");
        barcodeLookupLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        barcodeLookupLabel.setBounds(620, 61, 147, 28);
        shop.add(barcodeLookupLabel);

        barcodeInputBox = new JTextField();
        barcodeInputBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        barcodeInputBox.setBounds(630, 99, 182, 34);
        shop.add(barcodeInputBox);
        barcodeInputBox.setColumns(10);

        JLabel noOfBtnsLabel = new JLabel("Number of buttons on mouse:");
        noOfBtnsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        noOfBtnsLabel.setBounds(620, 202, 266, 28);
        shop.add(noOfBtnsLabel);

        JSpinner mouseButtonInputBox = new JSpinner();
        mouseButtonInputBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        mouseButtonInputBox.setBounds(630, 240, 45, 34);
        shop.add(mouseButtonInputBox);

        JLabel currentUserShopLabel = new JLabel("Current User: ");
        currentUserShopLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        currentUserShopLabel.setBounds(10, 469, 523, 20);
        shop.add(currentUserShopLabel);

        // Basket Panel
        JPanel basket = new JPanel();
        customerPane.addTab("Basket", null, basket, null);
        basket.setLayout(null);

        JLabel basketLabel = new JLabel("Basket");
        basketLabel.setBounds(36, 10, 64, 28);
        basketLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        basket.add(basketLabel);

        JList<Product> basketList = new JList<>();
        basketList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JScrollPane scrBasketDisplay = new JScrollPane(basketList);
        scrBasketDisplay.setBounds(36, 48, 556, 350);
        basket.add(scrBasketDisplay);

        JLabel paymentLabel = new JLabel("Payment");
        paymentLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        paymentLabel.setBounds(625, 44, 127, 28);
        basket.add(paymentLabel);

        JLabel currentUserBasketLabel = new JLabel("Current User:");
        currentUserBasketLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        currentUserBasketLabel.setBounds(10, 469, 536, 20);
        basket.add(currentUserBasketLabel);

        // Payment Tabbed Pane
        JTabbedPane paymentPane = new JTabbedPane(JTabbedPane.TOP);
        paymentPane.setBounds(627, 95, 289, 261);
        basket.add(paymentPane);

        // PayPal Panel
        JPanel paypal = new JPanel();
        paymentPane.addTab("PayPal", null, paypal, null);
        paypal.setLayout(null);

        JLabel emailLabel = new JLabel("Enter PayPal email address:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        emailLabel.setBounds(22, 10, 302, 36);
        paypal.add(emailLabel);

        emailInput = new JTextField();
        emailInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailInput.setBounds(32, 56, 231, 32);
        paypal.add(emailInput);
        emailInput.setColumns(10);

        JButton paypalPaymentBtn = new JButton("Pay");
        paypalPaymentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailInput.getText();
                try {
                    double amount = getCurrentCustomer().getAmount();
                    Address fullAddress = getCurrentCustomer().getAddress();
                    if (getCurrentCustomer().allItemsAvailable()) {
                        if (amount > 0) {
                            if (checkPayment.isValidEmail(email)) {
                                PayPal paypal = new PayPal(email, amount, fullAddress);

                                // Update quantity in stock
                                for (int i = 0; i < getCurrentCustomer().getBasket().getSize(); i++) {
                                    Product product = getCurrentCustomer().getBasket().getElementAt(i);
                                    product.setQuantityInStock(product.getQuantityInStock() - 1);
                                }

                                getCurrentCustomer().clearBasket();
                                emailInput.setText("");
                                fileManager.updateFile(stock.getAllStock());
                                JOptionPane.showMessageDialog(
                                    null,
                                    paypal.getReceipt().paypalToString(),
                                    "Receipt",
                                    JOptionPane.INFORMATION_MESSAGE
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Invalid email format",
                                    "Payment Info",
                                    JOptionPane.WARNING_MESSAGE
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Add to basket to make a purchase",
                                "Empty Basket",
                                JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to make a purchase",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        paypalPaymentBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        paypalPaymentBtn.setBounds(103, 112, 85, 32);
        paypal.add(paypalPaymentBtn);

        // Card Payment Panel
        JPanel cardPayment = new JPanel();
        paymentPane.addTab("Card Payment", null, cardPayment, null);
        cardPayment.setLayout(null);

        JLabel cardNumLabel = new JLabel("6-digit card number:");
        cardNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cardNumLabel.setBounds(22, 10, 214, 36);
        cardPayment.add(cardNumLabel);

        cardNumInput = new JTextField();
        cardNumInput.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cardNumInput.setBounds(32, 45, 137, 32);
        cardPayment.add(cardNumInput);
        cardNumInput.setColumns(10);

        JLabel securityCodeLabel = new JLabel("3-digit security code:");
        securityCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        securityCodeLabel.setBounds(22, 82, 214, 36);
        cardPayment.add(securityCodeLabel);

        securityCodeInput = new JTextField();
        securityCodeInput.setFont(new Font("Tahoma", Font.PLAIN, 18));
        securityCodeInput.setColumns(10);
        securityCodeInput.setBounds(32, 120, 69, 32);
        cardPayment.add(securityCodeInput);

        JButton cardPaymentBtn = new JButton("Pay");
        cardPaymentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardNum = cardNumInput.getText();
                String securityCode = securityCodeInput.getText();

                try {
                    double amount = getCurrentCustomer().getAmount();
                    Address fullAddress = getCurrentCustomer().getAddress();
                    if (getCurrentCustomer().allItemsAvailable()) {
                        if (amount > 0) {
                            if (checkPayment.isValidCardInfo(cardNum, securityCode)) {
                                CardPayment cardPayment = new CardPayment(cardNum, securityCode, amount, fullAddress);

                                // Update quantity in stock
                                for (int i = 0; i < getCurrentCustomer().getBasket().getSize(); i++) {
                                    Product product = getCurrentCustomer().getBasket().getElementAt(i);
                                    product.setQuantityInStock(product.getQuantityInStock() - 1);
                                }

                                getCurrentCustomer().clearBasket();
                                cardNumInput.setText("");
                                securityCodeInput.setText("");
                                fileManager.updateFile(stock.getAllStock());
                                JOptionPane.showMessageDialog(
                                    null,
                                    cardPayment.getReceipt().cardToString(),
                                    "Receipt",
                                    JOptionPane.INFORMATION_MESSAGE
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Invalid card info entered",
                                    "Payment Info",
                                    JOptionPane.WARNING_MESSAGE
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Add to basket to make a purchase",
                                "Empty Basket",
                                JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to make a purchase",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        cardPaymentBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cardPaymentBtn.setBounds(100, 178, 85, 32);
        cardPayment.add(cardPaymentBtn);

        // Select Admin Button
        JButton User = new JButton("Select User");
        User.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Admin selectedAdmin = adminList.getSelectedValue();
                    setCurrentAdmin(selectedAdmin);
                    currentUserShopLabel_1.setText("Current User: " + currentAdmin.getUsername());
                    currentUserShopLabel_2.setText("Current User: " + currentAdmin.getUsername());
                    currentUserAdminSelectLabel.setText("Current User: " + currentAdmin.getUsername());
                    viewProductsList.setModel(stock.getAdminStock());
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        User.setFont(new Font("Tahoma", Font.PLAIN, 18));
        User.setBounds(403, 419, 149, 28);
        adminSelect.add(User);

        // Select Customer Button
        JButton selectCustomerBtn = new JButton("Select User");
        selectCustomerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Customer selectedCustomer = customerList.getSelectedValue();
                    setCurrentCustomer(selectedCustomer);
                    currentUserBasketLabel.setText("Current User: " + currentCustomer.getUsername());
                    currentUserShopLabel.setText("Current User: " + currentCustomer.getUsername());
                    currentUserCustomerSelectLabel.setText("Current User: " + currentCustomer.getUsername());
                    securityCodeInput.setText("");
                    cardNumInput.setText("");
                    emailInput.setText("");
                    barcodeInputBox.setText("");
                    mouseButtonInputBox.setValue(0);
                    stock.clearFilters();
                    productsList.setModel(stock.getStockModel());
                    try {
                        basketList.setModel(getCurrentCustomer().getBasket());
                    } catch (Exception b) {
                        System.out.println("Empty basket");
                    }
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        selectCustomerBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        selectCustomerBtn.setBounds(403, 419, 149, 28);
        customerSelect.add(selectCustomerBtn);

        // Add to Basket Button
        JButton addToBasketBtn = new JButton("Add To Basket");
        addToBasketBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCurrentCustomer() != null) {
                    try {
                        Product product = productsList.getSelectedValue();
                        if (getCurrentCustomer().maxAdded(product)) {
                            getCurrentCustomer().addToBasket(product);
                            basketList.setModel(getCurrentCustomer().getBasket());
                        } else {
                        	JOptionPane.showMessageDialog(
                                    null,
                                    "This product is out of stock",
                                    "Maxiumum added",
                                    JOptionPane.WARNING_MESSAGE
                                );
                        }
                    } catch (Exception n) {
                    	JOptionPane.showMessageDialog(
                                null,
                                "Select an item too add to basket",
                                "No item selected",
                                JOptionPane.WARNING_MESSAGE
                            );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to add to basket",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        addToBasketBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addToBasketBtn.setBounds(226, 424, 176, 28);
        shop.add(addToBasketBtn);

        // Remove from Basket Button
        JButton removeFromBasketBtn = new JButton("Remove From Basket");
        removeFromBasketBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Product product = basketList.getSelectedValue();
                    getCurrentCustomer().removeFromBasket(product);
                    basketList.setModel(getCurrentCustomer().getBasket());
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to edit basket",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        removeFromBasketBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        removeFromBasketBtn.setBounds(76, 408, 215, 28);
        basket.add(removeFromBasketBtn);

        // Clear Basket Button
        JButton clearBasketBtn = new JButton("Clear Basket");
        clearBasketBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    getCurrentCustomer().clearBasket();
                    basketList.setModel(getCurrentCustomer().getBasket());
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to edit basket",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        clearBasketBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        clearBasketBtn.setBounds(300, 408, 158, 28);
        basket.add(clearBasketBtn);

        // Search by Barcode Button
        JButton searchByBarcode = new JButton("Search By Barcode");
        searchByBarcode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCurrentCustomer() != null) {
                    try {
                        int barcode = Integer.parseInt(barcodeInputBox.getText());
                        stock.searchByBarcode(barcode);
                        productsList.setModel(stock.getStockModel());
                    } catch (Exception b) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Empty barcode",
                            "Invalid barcode",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to search by barcode",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        searchByBarcode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        searchByBarcode.setBounds(661, 150, 206, 28);
        shop.add(searchByBarcode);

        // Search by Mouse Buttons Button
        JButton searchByMouseBtns = new JButton("Search By No. of Buttons");
        searchByMouseBtns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCurrentCustomer() != null) {
                    int noOfBtns = (int) mouseButtonInputBox.getValue();
                    stock.searchByMouseBtns(noOfBtns);
                    productsList.setModel(stock.getStockModel());
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to search by number of buttons",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        searchByMouseBtns.setFont(new Font("Tahoma", Font.PLAIN, 18));
        searchByMouseBtns.setBounds(630, 284, 256, 28);
        shop.add(searchByMouseBtns);

        // Clear Filters Button
        JButton clearFilters = new JButton("Clear Filters");
        clearFilters.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (getCurrentCustomer() != null) {
	                stock.clearFilters();
	                productsList.setModel(stock.getStockModel());
            	} else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Select a user to clear filters",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
            	}
            }
        });
        clearFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
        clearFilters.setBounds(690, 370, 142, 28);
        shop.add(clearFilters);
    }
}
