package entities;

import java.util.List;

public class Attack extends Entity {
	
	private final String description;
    private final List<Integer> damageRange;
    private final String type;

    public Attack(String name, String description, List<Integer> damageRange, String type) {
        super(name);
        this.description = description;
        this.damageRange = damageRange;
        this.type = type;
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
    
    public String getType() {
    	return type;
    }
}
