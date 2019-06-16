package game;
import java.util.HashMap;

public class Player {
	
	private String name;
	private Location location;
	private int gold;
	private int health;
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
	
	// returns command if found, else returns null
	public Command getEnteredCommand(String enteredCommand) {
		return this.getPlayerCommands().get(enteredCommand);
	}
	
	// returns location if found, else returns null
	public Location getEnteredLocation(String enteredPath) {
		return this.getCurrentLocation().getPaths().get(enteredPath);
	}
	
	
	public String moveTo(String newLocation) {
		if (this.getEnteredLocation(newLocation) == null) {
			return "You cannot go in that direction.";
		}
		else {
		this.location = getEnteredLocation(newLocation);
		return this.getCurrentLocation().describeYourself();
		}
	}
	
	public String look() {
		return "You can move in the following directions: ".concat(this.getCurrentLocation().getPaths().keySet().toString());
	}
	
	public String drink(String itemName) {
		if(this.getItem(itemName) == null) {
			return "You do not have this item in your inventory.";
		}
		else {
			this.removeItem(this.getItem(itemName));
			return "You drink " + itemName + ".";
		}
	}
	
	public String inventory() {
		return "You have the following items: ".concat(this.getInventory().keySet().toString());
	}
	
	
	public String help() {
		return "You can use the commands: ".concat(this.getPlayerCommands().keySet().toString() + ".");
	}
	
	
	// needs to handle exception if text[1] does not exist 
	public String executeCommand(String arguments) {
		try {
		String[] text = arguments.split(" ");
		if (getEnteredCommand(text[0]) == null) {
			return "This is not possible.";
		}
		else {
		switch(text[0]) {
		case "move": return this.moveTo(text[1]);
		case "drink": return this.drink(text[1]);
		case "look": return this.look();
		case "inventory": return this.inventory();
		case "help": return this.help();
		default: return "You cannot do that.";
		}
		}
		}
		catch(Exception e){
			return "Specify a valid command or an argument.";

		}}


	
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
	}
	
	
	/////////////////////////////////////////////////////////////////////////// ITEMS
	public void setInventoryHash(HashMap<String, Item> itemHash) {
		this.items = itemHash;
	}
	
	public void addItem(Item item) {
		this.getInventory().put(item.getName(), item);
	}
	
	// returns item if found, else returns null
	public Item getItem(String enteredItem) {
		return this.getInventory().get(enteredItem);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void removeItem(Item item) {
		this.getInventory().remove(item);
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
	
	public void setPlayerInitialLocation(Location location) {
		this.location = location;
	}
	
	public int getGold() {
		return this.gold;
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
	
	public void changeHealth(int amount) {
		if(this.getHealth() + amount < 0) {
			System.out.println("Game Over");
			this.gameOver();
		}
		else {
			this.health = this.getHealth() + amount;
		}
	}
	
	public int getXp() {
		return this.xp;
	}
	
	public int getlevel() {
		return this.level;
	}
	
	public void gainXp(int amount) {
		this.xp = this.getXp() + amount;
		if(this.getXp() > this.nextLevelLimit) {
			this.levelUp();
		}
	}
	
	public void levelUp() {
		this.level = this.level++;
		this.nextLevelLimit = this.nextLevelLimit + 500*levelMultiplier;
		this.levelMultiplier = this.levelMultiplier++;
	}
	
	public void gameOver() {
		
	}
	
	
	
	
	
	
	
	
}
