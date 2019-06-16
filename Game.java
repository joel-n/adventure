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
		Item healthpotion = new Item("health potion");
		this.getPlayer().addItem(healthpotion);
		this.getPlayer().setPlayerInitialLocation(location1);
		System.out.println("All set up.");
	}
	
	
	
	
	
	
	
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
