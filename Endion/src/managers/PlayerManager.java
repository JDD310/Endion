package managers;

import java.util.List;

import entities.Attack;
import entities.Item;
import entities.Player;
import entities.Quest;
import utilities.GameUtils;

public class PlayerManager {

    private Player player;
    
    // Constructor to initialize PlayerManager with a player
    public PlayerManager(Player player) {
        this.player = player;
    }

    // Set the player (if needed for reinitialization)
    public void setPlayer(Player player) {
        this.player = player;
    }

    // Get the current player
    public Player getPlayer() {
        return player;
    }

    // Add an item to the player's inventory
    public void addItemToInventory(Item item) {
        player.addItem(item);
    }

    // Remove an item from the player's inventory
    public void removeItemFromInventory(Item item) {
        List<Item> inventory = player.getInventory();
        if (inventory.contains(item)) {
            inventory.remove(item);
        }
    }
    
    // Get list of player quests as quest names 
    public List<String> getInventoryAsString() {
    	return GameUtils.convertToNameList(player.getInventory());
    }

    // Add an Attack to the player's list of attacks
    public void addAttack(Attack attack) {
        player.addAttack(attack);
    }
    
    // Add multiple attacks to the player's list of attacks
    public void addAttacks(List<Attack> attacksList) {
    	for (Attack attack : attacksList) {
            addAttack(attack); // Assumes Player has an addAttack method
        }
    }
    
    // Get list of player attacks as attack names 
    public List<String> getAttacksAsString() {
    	return GameUtils.convertToNameList(player.getAttacks());
    }
    
    // Add a quest to the player's active quests
    public void addQuest(Quest quest) {
        player.addQuest(quest);
    }

    // Complete a quest for the player
    public void completeQuest(Quest quest) {
        if (player.getActiveQuests().contains(quest)) {
            quest.completeQuest();
        }
    }
    
    // Get list of player quests as quest names 
    public List<String> getActiveQuestsAsString() {
    	return GameUtils.convertToNameList(player.getActiveQuests());
    }
    
    // Check if the player is alive
    public boolean isPlayerAlive() {
        return player.isAlive();
    }
}
