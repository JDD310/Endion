package managers;

import java.util.List;

import entities.Player;
import entities.Quest;

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

    // Heal the player by a specified amount
    public void healPlayer(int amount) {
        int newHealth = (player.getHealth() + amount);
        player.setHealth(newHealth);
    }

    // Damage the player by a specified amount
    public void hurtPlayer(int amount) {
        int newHealth = (player.getHealth() - amount);
        player.setHealth(newHealth);
    }

    // Add an item to the player's inventory
    public void addItemToInventory(String item) {
        player.addItem(item);
    }

    // Remove an item from the player's inventory
    public void removeItemFromInventory(String item) {
        List<String> inventory = player.getInventory();
        if (inventory.contains(item)) {
            inventory.remove(item);
        }
    }

    // Add a skill to the player
    public void addAttack(String skill) {
        player.addAttack(skill);
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
    
    // Check if the player is alive
    public boolean isPlayerAlive() {
        return player.isAlive();
    }
    
    // Attack method (returns a random attack)
    public String attack() {
        return player.attack();
    }
}
