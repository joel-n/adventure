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
		
		// SETUP LOCATIONS IN WORLD
		HashMap<String, Location> gameWorld = new HashMap<String, Location>();
		this.world = gameWorld;
		
		Location location1 = new Location("test location", "this is a test area");
		this.addLocation(location1);
		Location location2 = new Location("tl2", "this is another test area");
		this.addLocation(location2);
		
		
		
		// SETUP ITEMS AND PATHS
		HashMap<String, Item> inventory = new HashMap<String, Item>();
		this.getPlayer().setInventoryHash(inventory);
		
		HashMap<String, Item> loc1Item = new HashMap<String, Item>();
		HashMap<String, Location> loc1Path = new HashMap<String, Location>();
		location1.setLocationHashes(loc1Path, loc1Item);
		
		HashMap<String, Item> loc2Item = new HashMap<String, Item>();
		HashMap<String, Location> loc2Path = new HashMap<String, Location>();
		location2.setLocationHashes(loc2Path, loc2Item);
		
		location1.addPaths(location1, "east", location2, "west");
		
		Item potion = new Item("potion");
		this.getPlayer().addItem(potion);
		Item healthpotion = new Item("healthpotion");
		this.getPlayer().addItem(healthpotion);
		this.getPlayer().setLocation(location1);
		System.out.println("All set up.");
	}
	
	
	
	// returns command if found, else returns null
		public Command getEnteredCommand(String enteredCommand) {
			return this.getPlayer().getPlayerCommands().get(enteredCommand);
		}
		
		// returns location if found, else returns null
		public Location getEnteredLocation(String enteredPath) {
			return this.getPlayer().getCurrentLocation().getPaths().get(enteredPath);
		}
		
		
		public String moveTo(String newLocation) {
			if (this.getEnteredLocation(newLocation) == null) {
				return "You cannot go in that direction.";
			}
			else {
			this.getPlayer().setLocation(getEnteredLocation(newLocation));
			return this.getPlayer().getCurrentLocation().describeYourself();
			}
		}
		
		public String look() {
			return "You can move in the following directions: ".concat(this.getPlayer().getCurrentLocation().getPaths().keySet().toString());
		}
		
		public String drink(String itemName) {
			if(this.getPlayer().getItem(itemName) == null) {
				return "You do not have this item in your inventory.";
			}
			else {
				this.getPlayer().removeItem(itemName);
				return "You drink " + itemName + ".";
			}
		}
		
		public String takeItem(String itemName) {
			if(this.getPlayer().getCurrentLocation().getItem(itemName) == null) {
				return "There is no " + " in this location.";
			}
			else {
				this.getPlayer().addItem(this.getPlayer().getCurrentLocation().getItem(itemName));
				this.getPlayer().getCurrentLocation().removeItem(itemName);
				return "You take " + itemName + ".";
			}
		}
		
		public String inventory() {
			return "You have the following items: ".concat(this.getPlayer().getInventory().keySet().toString());
		}
		
		
		public String help() {
			return "You can use the commands: ".concat(this.getPlayer().getPlayerCommands().keySet().toString() + ".");
		}
		
		
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

	
	
	
	
	
	/* maybe solved by letting the frame refer to the game-instance
	 * and letting commands be passed to the game via the frame
	
	public GameFrame getFrame() {
		return this.frame;
	}
	
	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}
	 */
	
	
	/*
	public void main(String[] arguments) {
		GameFrame frame = new GameFrame();
		this.setFrame(frame);
	}
	*/
	

	
}
