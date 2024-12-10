package entities;


import java.util.List;

public class Tile extends Entity {

    private final String description;
    private final String searchableInfo;
    private List<String> lootPool;
    private List<Enemy> enemies; // List of enemies on this tile
    private final boolean hasNPC;
    private final int[] gridLocation;

    // Constructor
    public Tile(String name, String description, String searchableInfo, List<String> lootPool, List<Enemy> enemies, boolean hasNPC, int[] gridLocation) {
        super(name);
        this.description = description;
        this.searchableInfo = searchableInfo;
        this.lootPool = lootPool;
        this.enemies = enemies; // Initialize empty enemy list
        this.hasNPC = hasNPC;
        this.gridLocation = gridLocation;
    }
    
    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSearchableInfo() {
        return searchableInfo;
    }

    public List<String> getLootPool() {
        return lootPool;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
    
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
    
    public void addItem(String item) {
        lootPool.add(item);
    }

    public void removeItem(String item) {
        lootPool.remove(item);
    }
    
    public void clearEnemies() {
        enemies.clear();
    }
    
    public boolean hasNPC() {
        return hasNPC;
    }

    public int[] getGridLocation() {
        return gridLocation;
    }
}
