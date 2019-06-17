package game;

public class Enemy extends Npc {
	
	
	private int health;
	private Item loot;
	private int xpYield;
	
	
	public Enemy(String name, boolean attackable, String talkLine, int health, Item lootItem, int xpYield) {
		super(name,attackable,talkLine);
		this.setHealth(health);
		this.setLoot(lootItem);
		this.setXpYield(xpYield);
	}
	
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setLoot(Item item) {
		this.loot = item;
	}
	
	public Item getLoot() {
		return this.loot;
	}
	
	public void setXpYield(int xpYield) {
		this.xpYield = xpYield;
	}
	
	public int getXpYield() {
		return this.xpYield;
	}
	
	public boolean isDefeated() {
		return (this.getHealth() <= 0);
	}

}
