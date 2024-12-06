package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private int health;
    private List<String> inventory;
    private List<Attack> attacks;
    private List<Quest> activeQuests;
    private int[] playerPosition;

    // Constructor
    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.inventory = new ArrayList<>();
        this.attacks = new ArrayList<>();
        this.activeQuests = new ArrayList<>();
        this.playerPosition = new int[2];
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public List<Quest> getActiveQuests() {
        return activeQuests;
    }
    
    public int[] getPos() {
    	return playerPosition;
    }
    
    public int getX() {
        return playerPosition[0];
    }

    public int getY() {
        return playerPosition[1];
    }
    

    // Update health
    public void setHealth(int ammount) {
        this.health -= ammount;
        if (this.health < 0) {
            this.health = 0; // Prevent health from going below 0
        }
    }
    
    // Check if the player is alive
    public boolean isAlive() {
        return health > 0;
    }

    // Setter for x-coordinate
    public void setX(int x) {
        playerPosition[0] = x;
    }

    // Setter for y-coordinate
    public void setY(int y) {
        playerPosition[1] = y;
    }
    
    // Setter for Position
    public void setPos(int[] pos) {
    	playerPosition = pos;
    }
    
    // Add a quest to the active quests list
    public void addQuest(Quest quest) {
        activeQuests.add(quest);
    }

    // Add an item to the inventory
    public void addItem(String item) {
        inventory.add(item);
    }

    // Add an attack to the player's attack list
    public void addAttack(Attack attack) {
        attacks.add(attack);
    }
    
    // Attack method (returns a random attack)
    public Attack attack() {
        Random random = new Random();
        return attacks.get(random.nextInt(attacks.size()));
    }
}
