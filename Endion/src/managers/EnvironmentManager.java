package managers;

import java.util.List;
import java.util.Random;

import entities.Tile;


/**
* The {@code EnvironmentManager} class is responsible for managing the player's interaction 
* with the game environment. It allows players to view, search, and examine tiles 
* in the game world.
* 
* @author JD Dennis
*/
public class EnvironmentManager {

    private WorldManager worldManager;
    private InventoryManager inventoryManager; 
    private CombatManager combatManager;
    private NPCManager npcManager;
    private String currentEnvironment;

    /**
    * Constructs an {@code EnvironmentManager} with the specified managers.
    *
    * @param worldManager The {@link WorldManager} used to manage tile data.
    * @param inventoryManager The {@link InventoryManager} used to manage player inventory.
    * @param combatManager The {@link CombatManager} used to manage combat sequences.
    * @param npcManager The {@link NPCManager} used to manage NPC interactions.
    */
    public EnvironmentManager(
    						WorldManager worldManager,
    						InventoryManager inventoryManager,
    						CombatManager combatManager,
    						NPCManager npcManager
    						) {
        this.worldManager = worldManager;
        this.inventoryManager = inventoryManager;
        this.combatManager = combatManager;
        this.npcManager = npcManager;
    }

    /**
    * Initializes the environment and displays its information.
    */
    public void initializeEnvironment() {
        displayCurrentEnvironment();
        System.out.println("Initialized environment: " + currentEnvironment);
    }
    
    /**
    * Retrieves a list of tiles adjacent to the specified current tile.
    *
    * @param currentTile The current {@link Tile} for which adjacent tiles will be retrieved.
    * @return A list of adjacent {@link Tile} objects.
    */
    public List<Tile> getAdjacentTiles(Tile currentTile) {
        return worldManager.getAdjacentTiles(currentTile);  // Get adjacent tiles from WorldManager
    }
    
    /**
    * Displays information about the player's current environment, 
    * including the current tile name, description, and NPC details if present.
    *
    * @return A {@code String} containing the environment details.
    */
    public String displayCurrentEnvironment() {
    	
        // Get the player's current tile
        Tile currentTile = worldManager.getPlayerTile();

        if (currentTile != null) {
            StringBuilder environmentInfo = new StringBuilder();
            
            if(inventoryManager.hasRequiredAncientItems()) {
        		environmentInfo.append("You have Found the Ancient Items.\n" + "You now have the power to kill god.\nYou Win.");
        		return environmentInfo.toString();
        	}
            
            // Add environment details to the string
            environmentInfo.append("").append(currentTile.getName()).append("\n\n");
            environmentInfo.append(currentTile.getDescription()).append("\n\n");
            if(currentTile.hasNPC()) {
            	environmentInfo
            	.append("NPC Name: " + npcManager.getNPCOnTile(currentTile).getName() + "\n")
            	.append(npcManager.getNPCOnTile(currentTile).getDescription())
            	.append("\n\n");
            }

            // Update the GUI with the environment information
            return environmentInfo.toString();
        } else {
            return "You are not currently on a valid tile. You should NEVER see this Message.";
        }
    }

    
    /**
    * Allows the player to search the current tile for loot or an enemy encounter.
    * A random chance determines whether loot or an enemy is encountered.
    *
    * @return A message indicating the result of the search (loot found, enemy encountered, or no loot/enemy found).
    */
    public String searchCurrentTile() {
        Tile currentTile = worldManager.getPlayerTile();
        
        if (currentTile != null) {
            Random random = new Random();
            int chance = random.nextInt(2); // Generates either 0 or 1
            
            if (chance == 0) {
                // LOOT EVENT
                if (!currentTile.getLootPool().isEmpty()) {
                    // Randomly pick a loot item from the tile's loot pool
                    String loot = currentTile.getLootPool().get(random.nextInt(currentTile.getLootPool().size()));
                    // Add loot item to inventory
                    inventoryManager.foundItem(loot);
                    // Remove loot item from loot pool
                    currentTile.removeItem(loot);
					return ("You found loot: " + loot);
                } else {
                	return ("No loot found on this tile.");
                }
            } else {
                // ENEMY ENCOUNTER
                if (!currentTile.getEnemies().isEmpty()) {
                    return combatManager.combat();
                } else {
                	return ("No enemies on this tile.");
                }
            }
        } else {
        	System.out.println("Player is not on a valid tile."); 
        	return "";
            
        }
        
    }
    
    /**
    * Allows the player to examine the current tile for searchable information.
    *
    * @return A message containing the searchable information from the current tile, 
    *         or a message indicating no information was found.
    */
    public String examineCurrentTile() {
    	//TODO Should Display the searchable info from the current Tile.
    	Tile currentTile = worldManager.getPlayerTile();
    	if (currentTile != null) {
    		return currentTile.getSearchableInfo();
    	}
    	return "You Found No Info";
    }
}

    