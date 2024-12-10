package managers;


import java.util.List;
import java.util.Map;
import java.util.Random;



import entities.Enemy;
import entities.Tile;

/**
* The {@code EnemyManager} class is responsible for managing enemy interactions within the game world.
* It provides methods to add, remove, and retrieve enemies on specific tiles.
* 
* @author JD Dennis
*/
public class EnemyManager {

	private final Map<String, Enemy> allEnemies; // Unique enemies loaded from data
    private final WorldManager worldManager; // Reference to WorldManager for tile access
    
    /**
    * Constructs an {@code EnemyManager} with the specified world manager and enemy data.
    *
    * @param worldManager The {@link WorldManager} used to access the tiles in the game world.
    * @param enemyData A map containing enemy names mapped to their corresponding {@link Enemy} objects.
    */
    public EnemyManager(WorldManager worldManager, Map<String, Enemy> enemyData) {
        this.worldManager = worldManager;
        this.allEnemies = enemyData;
    }
   
    
    /**
    * Retrieves a list of enemies on a specific tile, identified by its coordinates.
    *
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    * @return A list of {@link Enemy} objects on the specified tile, or {@code null} if the tile does not exist.
    */
    public List<Enemy> getEnemiesForTile(int x, int y) {
        Tile tile = worldManager.getTile(x, y);
        return tile != null ? tile.getEnemies() : null;
    }
    
    /**
    * Selects a random enemy from the tile at the specified coordinates.
    *
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    * @return A randomly selected {@link Enemy} from the tile, or {@code null} if no enemies are present.
    */
    public Enemy getRandomEnemyFromTile(int x, int y) {
        List<Enemy> enemies = getEnemiesForTile(x, y);
        if (!enemies.isEmpty()) {
            Random random = new Random();
            return enemies.get(random.nextInt(enemies.size()));
        }
        return null;
    }

    /**
    * Adds an enemy to the specified tile.
    *
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    * @param enemyName The name of the enemy to add.
    */
    public void addEnemyToTile(int x, int y, String enemyName) {
        Tile tile = worldManager.getTile(x, y);
        if (tile != null && allEnemies.containsKey(enemyName)) {
            tile.addEnemy(allEnemies.get(enemyName));
        } else {
            System.out.println("Failed to add enemy: Invalid tile or enemy name.");
        }
    }

    /**
    * Removes a specified enemy from the tile at the given coordinates.
    *
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    * @param enemy The {@link Enemy} to remove from the tile.
    */
    public void removeEnemyFromTile(int x, int y, Enemy enemy) {
        Tile tile = worldManager.getTile(x, y);
        if (tile != null) {
            tile.removeEnemy(enemy);
        }
    }

    /**
    * Clears all enemies from the tile at the given coordinates.
    *
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    */
    public void clearEnemiesFromTile(int x, int y) {
        Tile tile = worldManager.getTile(x, y);
        if (tile != null) {
            tile.clearEnemies();
        }
    }
    
    /**
    * Retrieves a map containing all unique enemies available in the game.
    *
    * @return A map where the keys are enemy names and the values are their respective {@link Enemy} objects.
    */
    public Map<String, Enemy> getAllEnemies() {
        return allEnemies;
    }
}