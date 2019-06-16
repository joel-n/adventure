package game;

import java.util.HashMap;


public class Game {

	private HashMap<String, Location> world;
	private Player player;
	// private GameFrame frame; // might not be needed
	
	// define a frame in Game that can be accessed by getFrame()
	// return methods from a method
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void initAndSetPlayer() {
		Player player1 = new Player();
		this.player = player1;
	}
	
	public Game() {
		this.initAndSetPlayer();
		this.getPlayer().initAndSetCommandStore();
		this.initAndSetWorld();
	}
	
	public HashMap<String, Location> getWorld() {
		return this.world;
	}
	
	public void addLocation(Location location) {
		this.getWorld().put(location.getName(), location);
	}
	
	public void initAndSetWorld() {
		
		//// SETUP LOCATIONS IN WORLD
		
		// CREATING AND SETTING GAME WORLD
		HashMap<String, Location> gameWorld = new HashMap<String, Location>();
		this.world = gameWorld;
		
		// CREATING AND ADDING LOCATIONS
		Location location1 = new Location("test location", "this is a test area");
		this.addLocation(location1);
		Location location2 = new Location("tl2", "this is another test area");
		this.addLocation(location2);
		
		
		
		// SETTING LOCATION HASHMAPS
		HashMap<String, Item> loc1Item = new HashMap<String, Item>();
		HashMap<String, Location> loc1Path = new HashMap<String, Location>();
		location1.setLocationHashes(loc1Path, loc1Item);
		
		HashMap<String, Item> loc2Item = new HashMap<String, Item>();
		HashMap<String, Location> loc2Path = new HashMap<String, Location>();
		location2.setLocationHashes(loc2Path, loc2Item);
		
		
		// CONNECTING LOCATIONS
		// DOES NOT MATTER WHICH LOCATION ON WHICH THE METHOD IS CALLED
		location1.addPaths(location1, "east", location2, "west");
		
		// CREATING AND SETTING PLAYER INVENTORY
		HashMap<String, Item> inventory = new HashMap<String, Item>();
		this.getPlayer().setInventoryHash(inventory);
		
		// CREATING AND ADDING ITEMS TO PLAYER INVENTORY 
		Item potion = new Potion("potion", 1, 100, true, 10);
		this.getPlayer().addItem(potion);
		Item sword = new Item("sword", 1, 200, true);
		this.getPlayer().addItem(sword);
		
		
		
		// SETTING LOCATION, CARRY CAPACITY, GOLD AMOUNT, HEALTH, MAXHEALTH, XP AND LEVEL
		this.getPlayer().setLocation(location1);
		this.getPlayer().setCarryCapacity(100);
		this.getPlayer().setGold(10);
		this.getPlayer().setHealth(100);
		this.getPlayer().setMaxHealth(150);
		this.getPlayer().setXp(0);
		this.getPlayer().setLevel(1);
		this.getPlayer().setNextLevelLimit(1000);
		this.getPlayer().setLevelMultiplier(2);
		System.out.println("All set up.");
	}
	
	// CALLED BY HANDLEINPUT() IN GAMEFRAME, CALLS APPROPRIATE FUNCTIONS DEPENDING ON PLAYER INPUT
	// ALL COMMANDS RETURN A STRING TO THE OUTPUTHANDLER
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
		case "take": return this.takeItem(text[1]);
		case "drop": return this.dropItem(text[1]);
		case "look": return this.look();
		case "inventory": return this.inventory();
		case "health": return this.health();
		case "help": return this.help();
		default: return "You cannot do that.";
		}
		}
		}
		catch(Exception e){
			return "Specify a valid command or an argument.";

		}}
	
	
		// returns command from CommandStore if found, else returns null
		public Command getEnteredCommand(String enteredCommand) {
			return this.getPlayer().getPlayerCommands().get(enteredCommand);
		}
		
		// returns location from if players current location found, else returns null
		public Location getEnteredLocation(String enteredPath) {
			return this.getPlayer().getCurrentLocation().getPaths().get(enteredPath);
		}
		
		
		// moves player to new location
		public String moveTo(String newLocation) {
			if (this.getEnteredLocation(newLocation) == null) {
				return "You cannot go in that direction.";
			}
			else {
			this.getPlayer().setLocation(getEnteredLocation(newLocation));
			// this.getPlayer().gainXp(510); // line to control xp system and xpBar functionality by moving. 
			return this.getPlayer().getCurrentLocation().describeYourself();
			}
		}
		
		
		// returns information on available paths and items of the players location
		public String look() {
			if(this.getPlayer().getCurrentLocation().getPlaceInventory().isEmpty()) {
				return "You can move in the following directions: ".concat(this.getPlayer().getCurrentLocation().getPaths().keySet().toString() + "\n" +
						"There are no items in this place.");
			}
			else {
				
			}
			return "You can move in the following directions: ".concat(this.getPlayer().getCurrentLocation().getPaths().keySet().toString() + "\n" +
		"You see the following items: " + this.getPlayer().getCurrentLocation().getPlaceInventory().keySet().toString());
		}
		
		
		// consumes the item (potion)
		public String drink(String itemName) {
			if(this.getPlayer().getItem(itemName) == null) {
				return "You do not have this item in your inventory.";
			}
			else if (!(this.getPlayer().getItem(itemName) instanceof Potion)) { // if not instance of potion
				return "You cannot drink this item!";
			}
			else {
				// player's changHealth() manages max health (and minimum health)
				this.getPlayer().changeHealth(((Potion)this.getPlayer().getItem(itemName)).getHealing());
				this.getPlayer().removeItem(itemName);
				return "You drink " + itemName + ".";
			}
		}
		
		// player takes an item from the location
		// carry capacity managed in player's addItem method
		public String takeItem(String itemName) {
			if(this.getPlayer().getCurrentLocation().getItem(itemName) == null) {
				return "There is no " + " in this location.";
			} else if (this.getPlayer().getCurrentLocation().getItem(itemName).isRemovable() == false) {
				return "You cannot take this item.";
			} else if (this.getPlayer().getCarryCapacity() < this.getPlayer().getCurrentLocation().getItem(itemName).getWeight()) {
				return "You cannot carry anymore items.";
			}
			else {
				this.getPlayer().addItem(this.getPlayer().getCurrentLocation().getItem(itemName));
				this.getPlayer().getCurrentLocation().removeItem(itemName);
				return "You take " + itemName + ".";
			}
		}
		
		// player drops an item to the location
		// carry capacity managed in player's removeItem method
		public String dropItem(String itemName) {
			if(this.getPlayer().getItem(itemName) == null) {
				return "You do not have this item in your inventory.";
			}
			else {
				this.getPlayer().getCurrentLocation().addItem(this.getPlayer().getItem(itemName));
				this.getPlayer().removeItem(itemName);
				return "You drop " + itemName + ".";
			}
		}
		
		
		// returns information on the players items, carry capacity and gold
		public String inventory() {
			if(this.getPlayer().getInventory().isEmpty()) {
				return "You have no items in your inventory." + "\n"
						+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight. \n"
						+ "You have " + this.getPlayer().getGold() + " gold.";	
			}
			else {
			return "You have the following items: ".concat(this.getPlayer().getInventory().keySet().toString() + "\n"
				+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight. \n"
						+ "You have " + this.getPlayer().getGold() + " gold.");				
			}

		}
		
		public String health() {
			if(this.getPlayer().getHealth() == this.getPlayer().getMaxHealth()) {
				return "You are at maximum health";
			}
			else {
				return "You have " + this.getPlayer().getHealth() + "/" + this.getPlayer().getMaxHealth() + " hit points.";
			}
		}
		
		// returns the commands available to the player
		public String help() {
			return "You can use the commands: ".concat(this.getPlayer().getPlayerCommands().keySet().toString() + ".");
		}
		
}
