package managers;

import entities.Attack;
import entities.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* The {@code AttackManager} class is responsible for managing all the attacks in the game.
* It allows retrieval of specific attacks and provides access to the entire set of attacks.
* 
* @author JD Dennis
*/
public class AttackManager {

    private final Map<String, Attack> attackMap; // Stores all attack objects by name

    /**
    * Constructs an {@code AttackManager} with the specified map of attacks.
    *
    * @param attacks A map of attack names to their corresponding {@link Attack} objects.
    */
    public AttackManager(Map<String, Attack> attacks) {
        this.attackMap = attacks;
    }

    /**
    * Retrieves a specific attack by its name.
    *
    * @param name The name of the attack to retrieve.
    * @return The {@link Attack} object corresponding to the specified name, 
    *         or {@code null} if the attack does not exist.
    */
    public Attack getAttack(String name) {
        return attackMap.getOrDefault(name, null); // Return null if attack not found
    }

    /**
    * Retrieves all attacks stored in the manager.
    *
    * @return A copy of the map containing all attack names mapped to their respective {@link Attack} objects.
    */
    public Map<String, Attack> getAllAttacks() {
        return new HashMap<>(attackMap); // Return a copy of the map to avoid external modification
    }
    
    /**
    * Adds a set of basic attacks to the player's attack list.
    *
    * @param player The {@link Player} to whom the attacks will be added.
    * @param allAttacks A map of all available attack names to their corresponding {@link Attack} objects.
    * @param playerStartingAttackSet A list of attack names that represent the player's starting attack set.
    */
    public static void addBasicAttacksToPlayer(Player player, Map<String, Attack> allAttacks, List<String> playerStartingAttackSet) {
    	
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
