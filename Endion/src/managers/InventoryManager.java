package managers;

import java.util.List;
import java.util.stream.Collectors;

import entities.Player;
import entities.Item;

/**
* The {@code InventoryManager} class handles player inventory operations, 
* including adding, removing, and checking for specific items in the inventory.
* 
* @author JD Dennis
*/
public class InventoryManager {
	
	private Player player;
	
	/**
	* Constructs an {@code InventoryManager} with the specified player.
	*
	* @param player The {@link Player} whose inventory will be managed.
	*/
	public InventoryManager(Player player) {
		this.player = player;
	}
	
	/**
	* Creates a new {@link Item} with the specified name and adds it to the player's inventory.
	*
	* @param item The name of the item to add.
	*/
	public void foundItem(String item) {
		Item newItem = new Item(item);
		addItemToInventory(newItem);
	}
	
	/**
	* Adds an {@link Item} to the player's inventory.
	*
	* @param item The {@link Item} to add to the inventory.
	*/
    public void addItemToInventory(Item item) {
        player.addItem(item);
    }

    /**
	* Removes an {@link Item} from the player's inventory.
	*
	* @param item The {@link Item} to remove from the inventory.
	*/
    public void removeItemFromInventory(Item item) {
        List<Item> inventory = player.getInventory();
        if (inventory.contains(item)) {
            inventory.remove(item);
        }
    }
    
    /**
    * Checks if the player's inventory contains all three required items:
    * "Ancient Tome", "Ancient Sword", and "Ancient Shield".
    *
    * @return true if the player has all three items, false otherwise.
    */
    public boolean hasRequiredAncientItems() {
        // List of item names we are checking for
        List<String> requiredItems = List.of("Ancient Tome", "Ancient Sword", "Ancient Shield");
        
        // Create a list of item names from the player's inventory
        List<String> playerItemNames = player.getInventory().stream()
        										// Extract the name of each item
                                                 .map(item -> item.getName()).collect(Collectors.toList());

        // Check if the player's inventory contains all required item names
        for (String requiredItem : requiredItems) {
            if (!playerItemNames.contains(requiredItem)) {
                return false; // Return false if any of the items are missing
            }
        }
        
        return true; // Return true if all items are present
    }
}
