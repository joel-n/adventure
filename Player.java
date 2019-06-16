package game;
import java.util.HashMap;

public class Player {
	
	private String name;
	private Location location;
	private int gold;
	private int health;
	private int maxHealth;
	private int carryCapacity;
	private HashMap<String,Item> items;
	private int xp;
	private int nextLevelLimit;
	private int levelMultiplier;
	private int level;
	private CommandStore commands;
	

	/////////////////////////////////////////////////////////////////////////// COMMANDS
	public CommandStore getPlayerCommands() {
		return this.commands;
	}
	
	public void initAndSetCommandStore() {
		CommandStore cmdstore = new CommandStore();
		this.commands = cmdstore;
		Command move = new Command("move", "You move");
		cmdstore.addCommand(move);
		Command drink = new Command("drink", "You drink");
		cmdstore.addCommand(drink);
		Command lookInventory = new Command("inventory", "You look in your inventory");
		cmdstore.addCommand(lookInventory);
		Command help = new Command("help", " ");
		cmdstore.addCommand(help);
		Command look = new Command("look", "You look about your position... ");
		cmdstore.addCommand(look);
		Command take = new Command("take", "Take an item.");
		cmdstore.addCommand(take);
		Command drop = new Command("drop", "Drop an item.");
		cmdstore.addCommand(drop);
		Command health = new Command("health", "Check your hit points.");
		cmdstore.addCommand(health);
	}
	
	
	/////////////////////////////////////////////////////////////////////////// ITEMS
	public void setInventoryHash(HashMap<String, Item> itemHash) {
		this.items = itemHash;
	}
	
	public void addItem(Item item) {
		this.getInventory().put(item.getName(), item);
		this.setCarryCapacity(this.getCarryCapacity()-item.getWeight());
	}
	
	// returns item if found, else returns null
	public Item getItem(String enteredItem) {
		return this.getInventory().get(enteredItem);
	}
	
	public void removeItem(String itemName) {
		this.setCarryCapacity(this.getCarryCapacity()+this.getItem(itemName).getWeight());
		this.getInventory().remove(itemName);	
	}
	
	public HashMap<String, Item> getInventory(){
		return this.items;
	}
	

	/////////////////////////////////////////////////////////////////////////// GETTERS ETC.
	public String getName() {
		return this.name;
	}
	
	public void setName(String newname) {
		this.name = newname;
	}
	

	public Location getCurrentLocation() {
		return this.location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public void setGold(int amount) {
		this.gold = amount;
	}
	
	public void changeGold(int amount) {
		if(this.getGold() + amount < 0) {
			System.out.println("Not enough money.");
		}
		else {
			this.gold = this.getGold() + amount;
		}
		
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int amount) {
		this.health = amount;
	}
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public void setMaxHealth(int amount) {
		this.maxHealth = amount;
	}
	
	public void changeHealth(int amount) {
		if(this.getHealth() + amount < 0) {
			System.out.println("Game Over");
			this.gameOver();
		}
		else if (this.getHealth() + amount > this.getMaxHealth()) {
			this.setHealth(this.getMaxHealth());
		}
		else {
			this.health = this.getHealth() + amount;
		}
	}
	
	public int getCarryCapacity() {
		return this.carryCapacity;
	}
	
	public void setCarryCapacity(int cc) {
		this.carryCapacity = cc;
	}
	
	public int getXp() {
		return this.xp;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getNextLevelLimit() {
		return this.nextLevelLimit;
	}
	
	public void setNextLevelLimit(int amount) {
		this.nextLevelLimit = amount;
	}
	
	public int getLevelMultiplier() {
		return this.levelMultiplier;
	}
	
	public void setLevelMultiplier(int lm) {
		this.levelMultiplier = lm;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public void gainXp(int amount) {
		this.xp = this.getXp() + amount;
		if(this.getXp() > this.getNextLevelLimit()) {
			this.setXp(this.getXp()-this.getNextLevelLimit()); // subtraction must take place before calling levelUp();
			this.levelUp();
		}
	}
	
	public void levelUp() {
		this.setLevel(this.getLevel() + 1);
		this.setNextLevelLimit(this.getNextLevelLimit() + 500*this.getLevelMultiplier());
		this.setLevelMultiplier(this.getLevelMultiplier() + 1);
		
		this.setMaxHealth(this.getMaxHealth() + 50);
	}
	
	public void gameOver() {
		
	}
	
	
	
	
	
	
	
	
}
