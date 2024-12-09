package managers;

import java.util.List;

import entities.Attack;
import entities.Player;
import entities.Quest;
import utilities.GameUtils;

/**
* The {@code PlayerManager} class manages player-related actions, 
* including managing player inventory, attacks, quests, and status.
*/
public class PlayerManager {

    private Player player;
    
    /**
    * Constructs a {@code PlayerManager} with the specified player.
    *
    * @param player The {@link Player} to be managed by this {@code PlayerManager}.
    */
    public PlayerManager(Player player) {
        this.player = player;
    }

    /**
    * Sets the {@link Player} being managed by this {@code PlayerManager}.
    *
    * @param player The new {@link Player} to be managed.
    */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
    * Retrieves the current {@link Player} being managed.
    *
    * @return The current {@link Player}.
    */
    public Player getPlayer() {
        return player;
    }
    
    /**
    * Retrieves the player's inventory as a list of item names.
    *
    * @return A list of item names currently in the player's inventory.
    */
    public List<String> getInventoryAsString() {
    	return GameUtils.convertToNameList(player.getInventory());
    }

    /**
    * Adds an {@link Attack} to the player's list of available attacks.
    *
    * @param attack The {@link Attack} to be added to the player's attack list.
    */
    public void addAttack(Attack attack) {
        player.addAttack(attack);
    }
    
    /**
    * Adds multiple {@link Attack} objects to the player's list of available attacks.
    *
    * @param attacksList A list of {@link Attack} objects to be added to the player's attack list.
    */
    public void addAttacks(List<Attack> attacksList) {
    	for (Attack attack : attacksList) {
            addAttack(attack); // Assumes Player has an addAttack method
        }
    }
    
    /**
    * Retrieves the player's attacks as a list of attack names.
    *
    * @return A list of attack names currently available to the player.
    */
    public List<String> getAttacksAsString() {
    	return GameUtils.convertToNameList(player.getAttacks());
    }
    
    /**
    * Adds a {@link Quest} to the player's active quests.
    *
    * @param quest The {@link Quest} to be added to the player's active quests.
    */
    public void addQuest(Quest quest) {
        player.addQuest(quest);
    }

    /**
    * Completes a {@link Quest} for the player.
    *
    * @param quest The {@link Quest} to be marked as complete.
    */
    public void completeQuest(Quest quest) {
        if (player.getActiveQuests().contains(quest)) {
            quest.completeQuest();
        }
    }
    
    /**
    * Retrieves the player's active quests as a list of quest names.
    *
    * @return A list of quest names that are currently active for the player.
    */
    public List<String> getActiveQuestsAsString() {
    	return GameUtils.convertToNameList(player.getActiveQuests());
    }
    
    /**
    * Checks if the player is still alive.
    *
    * @return {@code true} if the player is alive, {@code false} otherwise.
    */
    public boolean isPlayerAlive() {
        return player.isAlive();
    }
}
