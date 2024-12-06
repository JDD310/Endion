package utilities;

public final class GameUtils {
	
	// Private constructor to prevent instantiation
    private GameUtils() {
        throw new UnsupportedOperationException("The GameUtils class cannot be instantiated.");
    }
    
    // Capitalize the first letter of a string
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    
    
}
