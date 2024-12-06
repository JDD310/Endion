package managers;

import entities.Attack;
import entities.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttackManager {

    private final Map<String, Attack> attackMap; // Stores all attack objects by name

    // Constructor
    public AttackManager(Map<String, Attack> attacks) {
        this.attackMap = attacks;
    }

    // Retrieves a specific attack by name
    public Attack getAttack(String name) {
        return attackMap.getOrDefault(name, null); // Return null if attack not found
    }

    // Retrieves all attacks
    public Map<String, Attack> getAllAttacks() {
        return new HashMap<>(attackMap); // Return a copy of the map to avoid external modification
    }
    
    // Helper Method to Create the Player's Starting Attack Set.
    public static void addBasicAttacksToPlayer(Player player, Map<String, Attack> allAttacks, String[] playerStartingAttackSet) {
    	
        // Create a list to hold the attacks
        List<Attack> basicAttacks = new ArrayList<>();

        // Retrieve the attacks from the map using their names
        for (String attackName : playerStartingAttackSet) {
            if (allAttacks.containsKey(attackName)) {
                basicAttacks.add(allAttacks.get(attackName));
            } else {
                System.out.println("Warning: Attack " + attackName + " not found in allAttacks.");
            }
        }

        // Add the basic attacks to the player's attack set
        for (Attack attack : basicAttacks) {
            player.addAttack(attack);
        }
    }
}
