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
	private BodyArmor bodyArmor;
	private Headgear headgear;
	private Boots boots;
	private Gloves gloves;
	private Weapon weapon;
	

	public Player(String name, int gold, int health, int maxHealth, int carryCapacity, int xp,
			int nextLevelLimit, int levelMultiplier, int level) {
		this.setName(name);
		this.setGold(gold);
		this.setHealth(health);
		this.setMaxHealth(maxHealth);
		this.setCarryCapacity(carryCapacity);
		HashMap<String,Item> items = new HashMap<String,Item>();
		this.setInventoryHash(items);
		this.setXp(xp);
		this.setNextLevelLimit(nextLevelLimit);
		this.setLevelMultiplier(levelMultiplier);
		this.setLevel(level);
		this.initAndSetCommandStore();
	}
	
	
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
		Command equipment = new Command("equipment", "Display equipped items.");
		cmdstore.addCommand(equipment);
		Command equip = new Command("equip", "Equip an item.");
		cmdstore.addCommand(equip);
		Command unequip = new Command("unequip", "Unequip an item.");
		cmdstore.addCommand(unequip);
		Command talk = new Command("talk", "Talk to a person.");
		cmdstore.addCommand(talk);
		
		Command place = new Command("place", "Place an an item in a chest.");
		cmdstore.addCommand(place);
		Command exit = new Command("exit", "Stop looting.");
		cmdstore.addCommand(exit);
		Command loot = new Command("loot", "Loot a chest.");
		cmdstore.addCommand(loot);
		
		Command attack = new Command("attack", "Attack an enemy");
		cmdstore.addCommand(attack);
		Command escape = new Command("escape", "Escape from battle.");
		cmdstore.addCommand(escape);
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
		this.setXp(this.getXp() + amount);
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
	
	public String presentEquippedItems() {
		return "Your equipment: \n"
				+ "Body: " + this.getBodyArmor().getName() + ". \n"
				+ "Head: " + this.getHeadgear().getName() + ". \n"
				+ "Feet: " + this.getBoots().getName() + ". \n"
				+ "Hands: " + this.getGloves().getName() + ". \n"
				+ "Weapon: " + this.getWeapon().getName() + ".";
	}
	
	public void switchBodyArmor(String itemName) {
		if(this.hasBodyArmor()) {
			this.addUnequippedItemToInventory(this.getBodyArmor());				// if not default armor, add it to inventory
		}
			this.setBodyArmor((BodyArmor) this.getItem(itemName));				// equip new item
			this.removeEquippedItemFromInventory(itemName);						// remove equipped item from inventory
		}
	
	public void switchHeadgear(String itemName) {
		if(this.hasHeadgear()) {
			this.addUnequippedItemToInventory(this.getHeadgear());
		}
			this.setHeadgear((Headgear) this.getItem(itemName));
			this.removeEquippedItemFromInventory(itemName);
		}
	
	public void switchGloves(String itemName) {
		if(this.hasGloves()) {
			this.addUnequippedItemToInventory(this.getGloves());
		}
		this.setGloves((Gloves) this.getItem(itemName));
		this.removeEquippedItemFromInventory(itemName);
	}
	
	public void switchBoots(String itemName) {
		if(this.hasBoots()) {
			this.addUnequippedItemToInventory(this.getBoots());
		}
		this.setBoots((Boots) this.getItem(itemName));
		this.removeEquippedItemFromInventory(itemName);
	}
	
	public void switchWeapon(String itemName) {
		if(this.hasWeapon()) {
			this.addUnequippedItemToInventory(this.getWeapon());
		}
		this.setWeapon((Weapon) this.getItem(itemName));
		this.removeEquippedItemFromInventory(itemName);
	}
	
	
	
	public boolean hasBodyArmor() {
		return (!(this.getBodyArmor().getName().equals("unarmored")));
	}
	
	public boolean hasGloves() {
		return (!(this.getGloves().getName().equals("unarmored")));
	}
	
	public boolean hasBoots() {
		return (!(this.getBoots().getName().equals("unarmored")));
	}
	
	public boolean hasHeadgear() {
		return (!(this.getHeadgear().getName().equals("unarmored")));
	}
	
	public boolean hasWeapon() {
		return (!(this.getWeapon().getName().equals("unarmored")));
	}
	
	public BodyArmor getBodyArmor() {
		return this.bodyArmor;
	}
	
	public void setBodyArmor(BodyArmor bodyArmor) {
		this.bodyArmor = bodyArmor;
	}
	
	public Headgear getHeadgear() {
		return this.headgear;
	}
	
	public void setHeadgear(Headgear headgear) {
		this.headgear = headgear;
	}
	
	public Boots getBoots() {
		return this.boots;
	}
	
	public void setBoots(Boots boots) {
		this.boots = boots;
	}
	
	public Gloves getGloves() {
		return this.gloves;
	}
	
	public void setGloves(Gloves gloves) {
		this.gloves = gloves;
	}
	
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	// DOES NOT AFFECT CARRYCAPACITY
	public void addUnequippedItemToInventory(Item item) {
		this.getInventory().put(item.getName(), item);
	}

	// DOES NOT AFFECT CARRYCAPACITY	
	public void removeEquippedItemFromInventory(String itemName) {
		this.getInventory().remove(itemName);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
