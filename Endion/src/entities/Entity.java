package entities;

public class Entity implements Nameable{
	
	// Common Variables Shared by all Entities
	protected final String name;
	
	// Entity Constructor
    public Entity(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
