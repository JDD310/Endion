package managers;

import java.util.Map;

import entities.NPC;
import entities.Tile;

/**
* The {@code NPCManager} class is responsible for managing NPC interactions 
* and tracking NPC positions within the game world.
* 
* @author JD Dennis
*/
public class NPCManager {
	
	private WorldManager worldManager;
	private Map<String,NPC> npcMap;
	
	/**
	* Constructs an {@code NPCManager} with the specified NPC data and world manager.
	*
	* @param npcs A map of NPC names mapped to their respective {@link NPC} objects.
	* @param worldManager The {@link WorldManager} used to access the tiles in the game world.
	*/
	public NPCManager(Map<String,NPC> npcs, WorldManager worldManager) {
		this.npcMap = npcs;
		this.worldManager = worldManager;
	}
	
	/**
	* Finds and returns the NPC located at the specified grid position.
	*
	* @param input An int array of [x, y] coordinates to check.
	* @return The NPC at the given position, or null if no NPC is found.
	*/
	public NPC getNPCAtGridLocation(int[] input) {
		if (input == null || input.length != 2) {
			System.out.println("Warning: getNPCAtGridLocation returned Null due to invalid input coordinates. ");
	        return null;
	    }

	    for (NPC npc : npcMap.values()) { // Assuming npcMap is a Map<String, NPC> in NPCManager
	        int[] npcLocation = npc.getGridLocation();
	        if (npcLocation != null 
	        		&& npcLocation.length == 2 
	        		&& npcLocation[0] == input[0] 
	        		&& npcLocation[1] == input[1]) {
	            return npc;
	        }
	    }
	    System.out.println("Warning: getNPCAtGridLocation returned Null because there is no NPC at " + input);
		return null;
	}
	
	/**
	* Finds and returns the NPC present on the given tile.
	*
	* @param currentTile The tile to check for an NPC.
	* @return The NPC on the given tile, or null if no NPC is found.
	*/
	public NPC getNPCOnTile(Tile tile) {
	    if (tile == null) {
	        System.out.println("Warning for getNPCOnTile: Tile is null.");
	        return null;
	    }

	    int[] tileLocation = tile.getGridLocation(); // Get the location of the current tile

	    for (NPC npc : npcMap.values()) { // Assuming npcMap is a Map<String, NPC> in NPCManager
	        int[] npcLocation = npc.getGridLocation();
	        if (npcLocation != null 
	            && npcLocation.length == 2 
	            && npcLocation[0] == tileLocation[0] 
	            && npcLocation[1] == tileLocation[1]) {
	            return npc;
	        }
	    }

	    System.out.println("Warning for getNPCOnTile: No NPC found at the specified tile " + tile.getName());
	    return null;
	}
	
	/**
	* Allows the player to speak to the {@link NPC} on the current tile.
	*
	* @return The dialogue of the {@link NPC}, or {@code null} if no NPC is present on the tile.
	*/
	public String speakToNPC() {
		Tile currentTile = worldManager.getPlayerTile();
		if (currentTile == null) {
	        System.out.println("Warning for speakToNPC: Tile is null.");
	        return null;
	    }
		if (currentTile.hasNPC()) {
			NPC currentNPC = getNPCOnTile(currentTile);
			return currentNPC.speak();
		}
		
		return null;
	}
	
	
}
