package managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Attack;
import entities.Enemy;
import entities.Player;
import entities.Tile;
import utilities.DataLoader;

public class WorldManager {

    private Tile[][] worldMap;  // 2D array of tiles (map)
    private Player player;
    private final int worldWidth = 9;  
    private final int worldHeight = 5;
    private Map<String, Tile> tiles;

    // Constructor
    public WorldManager(Player player, Map<String, Tile> tiles) {
        this.player = player;
        this.worldMap = new Tile[worldHeight][worldWidth];
        this.tiles = tiles;
        populateWorldMap(tiles);
    }
    
    // Populate WorldMap
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
            // Just in Case
            } else {
                System.out.println("Invalid grid location for tile: " + tile.getName());
            }
        }
    }

    // Get a specific tile by its coordinates
    public Tile getTile(int x, int y) {
        if (x >= 0 && x < worldHeight && y >= 0 && y < worldWidth) {
            return worldMap[x][y];
        }
        return null;  // Return null if coordinates are out of bounds
    }

    // Get adjacent tiles (up, down, left, right)
    public List<Tile> getAdjacentTiles(Tile tile) {
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
    
    // Moves player to a tile based on coordinate input.
    public void setPlayerLocation(int[] pos) {
    	player.setPos(pos);
    }
    
    // Moves player to tile based on a directional input.
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
                    if (targetPosition[0] == currentPosition[0] - 1 && targetPosition[1] == currentPosition[1]) {
                        targetTile = tile;
                    }
                    break;
                case "SOUTH":
                    if (targetPosition[0] == currentPosition[0] + 1 && targetPosition[1] == currentPosition[1]) {
                        targetTile = tile;
                    }
                    break;
                case "EAST":
                    if (targetPosition[0] == currentPosition[0] && targetPosition[1] == currentPosition[1] + 1) {
                        targetTile = tile;
                    }
                    break;
                case "WEST":
                    if (targetPosition[0] == currentPosition[0] && targetPosition[1] == currentPosition[1] - 1) {
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


    // Print the world map (for debugging purposes)
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
}
