package managers;

import entities.Attack;
import entities.Enemy;
import entities.Player;

import java.util.List;
import java.util.Random;

public class CombatManager {

    private final Player player;
    private final AttackManager attackManager;
    private final EnemyManager enemyManager;
    private final GUIManager guiManager;

    // Constructor
    public CombatManager(Player player, AttackManager attackManager, EnemyManager enemyManager, GUIManager guiManager) {
        this.player = player;
        this.attackManager = attackManager;
        this.enemyManager = enemyManager;
        this.guiManager = guiManager;
    }

	// Select an Enemy from the Current Tile
    public Enemy selectEnemy() {
    	return enemyManager.getRandomEnemyFromTile(player.getX(), player.getY());
    }
    
    
    // Start automated combat with a specific enemy
    public void startCombat(Enemy enemy) {
        appendToOutput("Combat begins against: " + enemy.getName() + "\n");
        appendToOutput(enemy.getDescription());

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            // Player's turn
            performPlayerAttack(enemy);

            if (enemy.getHealth() <= 0) {
                appendToOutput(enemy.getName() + " has been defeated!");
                break;
            }

            // Enemy's turn
            performEnemyAttack(enemy);

            if (player.getHealth() <= 0) {
                appendToOutput("You have been defeated...");
                break;
            }
        }
    }

    // Perform a random attack from the player
    private void performPlayerAttack(Enemy enemy) {
        List<Attack> playerAttacks = player.getAttacks();
        if (playerAttacks.isEmpty()) {
            appendToOutput("No attacks available for the player.");
            return;
        }

        // Randomly select an attack
        Random random = new Random();
        Attack chosenAttack = playerAttacks.get(random.nextInt(playerAttacks.size()));

        // Calculate and apply damage
        List<Integer> damageRange = chosenAttack.getDamageRange();
        
        int minDamage = damageRange.get(0);
        int maxDamage = damageRange.get(1);

        Random rand = new Random();
        int damage =  rand.nextInt((maxDamage - minDamage) + 1) + minDamage;
        
        switch(chosenAttack.getType()) {
        case "heal":
        	enemy.healEnemy(damage);
        	break;
        case "hurt":
        	enemy.hurtEnemy(damage);
        	break;
        }
        appendToOutput(player.getName() + " used " + chosenAttack.getName() + " and dealt " + damage + " damage to " + enemy.getName() + ".");
    }

    // Perform a random attack from the enemy
    private void performEnemyAttack(Enemy enemy) {
        List<Attack> enemyAttacks = enemy.getAttacks();
        if (enemyAttacks.isEmpty()) {
            appendToOutput("No attacks available for the enemy.");
            return;
        }

        // Randomly select an attack
        Random random = new Random();
        Attack chosenAttack = enemyAttacks.get(random.nextInt(enemyAttacks.size()));

        // Calculate and apply damage
        List<Integer> damageRange = chosenAttack.getDamageRange();
        
        int minDamage = damageRange.get(0);
        int maxDamage = damageRange.get(1);

        Random rand = new Random();
        int damage =  rand.nextInt((maxDamage - minDamage) + 1) + minDamage;
        
        switch(chosenAttack.getType()) {
        case "heal":
        	player.healPlayer(damage);
        	break;
        case "hurt":
        	player.hurtPlayer(damage);
        	break;
        }
        appendToOutput(enemy.getName() + " used " + chosenAttack.getName() + " and dealt " + damage + " damage to" + player.getName() + ".");
    }

    // Append output to the GUI text area
    private void appendToOutput(String message) {
        guiManager.updateMainMenuText(message + "\n");
    }
}
