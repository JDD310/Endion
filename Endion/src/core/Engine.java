package core;

import managers.*;

import java.util.Map;

import org.json.JSONObject;

import entities.*;
import gui.ActionController;
import gui.GUIHelper;
import gui.GameGUI;
import utilities.*;

public class Engine {
	
	
	private Player player; // The main player
	
	// Managers
	private AttackManager attackManager;
	private CombatManager combatManager;
	private EnemyManager enemyManager;
	private EnvironmentManager environmentManager;
	private GUIHelper guiHelper;
	private InventoryManager inventoryManager;
	private NPCManager npcManager;
	private PlayerManager playerManager;
	private QuestManager questManager;
    private WorldManager worldManager;
   
    // GUI
    private GameGUI gameGUI;
    private ActionController actionController;
 	
 	// Assigning Data Types To Variables
 	private Map<String, Attack> Attacks;
	private Map<String, Enemy> Enemies;
	private Map<String, Tile> Tiles;
	private Map<String, NPC> NPCs;
	
	private Config config;
	
 	// Constructor
    public Engine(String playerName, String worldDataPath, String difficulty) {
    	
    	gameGUI = new GameGUI();
    	
    	//Initialize World (By loading Data from the json File)
    	initializeWorld(worldDataPath, difficulty);
    	
        // Initialize the player
        this.player = new Player(playerName, config.getPlayerStartingHealth());
        
        // Fill the player's attacks list with attack objects
        AttackManager.addBasicAttacksToPlayer(player, Attacks, config.getPlayerStartingAttackSet());
        
        // Initialize WorldManager
        this.worldManager = new WorldManager(player, Tiles);
        
   
        // Initialize other Managers
        this.attackManager = new AttackManager(Attacks);
        this.playerManager = new PlayerManager(player);
        this.npcManager = new NPCManager(NPCs, worldManager);
        this.inventoryManager = new InventoryManager(player);
        this.guiHelper = new GUIHelper(playerManager, gameGUI);
        this.enemyManager = new EnemyManager(worldManager , Enemies);
        this.combatManager = new CombatManager(player, enemyManager, guiHelper);
        this.environmentManager = new EnvironmentManager(worldManager, inventoryManager, combatManager, npcManager);
        
        // Initialize ActionController, Passing in everything so it can properly execute all game actions. 
        this.actionController = new ActionController(environmentManager, guiHelper, npcManager, worldManager);
        
        this.gameGUI.setActionController(actionController);
        gameGUI.initialize();
        
    }
    
    // Initialize the world (Load world data from the DataLoader)
    public void initializeWorld(String filePath, String difficulty) {
    	// Load game data from the JSON file
        JSONObject gameData = DataLoader.loadData(filePath);
        
        if (gameData != null) {
        	// Load config data
        	Config configData = DataLoader.loadConfig(gameData, difficulty);
        	this.config = configData;
        	
        	// Parse attack data
        	Map<String, Attack> attacks = DataLoader.parseAttackData(gameData);
        	this.Attacks = attacks;
        	
            // Parse enemy data and pass in attack data
            Map<String, Enemy> enemies = DataLoader.parseEnemyData(gameData, attacks, config.getHealthMultiplier());
            this.Enemies = enemies;
            
            // Parse tile data and pass in the enemy data
            Map<String, Tile> tiles = DataLoader.parseTileData(gameData, enemies);
            this.Tiles = tiles;
            
            // Parse npc data
            Map<String, NPC> npcs = DataLoader.parseNPCData(gameData);
            this.NPCs = npcs;
        // Just in Case
        } else {
            System.out.println("Failed to load game data.");
        }
    }
 	
    // Getters and Setters
    
    public GUIHelper getGUIManager() {
    	return guiHelper;
    }
    
    public void setGUIManager(GUIHelper guiHelper) {
    	this.guiHelper = guiHelper;
    }
    
    public EnvironmentManager getEnvironmentManager() {
    	return environmentManager;
    }
    
    public void setEnvironmentManager(EnvironmentManager environmentManager) {
    	this.environmentManager = environmentManager;
    }
    
    public WorldManager getWorldManager() {
    	return worldManager;
    }
    
    public void setWorldManager(WorldManager worldManager) {
    	this.worldManager = worldManager;
    }

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public void setEnemyManager(EnemyManager enemyManager) {
		this.enemyManager = enemyManager;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	public CombatManager getCombatManager() {
		return combatManager;
	}

	public void setCombatManager(CombatManager combatManager) {
		this.combatManager = combatManager;
	}

	public NPCManager getNpcManager() {
		return npcManager;
	}

	public void setNpcManager(NPCManager npcManager) {
		this.npcManager = npcManager;
	}

	public AttackManager getAttackManager() {
		return attackManager;
	}

	public void setAttackManager(AttackManager attackManager) {
		this.attackManager = attackManager;
	}

	public InventoryManager getInventoryManager() {
		return inventoryManager;
	}

	public void setInventoryManager(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

	public QuestManager getQuestManager() {
		return questManager;
	}

	public void setQuestManager(QuestManager questManager) {
		this.questManager = questManager;
	}

	public Config getGameConfig() {
		return config;
	}

	public void setGameConfig(Config gameConfig) {
		this.config = gameConfig;
	}

	public GameGUI getGameGUI() {
		return gameGUI;
	}

	public void setGameGUI(GameGUI gameGUI) {
		this.gameGUI = gameGUI;
	}

	public ActionController getActionController() {
		return actionController;
	}

	public void setActionController(ActionController actionController) {
		this.actionController = actionController;
	}
}
