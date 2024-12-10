package managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import core.Config;

import entities.Player;
import entities.Tile;

/**
* The {@code WorldManager} class manages the structure and layout of the game world.
* It handles player movement, tile interactions, and world initialization.
*/
public class WorldManager {

    private Tile[][] worldMap;  // 2D array of tiles (map)
    private Player player;
    private final int worldWidth = 9;  
    private final int worldHeight = 5;
    private Map<String, Tile> tilesMap;
    

    /**
    * Constructs a {@code WorldManager} with the specified player and tile data.
    *
    * @param player The {@link Player} for whom the world is managed.
    * @param tiles A map of tile names mapped to their corresponding {@link Tile} objects.
    */
    public WorldManager(Player player, Map<String, Tile> tiles) {
        this.player = player;
        this.worldMap = new Tile[worldHeight][worldWidth];
        this.tilesMap = tiles;
        populateWorldMap(tilesMap);
    }
    
    /**
    * Populates the world map using the provided tile data.
    *
    * @param tiles A map of tile names mapped to their corresponding {@link Tile} objects.
    */
    public void populateWorldMap(Map<String, Tile> tiles) { 
    	// Populate the worldMap array using the tiles data
        for (Tile tile : tiles.values()) {
            // Get the tile's grid location (x, y) from the Tile object
            int[] gridLocation = tile.getGridLocation();
            int x = gridLocation[0];
            int y = gridLocation[1];

            // Place the tile in the worldMap at the coordinates
            if (x >= 0 && x < worldHeight && y >= 0 && y < worldWidth) {
                worldMap[x][y] = tile;
//                System.out.println(tile.getName() + "" + tile.getDescription() + "" + tile.getSearchableInfo() + "" + GameUtils.convertToNameList(tile.getEnemies()).toString() + tile.getLootPool().toString());
            // Just in Case
            } else {
                System.out.println("Invalid grid location for tile: " + tile.getName());
            }
        }
    }
    
    /**
    * Retrieves the {@link Tile} where the player is currently located.
    *
    * @return The current {@link Tile} the player is on, or {@code null} if no tile exists at the player's position.
    */
    public Tile getPlayerTile() {
        int[] position = player.getPos(); // Player position as (x, y)
        return getTile(position[0], position[1]); // Fetch the tile from the world map
    }

    /**
    * Retrieves a specific {@link Tile} by its coordinates.
    *
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    * @return The {@link Tile} at the specified coordinates, or {@code null} if the coordinates are out of bounds.
    */
    public Tile getTile(int x, int y) {
        if (x >= 0 && x < worldHeight && y >= 0 && y < worldWidth) {
            return worldMap[x][y];
        }
        return null;  // Return null if coordinates are out of bounds
    }

    /**
    * Retrieves the list of tiles adjacent to the specified {@link Tile}.
    *
    * @param tile The {@link Tile} for which adjacent tiles are to be found.
    * @return A list of adjacent {@link Tile} objects.
    */
    public List<Tile> getAdjacentTiles(Tile tile) {
    	
    	// Creating an Empty ArrayList that can be Filled with Tile Objects
        List<Tile> adjacentTiles = new ArrayList<>();

        // Get the tile's current position
        int[] position = tile.getGridLocation();
        int x = position[0];
        int y = position[1];

        // Add adjacent tiles (if within bounds)
        if (x > 0) adjacentTiles.add(getTile(x - 1, y)); // Tile above
        if (x < worldHeight - 1) adjacentTiles.add(getTile(x + 1, y)); // Tile below
        if (y > 0) adjacentTiles.add(getTile(x, y - 1)); // Tile to the left
        if (y < worldWidth - 1) adjacentTiles.add(getTile(x, y + 1)); // Tile to the right

        return adjacentTiles;
    }
    
    /**
    * Moves the player to a new position based on the provided coordinates.
    *
    * @param pos An array containing the x and y coordinates of the player's new position.
    */
    public void setPlayerLocation(int[] pos) {
    	player.setPos(pos);
    }
    
    /**
    * Moves the player to an adjacent tile in the specified direction.
    * Valid directions are "NORTH", "SOUTH", "EAST", and "WEST".
    *
    * @param direction The direction to move the player.
    */
    public void move(String direction) {
        // Get the player's current tile
        Tile currentTile = getTile(player.getX(), player.getY());
        if (currentTile == null) {
            // Just Don't move player, maybe add a out of bounds message.
            return;
        }

        // Get adjacent tiles
        List<Tile> adjacentTiles = getAdjacentTiles(currentTile);

        // Determine the target tile based on the direction
        Tile targetTile = null;
        for (Tile tile : adjacentTiles) {
            int[] targetPosition = tile.getGridLocation();
            int[] currentPosition = player.getPos();

            // Check for the tile matching the given direction
            switch (direction.toUpperCase()) {
                case "NORTH":
                	if (targetPosition[0] == currentPosition[0] && targetPosition[1] == currentPosition[1] - 1) {
                        targetTile = tile;
                    }
                    break;
                case "SOUTH":
                    
                    if (targetPosition[0] == currentPosition[0] && targetPosition[1] == currentPosition[1] + 1) {
                        targetTile = tile;
                    }
                    break;
                case "EAST":
                	if (targetPosition[0] == currentPosition[0] + 1 && targetPosition[1] == currentPosition[1]) {
                        targetTile = tile;
                    }
                    break;
                case "WEST":
                    
                    if (targetPosition[0] == currentPosition[0] - 1 && targetPosition[1] == currentPosition[1]) {
                        targetTile = tile;
                    }
                    break;
                default:
                    // Shouldn't ever happen because of buttons but adding it here just in case
                    return;
            }
        }

        // If a valid target tile is found, move the player
        if (targetTile != null) {
            player.setPos(targetTile.getGridLocation());
        // If I need to trigger environment updates or interactions I'll put them here
        }
            
    }


    /**
    * Prints a representation of the world map, displaying each tile's name.
    * This method is intended for debugging purposes.
    */
    public void printWorldMap() {
        for (int i = 0; i < worldHeight; i++) {
            for (int j = 0; j < worldWidth; j++) {
                if (worldMap[i][j] != null) {
                    System.out.print(worldMap[i][j].getName() + " ");
                } else {
                    System.out.print("Empty ");
                }
            }
            System.out.println();
        }
    }
    
    /**
    * Sets the player's starting position using a spawn location identifier.
    * The spawn location is retrieved from the {@link Config} class.
    *
    * @param spawn The name of the spawn location.
    */
  	public void spawnPlayer(String spawn) {
  		int[] spawnLoc = Config.getSpawn(spawn);
  	    setPlayerLocation(spawnLoc);
  	}
}
