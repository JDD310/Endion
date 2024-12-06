package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    private final String name;
    private final String description;
    private int health;
    private final List<Attack> attacks;

    // Constructor
    public Enemy(String name, String description, int health, List<Attack> attacks) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.attacks = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }
    
    // Set Health
    public void setHealth(int ammount) {
    	this.health = ammount;
    }
    
    // Attack method (returns a random attack)
    public Attack attack() {
        Random random = new Random();
        return attacks.get(random.nextInt(attacks.size()));
    }
}
