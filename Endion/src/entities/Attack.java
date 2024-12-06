package entities;

import java.util.List;

public class Attack {
    private final String name;
    private final String description;
    private final List<Integer> damageRange;

    public Attack(String name, String description, List<Integer> damageRange) {
        this.name = name;
        this.description = description;
        this.damageRange = damageRange;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getDamageRange() {
        return damageRange;
    }
}
