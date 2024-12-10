package entities;

//This Class is not used yet, Will be used in later updates to this game.

public class Weapon implements Nameable {
	
	private String name;
	
    public Weapon (String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
