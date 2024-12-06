package core;

public class GameConfig {
	
	// Change these Values to Change the Starting Config for the player. May Eventually add a difficulty setting to change 
	// Automatically
	
	private String[] playerStartingAttackSet = {
            "Sword Slash", "Sword Slash+", "Dagger Slash", "Dagger Slash+",
            "Fireball", "Water Ball", "Air Palm", "Mean Elbow"
        };
	
	private int playerStartingHealth = 100;
	
	private String playerSpawnLocation = "city"; // Default Spawn is city, this is Changed by SpawnMenu GUI.
	

	public int getPlayerStartingHealth() {
		return playerStartingHealth;
	}


	public void setPlayerStartingHealth(int playerStartingHealth) {
		this.playerStartingHealth = playerStartingHealth;
	}


	public String[] getPlayerStartingAttackSet() {
		return playerStartingAttackSet;
	}


	public void setPlayerStartingAttackSet(String[] playerStartingAttackSet) {
		this.playerStartingAttackSet = playerStartingAttackSet;
	}


	public String getPlayerSpawnLocation() {
		return playerSpawnLocation;
	}


	public void setPlayerSpawnLocation(String playerSpawnLocation) {
		this.playerSpawnLocation = playerSpawnLocation;
	}
}
