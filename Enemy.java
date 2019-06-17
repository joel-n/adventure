package game;

public class Enemy extends Npc {
	
	
	private int health;
	private Item loot;
	private int xpYield;
	private int attack;
	
	
	public Enemy(String name, boolean attackable, String talkLine, int health, Item lootItem, int xpYield, int attack) {
		super(name,attackable,talkLine);
		this.setHealth(health);
		this.setLoot(lootItem);
		this.setXpYield(xpYield);
		this.setAttack(attack);
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
	
	public int getAttack() {
		return this.attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}

}
