package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    private final String name;
    private final String description;
    private float health;
    private final List<Attack> attacks;

    // Constructor
    public Enemy(String name, String description, float health, List<Attack> attacks) {
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

    public float getHealth() {
        return health;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }
    
    // Set Health
    public void setHealth(float ammount) {
    	this.health = ammount;
    }
    
    // Heal the Enemy by a specified amount
    public void healEnemy(int amount) {
        float newHealth = (getHealth() + amount);
        setHealth(newHealth);
    }

    // Damage the Enemy by a specified amount
    public void hurtEnemy(int amount) {
        float newHealth = (getHealth() - amount);
        setHealth(newHealth);
    }
    
    // Attack method (returns a random attack)
    public Attack attack() {
        Random random = new Random();
        return attacks.get(random.nextInt(attacks.size()));
    }
}
