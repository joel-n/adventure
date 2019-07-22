package game;

public class Npc {
	
	private String name;
	private boolean attackable;
	private boolean canTrade;
	private String talkLine;
	private int gold;
	private Chest inventory;
	
	// possible later to implement custom cannotattackLine "not able to attack this person, too strong/is friend" etc
	
	public Npc(String name, boolean attackable, String talkLine, boolean canTrade, int gold) {
		this.setName(name);
		this.setAttackable(attackable);
		this.setTalkLine(talkLine);
		this.setCanTrade(canTrade);
		if(this.canTrade()) {
			Chest inventory = new Chest(this.getName() + "'s inventory",0,0,false,100);
			this.setNpcInventory(inventory);
		}
		this.setNpcGold(gold);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCanTrade(boolean canTrade) {
		this.canTrade = canTrade;
	}
	
	public boolean canTrade() {
		return this.canTrade;
	}
	
	public boolean isAttackable() {
		return this.attackable;
	}
	
	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}
	
	public String getTalkLine() {
		return this.talkLine;
	}
	
	public void setTalkLine(String talkLine) {
		this.talkLine = talkLine;
	}

	/////////////////////////////////////////////////////////////////////////// ITEMS
	public void setNpcInventory(Chest inventory) {
		this.inventory = inventory;
	}
	
	// CARRY CAPACITY AND SPACE SHOULD BE CHECKED IN GAME
	public void addItem(Item item) {
		this.getNpcInventory().addItem(item);
	}
	
	// returns item if found, else returns null
	public Item getItem(String itemName) {
		return this.getNpcInventory().getItem(itemName);
	}
	
	public void removeItem(String itemName) {
		this.getNpcInventory().removeItem(itemName);	
	}
	
	public Chest getNpcInventory(){
		return this.inventory;
	}
	
	public int getNpcGold() {
		return this.gold;
	}
	
	public void setNpcGold(int amount) {
		this.gold = amount;
	}
	
	public void changeNpcGold(int amount) {
		this.setNpcGold(this.getNpcGold() + amount);
	}
	
	public String printGold() {
		return this.getName() + " has " + this.getNpcGold() + " gold.";
	}
}
