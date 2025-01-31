import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import javax.swing.JOptionPane;
import javax.swing.ListModel;

// This class handles updating a text file with product information from a ListModel
public class UpdateTextFile {

  // Method to update the text file with product details from the ListModel
  public void updateFile(ListModel<Product> productList) {
      try {
		// Create a FileWriter object to write to the "Stock.txt" file
		FileWriter myWriter = new FileWriter("Stock.txt");
		
		// Iterate over each element in the ListModel
		for (int i = 0; i < productList.getSize(); i++) {
			  // Get the product at the current index
	    	  Product product = (Product) productList.getElementAt(i);
	    	  
	    	  // Check if the product is an instance of Keyboard
	    	  if (product instanceof Keyboard){
	    		  // Cast the product to Keyboard type for specific processing
	    		  Keyboard keyboard = (Keyboard) product;
	    		  // Write the formatted text representation of the keyboard to the file
	    		  myWriter.write(keyboard.textFileFormat());
	    	  } else {
	    		  // Assume the product is an instance of Mouse
	    		  Mouse mouse = (Mouse) product;
	    		  // Write the formatted text representation of the mouse to the file
	    		  myWriter.write(mouse.textFileFormat()); 
	    	  }
	    	  
	    	  // Add a newline character if it's not the last element in the ListModel
	    	  if (i != productList.getSize() - 1) {
	    		  myWriter.write("\n");
	    	  }
	      }
	      
	      // Close the FileWriter to ensure all data is written and resources are released
	      myWriter.close();
	      
	} catch (IOException e) {
		// Handle any IOException that may occur during file writing
		JOptionPane.showMessageDialog(null, "Could not update text file", "File Writing Error", JOptionPane.WARNING_MESSAGE);
	}
  }
}
