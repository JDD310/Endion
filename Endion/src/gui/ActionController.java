package gui;


import managers.EnvironmentManager;

import managers.NPCManager;

import managers.WorldManager;


public class ActionController {
//	private AttackManager attackManager;
//	private CombatManager combatManager;
//	private EnemyManager enemyManager;
	private EnvironmentManager environmentManager;
//	private InventoryManager inventoryManager;
	private NPCManager npcManager;
//	private PlayerManager playerManager;
//	private QuestManager questManager;
    private WorldManager worldManager;
    private GUIHelper guiHelper;
    
    public ActionController(
//    		AttackManager attackManager,
//    		CombatManager combatManager,
//    		EnemyManager enemyManager,
    		EnvironmentManager environmentManager,
    		GUIHelper guiHelper,
//    		InventoryManager inventoryManager,
    		NPCManager npcManager,
//    		PlayerManager playerManager,
//    		QuestManager questManager,
    	    WorldManager worldManager
    	){
//    		this.attackManager = attackManager;
//    		this.combatManager = combatManager;
//    		this.enemyManager = enemyManager;
    		this.environmentManager = environmentManager;
    		this.guiHelper = guiHelper;
//    		this.inventoryManager = inventoryManager;
    		this.npcManager = npcManager;
//    		this.playerManager = playerManager;
//    		this.questManager = questManager;
    		this.worldManager = worldManager;
    }
    
    
    public void doAction(String action) {
        switch(action) {
        	
        	// Cases for Movement Butttons
            case "moveNorth":
                worldManager.move("NORTH");
                guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment());
                guiHelper.updateHealth();
                break;
            case "moveSouth":
                worldManager.move("SOUTH");
                guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment());
                guiHelper.updateHealth();
                break;
            case "moveEast":
                worldManager.move("EAST");
                guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment());
                guiHelper.updateHealth();
                break;
            case "moveWest":
                worldManager.move("WEST"); 
                guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment());
                guiHelper.updateHealth();
                break;
            
            // Cases for Menu Buttons
            case "examine":
            	// Examine Environment
            	guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment() + environmentManager.examineCurrentTile());
            	guiHelper.updateHealth();
            	break;
            case "search":
            	guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment() + environmentManager.searchCurrentTile());
            	guiHelper.updateHealth();
            	break;
            case "updateStatusMenu":
            	guiHelper.updateStatus();
            	guiHelper.updateHealth();
            	break;
            case "endGame":
            	guiHelper.endGame();
            	break;
            case "speak":
            	guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment() + npcManager.speakToNPC());
            	break;
            
            case "setSpawnCity":
            	worldManager.spawnPlayer("city");
            	guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment());
            	guiHelper.updateHealth();
            	break;
            case "setSpawnMountain":
            	worldManager.spawnPlayer("mountain");
            	guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment());
            	guiHelper.updateHealth();
            	break;
            case "setSpawnForest":
            	worldManager.spawnPlayer("forest");
            	guiHelper.updateMainMenuText(environmentManager.displayCurrentEnvironment());
            	guiHelper.updateHealth();
            	break;

            default:
                System.out.println("No action defined for: " + action);
                break;
        }
    }
    
}
