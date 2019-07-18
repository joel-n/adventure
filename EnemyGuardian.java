package game;

public class EnemyGuardian extends Enemy {

	private Location guardedLocation;
	private String pathTo;
	private String pathFrom;
	private String revelationMessage;
	
	public EnemyGuardian(String name, boolean attackable, String talkLine, int health, Item lootItem, int xpYield, int attack, Location guardedLocation,
			String revelationMessage, String pathTo, String pathFrom) {
					super(name,attackable,talkLine,health,lootItem,xpYield,attack);
					this.setGuardedLocation(guardedLocation);
					this.setPathTo(pathTo);
					this.setPathFrom(pathFrom);
					this.setRevelationMessage(revelationMessage);
	}
	
	public EnemyGuardian(String name, boolean attackable, String talkLine, int health, Item lootItem, int xpYield, int attack, int questId, Location guardedLocation,
			String revelationMessage, String pathTo, String pathFrom) {
					super(name,attackable,talkLine,health,lootItem,xpYield,attack,questId);
					this.setGuardedLocation(guardedLocation);
					this.setPathTo(pathTo);
					this.setPathFrom(pathFrom);
					this.setRevelationMessage(revelationMessage);
	}
	
	public void setGuardedLocation(Location location) {
		this.guardedLocation = location;
	}
	
	public Location getGuardedLocation() {
		return this.guardedLocation;
	}
	
	public void setRevelationMessage(String m) {
		this.revelationMessage = m;
	}
	
	public String getRevelationMessage() {
		return this.revelationMessage;
	}
	
	public String getPathTo() {
		return this.pathTo;
	}
	
	public void setPathTo(String name) {
		this.pathTo = name;
	}

	public String getPathFrom() {
		return this.pathFrom;
	}
	
	public void setPathFrom(String name) {
		this.pathFrom = name;
	}

}


