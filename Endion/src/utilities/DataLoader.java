package utilities;

import org.json.JSONArray;

import org.json.JSONObject;
import org.json.JSONTokener;

import core.Config;
import entities.Attack;
import entities.Enemy;
import entities.NPC;
import entities.Tile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
* The {@code DataLoader} class is responsible for loading and parsing data from 
* external JSON files. This data is used to configure the game and create entities 
* such as enemies, NPCs, tiles, and attacks.
* 
* @author JD Dennis
*/
public class DataLoader {

	/**
    * Loads a JSON file from the given file path and returns its contents as a {@link JSONObject}.
    *
    * @param filepath The path of the file to be loaded.
    * @return The loaded {@link JSONObject}, or {@code null} if an error occurs.
    */
    public static JSONObject loadData(String filepath) {
        try (InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream(filepath);
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            // Parse JSON file into a JSONObject
            return new JSONObject(new JSONTokener(reader));
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return null;
    }
    
    /**
    * Loads configuration data from a {@link JSONObject} based on the specified difficulty.
    *
    * @param data The complete JSON data containing the configuration.
    * @param difficulty The selected difficulty level to load.
    * @return A {@link Config} object containing player and enemy settings.
    */
    public static Config loadConfig(JSONObject data, String difficulty) {
    	
        // Access the "config" section
        if (!data.has("config")) {
            throw new IllegalArgumentException("Config section not found in the JSON data");
        }
        
        JSONObject configSection = data.getJSONObject("config");

        // Get the difficulty-specific data
        if (!configSection.has(difficulty)) {
            throw new IllegalArgumentException("Difficulty not found: " + difficulty);
        }
        
        JSONObject difficultyData = configSection.getJSONObject(difficulty);

        // Parse player configuration
        JSONObject playerData = difficultyData.getJSONObject("player");
        int playerHealth = playerData.getInt("health");
        List<String> playerAttacks = new ArrayList<>();
        playerData.getJSONArray("attacks").forEach(item -> playerAttacks.add(item.toString()));

        // Parse enemy configuration
        JSONObject enemyData = difficultyData.getJSONObject("enemies");
        float healthMultiplier = (float) enemyData.getDouble("healthMulti");
        float damageMultiplier = (float) enemyData.getDouble("damageMulti");

        // Return the parsed data as a ConfigData object
        return new Config(playerHealth, playerAttacks, healthMultiplier, damageMultiplier);
    }

    
    /**
    * Parses and creates enemy objects from JSON data.
    *
    * @param data The JSON data containing the enemy details.
    * @param attackData A map of attacks used to assign attacks to enemies.
    * @param healthMod A modifier to adjust the enemy's health.
    * @return A map of enemy names to their corresponding {@link Enemy} objects.
    */
    public static Map<String, Enemy> parseEnemyData(JSONObject data, Map<String, Attack> attackData, float healthMod) {
        Map<String, Enemy> enemies = new HashMap<>();

        if (data.has("enemies")) {
            JSONObject enemiesData = data.getJSONObject("enemies");
            for (String enemyName : enemiesData.keySet()) {
                JSONObject enemyInfo = enemiesData.getJSONObject(enemyName);

                // Extract enemy attributes
                // that attacksArray + attacks list "THING" was impossible to write. took an hour by itself.
                String description = enemyInfo.optString("description", "No description available.");
                int health = enemyInfo.optInt("health", 0);
                
                // Create a list of attacks for the enemy
                List<Attack> enemyAttacks = new ArrayList<>();
                if (enemyInfo.has("attacks")) {
                    JSONArray attackNames = enemyInfo.getJSONArray("attacks");
                    for (int i = 0; i < attackNames.length(); i++) {
                        String attackName = attackNames.getString(i);
                        if (attackData.containsKey(attackName)) {
                            enemyAttacks.add(attackData.get(attackName)); // Add attack object to the list
                        } else {
                            System.out.println("Warning: Attack " + attackName + " not found for Enemy " + enemyName);
                        }
                    }
                }
                float healthFinal = health * healthMod;
                // Create Enemy object
                Enemy enemy = new Enemy(enemyName, description, healthFinal, enemyAttacks);
                enemies.put(enemyName, enemy);
            }
        }

        return enemies;
    }

    /**
    * Parses and creates tile objects from JSON data.
    *
    * @param data The JSON data containing the tile details.
    * @param enemyData A map of enemy data used to assign enemies to tiles.
    * @return A map of tile names to their corresponding {@link Tile} objects.
    */
    public static Map<String, Tile> parseTileData(JSONObject data, Map<String, Enemy> enemyData) {
        Map<String, Tile> tiles = new HashMap<>();

        if (data.has("environments")) {
            JSONObject environments = data.getJSONObject("environments");

            for (String tileName : environments.keySet()) {
                JSONObject tileInfo = environments.getJSONObject(tileName);

                // Extract tile attributes
                String description = tileInfo.optString("description", "No description available.");
                String searchableInfo = tileInfo.optString("searchableInfo", "No searchable information.");
                JSONArray lootPoolArray = tileInfo.optJSONArray("lootPool");
                
                /**
                * This line is used multiple times, just with changed variables. Here is an explanation on how it works.
                * 
                * 1. {lootPoolArray != null} Checks if lootPoolArray is not null.
				*		1.a If lootPoolArray is not null, the code after the ? is executed.
				*		1.b If lootPoolArray is null, the code after the : is executed, which returns an empty list:
				*	
				* 2. {lootPoolArray.toList()} Converts the lootPoolArray (which is a JSONArray) into a Java List of objects.
				* 		This is done to allow easier use of Java’s Stream API for transformations.
				* 
				* 3. {.stream()} Converts the list of objects into a stream, enabling functional operations like map() and collect().
				* 
				* 4. {.map(Object::toString)} Calls the toString() method on each object in the stream.
				* 		This ensures that all items in the list are converted to Strings.
				* 		4.a This is a shorthand for {.map(obj -> obj.toString())}
				* 
				* 5. {.collect(Collectors.toList())} Collects all the converted string elements back into a new List<String>.
				* 		The final result is a list of string representations of the items from the original lootPoolArray.
				* 
				* 6. {: new ArrayList<>()} If lootPoolArray is null, it returns a new empty list using this code.
				* 
				*  This one line of code is the one i am most proud of in the entire project. 
				*  it took hours of looking at docs and thinking and testing to get right.
                */
                List<String> lootPool = 
                		lootPoolArray != null 
                		? lootPoolArray.toList()
                		.stream()
                        .map(Object::toString)
                        .collect(Collectors.toList())
                        : new ArrayList<>();
                
                boolean hasNPC = tileInfo.optBoolean("has_npc", false);
                JSONArray gridLocationArray = tileInfo.optJSONArray("gridLocation");
                List<Integer> gridLocation = gridLocationArray != null ? gridLocationArray.toList().stream()
                        .map(obj -> Integer.parseInt(obj.toString()))
                        .collect(Collectors.toList()) : new ArrayList<>();

                // Convert gridLocation to an int[] array
                int[] gridLoc = { gridLocation.get(0), gridLocation.get(1) };
                
                // Create a list of enemies for the tile
                List<Enemy> tileEnemies = new ArrayList<>();
                if (tileInfo.has("enemies")) {
                    JSONArray enemyNames = tileInfo.getJSONArray("enemies");
                    for (int i = 0; i < enemyNames.length(); i++) {
                        String enemyName = enemyNames.getString(i);
                        if (enemyData.containsKey(enemyName)) {
                            tileEnemies.add(enemyData.get(enemyName)); // Add enemy object to the list
                        } else {
                            System.out.println("Warning: Enemy " + enemyName + " not found for tile " + tileName);
                        }
                    }
                } else {
                	System.out.println("Warning: Enemies not found for tile" + tileName);
                }
                // Create Tile object
                Tile tile = new Tile(tileName, description, searchableInfo, lootPool, tileEnemies, hasNPC, gridLoc);

                tiles.put(tileName, tile);
            }
        }

        return tiles;
    }

    /**
    * Parses and creates NPC objects from JSON data.
    *
    * @param data The JSON data containing NPC details.
    * @return A map of NPC names to their corresponding {@link NPC} objects.
    */
    public static Map<String, NPC> parseNPCData(JSONObject data) {
        Map<String, NPC> npcs = new HashMap<>();

        if (data.has("npcs")) {
            JSONObject npcsData = data.getJSONObject("npcs");
            for (String npcName : npcsData.keySet()) {
                JSONObject npcInfo = npcsData.getJSONObject(npcName);

                // Extract NPC attributes
                
                String description = npcInfo.optString("description", "No description available.");
                JSONArray dialogueArray = npcInfo.optJSONArray("attacks");
                List<String> dialogue = dialogueArray != null ? dialogueArray.toList().stream()
                        .map(Object::toString)
                        .collect(Collectors.toList()) : new ArrayList<>();
                String quest = npcInfo.optString("quest", "No quest available.");
                JSONArray gridLocationArray = npcInfo.optJSONArray("gridLocation");
                List<Integer> gridLocation = gridLocationArray != null ? gridLocationArray.toList().stream()
                        .map(obj -> Integer.parseInt(obj.toString()))
                        .collect(Collectors.toList()) : new ArrayList<>();

                // Convert gridLocation to an int[] array
                int[] gridLoc = { gridLocation.get(0), gridLocation.get(1) };
                
                // Create NPC object
                NPC npc = new NPC(npcName, description, dialogue, quest, gridLoc);
                npcs.put(npcName, npc);
            }
        }

        return npcs;
    }
    
    /**
    * Parses and creates attack objects from JSON data.
    *
    * @param data The JSON data containing attack details.
    * @return A map of attack names to their corresponding {@link Attack} objects.
    */
    public static Map<String, Attack> parseAttackData(JSONObject data) {
        Map<String, Attack> attacks = new HashMap<>();

        if (data.has("attacks")) {
            JSONObject attacksData = data.getJSONObject("attacks");
            for (String attackName : attacksData.keySet()) {
                JSONObject attackInfo = attacksData.getJSONObject(attackName);

                // Extract Attack attributes
                String description = attackInfo.optString("description", "No description available.");
                JSONArray damageRangeArray = attackInfo.optJSONArray("damageRange");
                List<Integer> damageRange = damageRangeArray != null ? damageRangeArray.toList().stream()
                        .map(obj -> Integer.parseInt(obj.toString()))
                        .collect(Collectors.toList()) : new ArrayList<>();
                String type = attackInfo.optString("type", "hurt");
                
                // Create Attack object
                Attack attack = new Attack(attackName, description, damageRange, type);
                attacks.put(attackName, attack);
            }
        }

        return attacks;
    }
}
