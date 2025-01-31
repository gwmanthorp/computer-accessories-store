// This class represents a physical address with a house number, postcode, and city.
public class Address {
    // Private instance variables to store address details
    private int houseNumber;  // House number of the address
    private String postcode;  // Postcode of the address
    private String city;      // City where the address is located
    
    // Constructor to initialize an Address object with given details
    public Address(int houseNumber, String postcode, String city) {
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
    }
    
    // Override the toString() method to provide a string representation of the Address object
    @Override
    public String toString() {
        // Create a formatted string combining house number, postcode, and city
        return Integer.toString(houseNumber) + " " + postcode + " " + city;
    }
}
