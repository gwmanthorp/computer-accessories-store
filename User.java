// Abstract class representing a User
public abstract class User {
	
    // Fields to store user information
	private int userID;             // Unique identifier for the user
	private String username;        // User's username
	private String name;            // User's full name
	private Address address;        // User's address
	private UserRole role;          // User's role in the system (e.g., admin, regular user)
	
	// Constructor to initialize a User object
	public User(int userID, String username, String name, Address address, UserRole role) {
		this.userID = userID;
		this.username = username;
        this.name = name;
        this.address = address;
        this.role = role;
	}
	
	// Override toString method to provide a string representation of the User
	@Override
	public String toString() {
		// Format the user information as a string
		return Integer.toString(userID) + " - " + username + ": " + name + ", Address: " + address.toString();
	}
	
	// Getter method to retrieve the username of the User
	public String getUsername() {
		return username;
	}
	
	// Getter method to retrieve the address of the User
	public Address getAddress() {
		return address;
	}
}