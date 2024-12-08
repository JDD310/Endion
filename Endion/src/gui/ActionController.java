package gui;

import managers.AttackManager;
import managers.CombatManager;
import managers.EnemyManager;
import managers.EnvironmentManager;
import managers.InventoryManager;
import managers.NPCManager;
import managers.PlayerManager;
import managers.QuestManager;
import managers.WorldManager;


public class ActionController {
	private AttackManager attackManager;
	private CombatManager combatManager;
	private EnemyManager enemyManager;
	private EnvironmentManager environmentManager;
	private InventoryManager inventoryManager;
	private NPCManager npcManager;
	private PlayerManager playerManager;
	private QuestManager questManager;
    private WorldManager worldManager;
    private GUIHelper guiHelper;
    
    public ActionController(
    		AttackManager attackManager,
    		CombatManager combatManager,
    		EnemyManager enemyManager,
    		EnvironmentManager environmentManager,
    		GUIHelper guiHelper,
    		InventoryManager inventoryManager,
    		NPCManager npcManager,
    		PlayerManager playerManager,
    		QuestManager questManager,
    	    WorldManager worldManager
    	){
    		this.attackManager = attackManager;
    		this.combatManager = combatManager;
    		this.enemyManager = enemyManager;
    		this.environmentManager = environmentManager;
    		this.guiHelper = guiHelper;
    		this.inventoryManager = inventoryManager;
    		this.npcManager = npcManager;
    		this.playerManager = playerManager;
    		this.questManager = questManager;
    		this.worldManager = worldManager;
    }
    
    
    public void doAction(String action) {
        switch(action) {
        	
        	// Cases for Movement Butttons
            case "moveNorth":
                worldManager.move("NORTH");
                break;
            case "moveSouth":
                worldManager.move("SOUTH");
                break;
            case "moveEast":
                worldManager.move("EAST");
                break;
            case "moveWest":
                worldManager.move("WEST"); 
                break;
            
            // Cases for MainMenu Buttons
            case "updateMoveMenu":
            	// update the move menu grid
            	break;
            case "examine":
            	// Examine Environment
            	break;
            case "search":
            	// search environment
            	break;
            case "updateStatusMenu":
            	guiHelper.updateStatus();
            	break;
            case "endGame":
            	guiHelper.endGame();
            	break;
            
            default:
                System.out.println("No action defined for: " + action);
                break;
        }
    }
    
}
