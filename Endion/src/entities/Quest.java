package entities;

public class Quest implements Nameable {
    private final String name;
    private final String description;
    private final String objective;
    private boolean isCompleted;

    // Constructor
    public Quest(String name, String description, String objective) {
        this.name = name;
        this.description = description;
        this.objective = objective;
        this.isCompleted = false;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getObjective() {
        return objective;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Mark the quest as completed
    public void completeQuest() {
        this.isCompleted = true;
    }
}
