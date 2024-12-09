package core;

import java.util.List;

public class Config {
	
	// Change these Values to Change the Starting Config for the player. May Eventually add a difficulty setting to change 
	// Automatically
	
	private static Difficulty difficulty;
	private String playerSpawnLocation; // Default Spawn is city, this is Changed by SpawnMenu GUI.
	private final int playerStartingHealth;
    private final List<String> playerStartingAttacks;
    private final float healthMultiplier;
    private final float damageMultiplier;
    
    // Assigning Coordinates to Spawn Locations
   	private final static int[] citySpawn = new int[]{3, 1};
   	private final static int[] mountainSpawn = new int[]{2, 4};
   	private final static int[] forestSpawn = new int[]{1, 7};
		
    public Config(int playerHealth, List<String> playerAttacks, float healthMultiplier, float damageMultiplier) {
        this.playerStartingHealth = playerHealth;
        this.playerStartingAttacks = playerAttacks;
        this.healthMultiplier = healthMultiplier;
        this.damageMultiplier = damageMultiplier;
    }
    
    // Getter for spawn location info, default spawn is city.
    public static int[] getSpawn(String spawn) {
    	switch(spawn.toLowerCase()) {
    	case "city":
    		return citySpawn;
    	case "mountain":
    		return mountainSpawn;
    	case "forest":
    		return forestSpawn;
    	default:
    		return citySpawn;
    	}
    	
    		
    }
    
    public static Difficulty getDiff() {
    	return difficulty;
    }
    
    public static void setDifficulty(Difficulty diff) {
    	difficulty = diff;
    }

	public int getPlayerStartingHealth() {
		return playerStartingHealth;
	}

	public List<String> getPlayerStartingAttackSet() {
		return playerStartingAttacks;
	}

	public String getPlayerSpawnLocation() {
		return playerSpawnLocation;
	}

	public void setPlayerSpawnLocation(String playerSpawnLocation) {
		this.playerSpawnLocation = playerSpawnLocation;
	}

	public float getHealthMultiplier() {
		return healthMultiplier;
	}

	public float getDamageMultiplier() {
		return damageMultiplier;
	}
}
