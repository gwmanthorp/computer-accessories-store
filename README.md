# Computer Accessories Store

## Overview

This project is a Java-based application for managing a computer accessories store. It allows administrators to manage stock and customers to browse and purchase products.

## Features

- **Admin Panel**: Manage stock, add new products, and update existing products.
- **Customer Panel**: Browse products, add items to the basket, and make purchases using PayPal or credit card.
- **Product Management**: Supports different types of products like keyboards and mice with various attributes.
- **User Management**: Handles different user roles such as admin and customer.

## Project Structure

### File Structure

- `Admin.java`: Represents an admin user.
- `Address.java`: Represents a physical address.
- `CardPayment.java`: Handles card payment processing.
- `ConnectivityType.java`: Enum for connectivity types (wired, wireless).
- `Customer.java`: Represents a customer user.
- `DeviceType.java`: Enum for device types (gaming, standard, flexible).
- `Keyboard.java`: Represents a keyboard product.
- `KeyboardLayout.java`: Enum for keyboard layouts (US, UK).
- `MainFrame.java`: Main GUI frame for the application.
- `Mouse.java`: Represents a mouse product.
- `PayPal.java`: Handles PayPal payment processing.
- `PaymentMethod.java`: Interface for payment methods.
- `Product.java`: Abstract class for products.
- `ProductCategory.java`: Enum for product categories (keyboard, mouse).
- `Receipt.java`: Represents a receipt for transactions.
- `StockManager.java`: Manages the stock of products.
- `UpdateTextFile.java`: Handles updating the stock text file.
- `User.java`: Abstract class for users.
- `UserManager.java`: Manages user accounts.
- `UserRole.java`: Enum for user roles (customer, admin).
- `ValidPayment.java`: Validates payment information.

### Class Diagram

[ClassDiagram.jpg]

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE like Eclipse or IntelliJ IDEA

### Downloading the Project

1. Clone the repository:
   ```sh
   git clone https://github.com/gwmanthorp/computer-accessories-store.git
   ```
2. Navigate to the project directory:
   ```sh
   cd computer-accessories-store
   ```

### Compiling and Running

1. Open the project in your IDE.
2. Ensure all dependencies are resolved.
3. Compile the project.
4. Run the `MainFrame` class to start the application.

### Usage

- **Admin**: Select the "Admin" tab to manage stock and add new products.
- **Customer**: Select the "Customer" tab to browse products, add items to the basket, and make purchases.
