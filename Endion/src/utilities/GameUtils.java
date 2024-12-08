package utilities;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import entities.Nameable;

public final class GameUtils {
	
	// Private constructor to prevent instantiation
    private GameUtils() {
        throw new UnsupportedOperationException("The GameUtils class cannot be instantiated.");
    }
    
    /**
    * Fills a JList with a list of strings.
    * 
    * @param list The JList to populate.
    * @param data The list of strings to be added to the JList.
    */
    public static void fillJList(JList<String> list, List<String> data) {
    	
    	// Create a new DefaultListModel to store the data
    	DefaultListModel<String> model = new DefaultListModel<>();
          
        // Add each string from the list to the DefaultListModel
        for (String item : data) {
        	model.addElement(item);
        }
     
        // Set the model to the JList
        list.setModel(model);
     }
    
    
    /**
    * Capitalize the first letter of a string
    * 
    * @param objectList The list of Nameable objects.
    * @return A list of names extracted from the objects.
    */
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    
    /**
    * Converts a list of objects that implement the Nameable interface into a list of their names.
    * 
    * @param objectList The list of Nameable objects.
    * @return A list of names extracted from the objects.
    */
    public static List<String> convertToNameList(List<? extends Nameable> objectList) {
    	// Stream over the list of Nameable objects
        return objectList.stream()
                // Call the getName() method on each object in the list of Nameable objects
                .map(Nameable::getName)
                // Collect all the names into a new list of strings
                .collect(Collectors.toList());
    }
}
