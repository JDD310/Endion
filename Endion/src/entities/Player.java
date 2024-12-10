package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Entity {

    private float health;
    private List<Item> inventory;
    private List<Attack> attacks;
    private List<Quest> activeQuests;
    private int[] playerPosition;

    // Constructor
    public Player(String name, int health) {
        super(name);
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

    public float getHealth() {
        return health;
    }

    public List<Item> getInventory() {
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
    

    // set Health to a given Value
    public void setHealth(float ammount) {
        this.health = ammount;
        if (this.health < 0) {
            this.health = 0; // Prevent health from going below 0
        }
    }
    
    // Heal the player by a specified amount
    public void healPlayer(int amount) {
        float newHealth = (getHealth() + amount);
        setHealth(newHealth);
    }

    // Damage the player by a specified amount
    public void hurtPlayer(int amount) {
        float newHealth = (getHealth() - amount);
        setHealth(newHealth);
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
    public void addItem(Item item) {
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
