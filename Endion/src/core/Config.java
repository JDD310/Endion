package core;

import java.util.List;

public class Config {
	
	// Change these Values to Change the Starting Config for the player. May Eventually add a difficulty setting to change 
	// Automatically
	
	private static Difficulty difficulty;
	private String playerSpawnLocation = "city"; // Default Spawn is city, this is Changed by SpawnMenu GUI.
	private final int playerStartingHealth;
    private final List<String> playerStartingAttacks;
    private final float healthMultiplier;
    private final float damageMultiplier;
		
    public Config(int playerHealth, List<String> playerAttacks, float healthMultiplier, float damageMultiplier) {
        this.playerStartingHealth = playerHealth;
        this.playerStartingAttacks = playerAttacks;
        this.healthMultiplier = healthMultiplier;
        this.damageMultiplier = damageMultiplier;
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
