package entities;


import java.util.List;
import java.util.Random;

public class NPC extends Entity {
	
    private final String description;
    private final List<String> dialogues;
    private final String quest;
    private final int[] gridLocation;

    // Constructor for NPC with a quest
    public NPC(String name, String description, List<String> dialogues, String quest, int[] gridLocation) {
        super(name);
        this.description = description;
        this.dialogues = dialogues;
        this.quest = quest;
        this.gridLocation = gridLocation;
    }

    // Constructor for NPC without a quest
    public NPC(String name, String description, List<String> dialogues, int[] gridLocation) {
        this(name, description, dialogues, null, gridLocation);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDialogues() {
        return dialogues;
    }

    public String getQuest() {
        return quest;
    }
    
    public int[] getGridLocation() {
        return gridLocation;
    }

    // NPC speaks (randomly selects a dialogue)
    public String speak() {
        Random random = new Random();
        return dialogues.get(random.nextInt(dialogues.size()));
    }

    // Quest-giving method (optional for future)
    public String giveQuest() {
        return quest != null ? quest : "No quest available.";
    }
}
