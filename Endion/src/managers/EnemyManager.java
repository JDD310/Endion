package managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

import entities.Enemy;
import entities.Tile;
import utilities.DataLoader;

public class EnemyManager {

	private final Map<String, Enemy> allEnemies; // Unique enemies loaded from data
    private final WorldManager worldManager; // Reference to WorldManager for tile access
    
    // Constructor
    public EnemyManager(WorldManager worldManager, Map<String, Enemy> enemyData) {
        this.worldManager = worldManager;
        this.allEnemies = enemyData;
    }
   
    
    // Get enemies on a specific tile by coordinates
    public List<Enemy> getEnemiesForTile(int x, int y) {
        Tile tile = worldManager.getTile(x, y);
        return tile != null ? tile.getEnemies() : null;
    }
    
    // Get a random enemy from a specific tile by coordinates
    public Enemy getRandomEnemyFromTile(int x, int y) {
        List<Enemy> enemies = getEnemiesForTile(x, y);
        if (!enemies.isEmpty()) {
            Random random = new Random();
            return enemies.get(random.nextInt(enemies.size()));
        }
        return null;
    }

    // Add an enemy to a specific tile
    public void addEnemyToTile(int x, int y, String enemyName) {
        Tile tile = worldManager.getTile(x, y);
        if (tile != null && allEnemies.containsKey(enemyName)) {
            tile.addEnemy(allEnemies.get(enemyName));
        } else {
            System.out.println("Failed to add enemy: Invalid tile or enemy name.");
        }
    }

    // Remove an enemy from a specific tile
    public void removeEnemyFromTile(int x, int y, Enemy enemy) {
        Tile tile = worldManager.getTile(x, y);
        if (tile != null) {
            tile.removeEnemy(enemy);
        }
    }

    // Clear all enemies from a specific tile
    public void clearEnemiesFromTile(int x, int y) {
        Tile tile = worldManager.getTile(x, y);
        if (tile != null) {
            tile.clearEnemies();
        }
    }
    
    // Get all unique enemies
    public Map<String, Enemy> getAllEnemies() {
        return allEnemies;
    }
}