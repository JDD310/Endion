package managers;

import java.util.List;

import entities.Player;
import entities.Tile;

public class EnvironmentManager {

    private Player player;
    private WorldManager worldManager;

    // The name or identifier of the current environment
    private String currentEnvironment;

    // Constructor
    public EnvironmentManager(Player player, WorldManager worldManager) {
    	this.player = player;
        this.worldManager = worldManager;
    }

    // Initialize the environment manager
    public void initializeEnvironment() {
        displayCurrentEnvironment();
        System.out.println("Initialized environment: " + currentEnvironment);
    }
    
    // Get the list of adjacent tiles to the current tile
    public List<Tile> getAdjacentTiles(Tile currentTile) {
        return worldManager.getAdjacentTiles(currentTile);  // Get adjacent tiles from WorldManager
    }
    
    // Display information about the current environment
    public String displayCurrentEnvironment() {
        // Get the player's current tile
        Tile currentTile = getPlayerTile();

        if (currentTile != null) {
            StringBuilder environmentInfo = new StringBuilder();

            // Add environment details to the string
            environmentInfo.append("").append(currentTile.getName()).append("\n");
            environmentInfo.append(currentTile.getDescription()).append("\n");

            environmentInfo.append("NPCs Present: ").append(currentTile.hasNPC() ? "Yes" : "No").append("\n");

            // Update the GUI with the environment information
            return environmentInfo.toString();
        } else {
            return "You are not currently on a valid tile. You should NEVER see this Message.";
        }
    }

    // Get the tile where the player is currently located
    private Tile getPlayerTile() {
        int[] position = player.getPos(); // Player position as (x, y)
        return worldManager.getTile(position[0], position[1]); // Fetch the tile from the world map
    }
    
    // Search Area Function
    public void searchCurrentTile() {
    	//TODO
    	/* 
    	This should select at random, to either be loot or an enemy. once it has selected which, it should randomly
    	pick one from the lists and then either allow the player to obtain the item
    	*/ 
    }
    
    // Examine Area Function
    public void examineCurrentTile() {
    	//TODO Should Display the searchable info from the current Tile.
    }
}

    