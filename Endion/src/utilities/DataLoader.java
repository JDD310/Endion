package utilities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import entities.Attack;
import entities.Enemy;
import entities.NPC;
import entities.Tile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataLoader {

	// Load data from a JSON file
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

    // Parse enemy data from the JSON
    public static Map<String, Enemy> parseEnemyData(JSONObject data, Map<String, Attack> attackData) {
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

                // Create Enemy object
                Enemy enemy = new Enemy(enemyName, description, health, enemyAttacks);
                enemies.put(enemyName, enemy);
            }
        }

        return enemies;
    }

    // Parse tile data from the JSON
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
                List<String> lootPool = lootPoolArray != null ? lootPoolArray.toList().stream()
                        .map(Object::toString)
                        .collect(Collectors.toList()) : new ArrayList<>();
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
                }
                // Create Tile object
                Tile tile = new Tile(tileName, description, searchableInfo, lootPool, tileEnemies, hasNPC, gridLoc);

                tiles.put(tileName, tile);
            }
        }

        return tiles;
    }

    // Parse npc data from the JSON
    public static Map<String, NPC> parseNPCData(JSONObject data) {
        Map<String, NPC> npcs = new HashMap<>();

        if (data.has("enemies")) {
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
    
    // Parses attack data from the loaded JSON
    public static Map<String, Attack> parseAttackData(JSONObject data) {
        Map<String, Attack> attacks = new HashMap<>();

        if (data.has("attacks")) {
            JSONObject attacksData = data.getJSONObject("attacks");
            for (String attackName : attacksData.keySet()) {
                JSONObject npcInfo = attacksData.getJSONObject(attackName);

                // Extract Attack attributes
                String description = npcInfo.optString("description", "No description available.");
                JSONArray damageRangeArray = npcInfo.optJSONArray("damageRange");
                List<Integer> damageRange = damageRangeArray != null ? damageRangeArray.toList().stream()
                        .map(obj -> Integer.parseInt(obj.toString()))
                        .collect(Collectors.toList()) : new ArrayList<>();
                
                // Create Attack object
                Attack attack = new Attack(attackName, description, damageRange);
                attacks.put(attackName, attack);
            }
        }

        return attacks;
    }
}
