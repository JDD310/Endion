package managers;

import entities.Attack;
import entities.Enemy;
import entities.Player;
import gui.GUIHelper;
import java.util.List;
import java.util.Random;

/**
* The {@code CombatManager} class handles the combat logic between the player and enemies.
* It manages enemy selection, turn-based combat, and player and enemy attacks.
* 
* @author JD Dennis
*/
public class CombatManager {
	
//  private final AttackManager attackManager;
    private final Player player;
    private final EnemyManager enemyManager;
    private final GUIHelper guiHelper;
    private StringBuilder output = new StringBuilder();
    
    /**
    * Constructs a {@code CombatManager} with the specified player, enemy manager, and GUI helper.
    *
    * @param player The {@link Player} engaged in combat.
    * @param enemyManager The {@link EnemyManager} used to access enemy data.
    * @param guiHelper The {@link GUIHelper} for handling GUI-related actions.
    */
    public CombatManager(Player player, EnemyManager enemyManager, GUIHelper guiHelper) {
        this.player = player;
//        this.attackManager = attackManager;
        this.enemyManager = enemyManager;
        this.guiHelper = guiHelper;
    }
    
    /**
    * Initiates combat with an enemy on the player's current tile.
    *
    * @return The output of the combat sequence as a {@code String}.
    */
    public String combat() {
    	output.delete(0, output.length());
    	Enemy SelectedEnemy = selectEnemy();
    	startCombat(SelectedEnemy);
    	return output.toString();
    }

    /**
    * Selects a random enemy from the tile where the player is currently located.
    *
    * @return A randomly selected {@link Enemy} from the tile, or {@code null} if no enemies are present.
    */
    public Enemy selectEnemy() {
    	return enemyManager.getRandomEnemyFromTile(player.getX(), player.getY());
    }
    
    
    /**
    * Starts an automated turn-based combat between the player and a specified enemy.
    *
    * @param enemy The {@link Enemy} to engage in combat.
    */
    public void startCombat(Enemy enemy) {
    	
        if(enemy.getHealth() > 0) {
        	guiHelper.clearTextField();
        	appendToOutput("An enemy appears: " + enemy.getName());
            appendToOutput("Combat begins against: " + enemy.getName() + "");
            appendToOutput(enemy.getDescription());
        } else {
        	appendToOutput("No enemies on this tile.");
        }
        	
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            // Player's turn
            performPlayerAttack(enemy);

            if (enemy.getHealth() <= 0) {
                appendToOutput(enemy.getName() + " has been defeated!");
                enemyManager.removeEnemyFromTile(player.getX(), player.getY(), enemy);
                break;
            }

            // Enemy's turn
            performEnemyAttack(enemy);

            if (player.getHealth() <= 0) {
                appendToOutput("You have been defeated...");
                appendToOutput("You are Dead.\n");
                appendToOutput("Game Over.\n");
                guiHelper.endGame();
                break;
            }
            
        }
    }

    /**
    * Executes a random attack by the player against the enemy.
    *
    * @param enemy The {@link Enemy} that the player attacks.
    */
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
        	player.healPlayer(damage);
        	appendToOutput(player.getName() + " used " + chosenAttack.getName() + " and added " + damage + " health to themselves.");
        	break;
        case "hurt":
        	enemy.hurtEnemy(damage);
        	appendToOutput(player.getName() + " used " + chosenAttack.getName() + " and dealt " + damage + " damage to " + enemy.getName() + ".");
        	break;
        }
        
    }

    /**
    * Executes a random attack by the enemy against the player.
    *
    * @param enemy The {@link Enemy} that attacks the player.
    */
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
        	enemy.healEnemy(damage);
        	appendToOutput(enemy.getName() + " used " + chosenAttack.getName() + " and added " + damage + " health to themselves.");
        	break;
        case "hurt":
        	player.hurtPlayer(damage);
        	appendToOutput(enemy.getName() + " used " + chosenAttack.getName() + " and dealt " + damage + " damage to " + player.getName() + ".");
        	break;
        }
        
    }

    /**
    * Appends a message to the combat log, which is displayed in the GUI.
    *
    * @param message The message to append to the output log.
    */
    private void appendToOutput(String message) {
        output.append(message + "\n");
    }
}
