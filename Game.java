package game;

import java.util.HashMap;


public class Game {

	private HashMap<String, Location> world;
	private Player player;
	
	private boolean inBattle;
	private Enemy currentEnemy;
	
	private Spawner spawner;
	
	private BodyArmor defaultBodyArmor;
	private Boots defaultBoots;
	private Gloves defaultGloves;
	private Headgear defaultHeadgear;
	private Weapon defaultWeapon;
	
	public Game() {
		this.initAndSetPlayer();
		this.getPlayer().initAndSetCommandStore();
		this.initAndSetWorld();
	}	
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void initAndSetPlayer() {
		Player player1 = new Player("Hero", 0, 150, 150, 100, 0,
				1000, 2, 1);
		this.player = player1;
	}
	
	/*
	 * PLAYER CONSTRUCTOR - LOCATION AND ITEM HASHES ARE AUTOMATICALLY SET BY CALLING CONSTRUCTOR
	 * Player(String name, int gold, int health, int maxHealth, int carryCapacity, int xp,
	 * 	 int nextLevelLimit, int levelMultiplier, int level)	
	 */

	
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
		
		// CREATING AND ADDING LOCATIONS, LOCATION AND ITEM HASHES ARE AUTOMATICALLY SET BY CALLING CONSTRUCTOR
		Location valley = new Location("valley", "Evalon Valley. A green valley with fertile soil.",
				"You cannot go in that direction.");
		this.addLocation(valley);
		Location plains = new Location("plains", "West Plains. A desolate plain.",
				"You cannot go in that direction. There is nothing but dust over there.");
		this.addLocation(plains);
		Location mountain = new Location("mountain", "Northern Mountains. A labyrinth of razor sharp rocks.",
				"You cannot go in that direction. The Mountain is not so easily climbed.");
		this.addLocation(mountain);
		Location shore = new Location("shore", "Western Shore. The water is calm.",
				"You cannot go in that direction. There might be dangerous beasts in the water.");
		this.addLocation(shore);
		Location woods = new Location("woods", "King's Forest. A bright forest with high pines.",
				"You cannot go in that direction. Be careful not to get lost in the woods.");
		this.addLocation(woods);
		Location hills = new Location("hills", "Evalon hills.",
				"You cannot go in that direction.");
		this.addLocation(hills);
		
		// CONNECTING LOCATIONS
		// IT DOES NOT MATTER ON WHICH LOCATION THE METHOD ADDPATHS IS CALLED
		valley.addPaths(valley, "east", plains, "west");
		valley.addPaths(valley, "north", mountain, "south");
		valley.addPaths(valley, "west", shore, "east");
		valley.addPaths(valley, "south", woods, "north");
		woods.addPaths(woods, "east", hills, "west");
		
		
		// CREATE EMPTY ARMOR SET FOR GAME START AND UNEQUIPS
		BodyArmor noBodyArmor = new BodyArmor("unarmored", 0, 0, true, 0);
		this.setDefaultBodyArmor(noBodyArmor);
		Boots noBoots = new Boots("unarmored", 0, 0, true, 0);
		this.setDefaultBoots(noBoots);
		Headgear noHeadgear = new Headgear("unarmored", 0, 0, true, 0);
		this.setDefaultHeadgear(noHeadgear);
		Gloves noGloves = new Gloves("unarmored", 0, 0, true, 0);
		this.setDefaultGloves(noGloves);
		Weapon noWeapon = new Weapon("unarmored", 0, 0, true, 5, false);
		this.setDefaultWeapon(noWeapon);
		
		this.getPlayer().setBodyArmor(noBodyArmor);
		this.getPlayer().setBoots(noBoots);
		this.getPlayer().setGloves(noGloves);
		this.getPlayer().setHeadgear(noHeadgear);
		this.getPlayer().setWeapon(noWeapon);
		
		
		// CREATING AND ADDING ITEMS TO PLAYER INVENTORY 
		Item potion = new Potion("potion", 1, 100, true, 10);
		this.getPlayer().addItem(potion);
		Weapon sword = new Weapon("sword", 10, 200, true, 10, false);
		valley.addItem(sword);
		
		
		// ENEMY LOOT
		BodyArmor chestplate = new BodyArmor("chestplate", 20, 200, true, 20);
		
		// ADDING ENEMY TO WORLD
		Enemy enemy1 = new Enemy("enemy", true, "Come at me!", 50, chestplate, 200, 10);
		mountain.addNpc(enemy1);
		
		// ADDING SPAWNER TO WORLD
		this.setSpawner(new BanditSpawner()); 
		
		// ADDING PLAYER TO THE WORLD
		this.getPlayer().setLocation(valley);
		
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
		else if(this.inBattle() == true) {
			switch(text[0]) {
			case "attack": return this.attack(); 			// ALSO TRIGGERS ENEMY ATTACK
			case "escape": return this.runAway();
			case "health": return this.health();
			default: return "You cannot do that.";
			}
		}
		else {
			switch(text[0]) {
			case "move": return this.moveTo(text[1]);		// 20% PROBABILITY OF SPAWNING A BANDIT AT NEW LOCATION
			case "drink": return this.drink(text[1]);
			case "take": return this.takeItem(text[1]);
			case "drop": return this.dropItem(text[1]);
			case "equip": return this.equipItem(text[1]);
			case "unequip": return this.unequipItem(text[1]);
			case "look": return this.look();
			case "inventory": return this.inventory();
			case "health": return this.health();
			case "help": return this.help();
			case "attack": return this.engageEnemy(text[1]);
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
		public Location getNeighbouringPath(String enteredPath) {
			return this.getPlayer().getCurrentLocation().getPaths().get(enteredPath);
		}
		
		
		// MOVES PLAYER TO NEW LOCATION
		// 20% PROBABILITY OF SPAWNING A BANDIT 
		public String moveTo(String enteredPath) {
			if (this.getNeighbouringPath(enteredPath) == null) {
				return this.getPlayer().getCurrentLocation().getOnWrongPathMessage();
			}
			else {
				this.getPlayer().setLocation(getNeighbouringPath(enteredPath));
				this.spawnOnChance(this.getPlayer().getCurrentLocation());
				return this.getPlayer().getCurrentLocation().describeYourself();
			}
		}
		
		
		// returns description of current location, its available paths and items
		public String look() {
			if(this.getPlayer().getCurrentLocation().getPlaceInventory().isEmpty()) {
				return "You are at " + this.getPlayer().getCurrentLocation().getDescription() + "\n" +
						"You can move in the following directions: " + this.getPlayer().getCurrentLocation().getPaths().keySet().toString() + "\n" +
						"There are no items in this place. \n" +
						"You see the following people: " + this.getPlayer().getCurrentLocation().getLocationNpcs().keySet().toString() + ". \n";
			}
			else {
				return "You are at " + this.getPlayer().getCurrentLocation().getDescription() + "\n" +
						"You can move in the following directions: " + this.getPlayer().getCurrentLocation().getPaths().keySet().toString() + "\n" +
						"You see the following items: " + this.getPlayer().getCurrentLocation().getPlaceInventory().keySet().toString() + "\n" +
						"You see the following people: " + this.getPlayer().getCurrentLocation().getLocationNpcs().keySet().toString() + ". \n";
			}
		}
		
		
		// consumes the item (Potion)
		public String drink(String itemName) {
			if(this.getPlayer().getItem(itemName) == null) {
				return "You do not have this item in your inventory.";
			}
			else if (!(this.getPlayer().getItem(itemName) instanceof Potion)) { // if not of class Potion
				return "You cannot drink this item!";
			}
			else {
				// player's changeHealth() manages max health (and minimum health)
				this.getPlayer().changeHealth(((Potion)this.getPlayer().getItem(itemName)).getHealing());
				this.getPlayer().removeItem(itemName);
				return "You drink " + itemName + ".";
			}
		}
		
		// player takes an item from the location
		// carry capacity managed in player's addItem method
		public String takeItem(String itemName) {
			if(this.getPlayer().getCurrentLocation().getItem(itemName) == null) {
				return "There is no " + itemName + " in this location.";
			} else if (this.getPlayer().getCurrentLocation().getItem(itemName).isRemovable() == false) {
				return "You cannot take this item.";
			} else if (this.getPlayer().getCarryCapacity() < this.getPlayer().getCurrentLocation().getItem(itemName).getWeight()) {
				return "You cannot carry anymore items.";
			}
			else {
				this.getPlayer().addItem(this.getPlayer().getCurrentLocation().getItem(itemName));
				this.getPlayer().getCurrentLocation().removeItem(itemName);
				return "You take " + itemName + ". \n"
						+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight.";
			}
		}
		
		// EQUIP DOES NOT AFFECT CARRIED WEIGHT
		public String equipItem(String itemName) {
			if(this.getPlayer().getItem(itemName) == null) {
				return "You do not have this item in your inventory.";
			}
			else {
				switch (this.getPlayer().getItem(itemName).getClassName()) {
					case "game.BodyArmor":
						this.getPlayer().switchBodyArmor(itemName);					
						return "You equip " + itemName + ".";
					case "game.Headgear":
						this.getPlayer().switchHeadgear(itemName);
						return "You equip " + itemName + ".";
					case "game.Gloves":
						this.getPlayer().switchGloves(itemName);
						return "You equip " + itemName + ".";
					case "game.Boots":
						this.getPlayer().switchBoots(itemName);
						return "You equip " + itemName + ".";
					case "game.Weapon":
						this.getPlayer().switchWeapon(itemName);
						return "You equip " + itemName + ".";
					default: return "You cannot equip this item.";
				}
			}
		}
		
		
		// UNEQUIP DOES NOT AFFECT CARRIED WEIGHT		
		public String unequipItem(String itemName) {
			if(itemName == "unarmored") {
				return "You do not have any armor to unequip."; // handles unarmored situation
			}
			else if(itemName.equals(this.getPlayer().getBodyArmor().getName())) {
				this.getPlayer().addUnequippedItemToInventory(this.getPlayer().getBodyArmor());
				this.getPlayer().setBodyArmor(this.getDefaultBodyArmor());		// set armor to default on unequip
				return "You unequip " + itemName + ".";
			}
			else if (itemName.equals(this.getPlayer().getHeadgear().getName())) {
				this.getPlayer().addUnequippedItemToInventory(this.getPlayer().getHeadgear());
				this.getPlayer().setHeadgear(this.getDefaultHeadgear());		// set headgear to default on unequip 
				return "You unequip " + itemName + ".";
			}
			else if (itemName.equals(this.getPlayer().getGloves().getName())) {
				this.getPlayer().addUnequippedItemToInventory(this.getPlayer().getGloves());
				this.getPlayer().setGloves(this.getDefaultGloves());			// set gloves to default on unequip 
				return "You unequip " + itemName + ".";
			}
			else if (itemName.equals(this.getPlayer().getBoots().getName())) {
				this.getPlayer().addUnequippedItemToInventory(this.getPlayer().getBoots());
				this.getPlayer().setBoots(this.getDefaultBoots());				// set boots to default on unequip 
				return "You unequip " + itemName + ".";
			}
			else if (itemName.equals(this.getPlayer().getWeapon().getName())) {
				this.getPlayer().addUnequippedItemToInventory(this.getPlayer().getWeapon());
				this.getPlayer().setWeapon(this.getDefaultWeapon());			// set weapon to default on unequip 
				return "You unequip " + itemName + ".";
			}
			else {
				return "You cannot unequip an item you are not wearing.";
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
				return "You drop " + itemName + ". \n"
						+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight.";
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
		
		
		// returns hitpoints and equipped items
		public String health() {
			if(this.getPlayer().getHealth() == this.getPlayer().getMaxHealth()) {
				return "You are at maximum health. \n"
						+ this.getPlayer().presentEquippedItems();
			}
			else {
				return "You have " + this.getPlayer().getHealth() + "/" + this.getPlayer().getMaxHealth() + " hit points. \n"
						+ this.getPlayer().presentEquippedItems();
			}
		}
		
		// returns the commands available to the player
		public String help() {
			return "You can use the commands: \n" + this.getPlayer().getPlayerCommands().keySet().toString() + ".";
		}
		
		// ADDING .replace("[", "").replace("]", "") AFTER STRING WOULD REMOVE BRACKETS
		
		/////////////////////////////////////////////////////////////////////
		// ATTACK SYSTEM
		public String engageEnemy(String enemyName) {
			if(this.getPlayer().getCurrentLocation().getNpc(enemyName) == null) {
				return "This person is not here.";
			}
			else if(this.getPlayer().getCurrentLocation().getNpc(enemyName).isAttackable() == false) {
				return "You cannot attack this person."; // possible later to implement custom "not able to attack this person, too strong/is friend" etc
			}
				this.setCurrentEnemy((Enemy) this.getPlayer().getCurrentLocation().getNpc(enemyName));
				this.setInBattle(true);
				return "You are now fighting " + enemyName + ".";
		}
		
		public String runAway() {
			this.setInBattle(false);
			return "You are no longer in battle.";
		}
		
		public String attack() {
			this.getCurrentEnemy().setHealth(this.getCurrentEnemy().getHealth()-this.getPlayer().getWeapon().getAttack());
			if(this.getCurrentEnemy().isDefeated()) {
				this.setInBattle(false);
				if(this.getCurrentEnemy().getLoot() != null) {
					this.getPlayer().getCurrentLocation().addItem(this.getCurrentEnemy().getLoot());
				}
				this.getPlayer().gainXp(this.getCurrentEnemy().getXpYield());
				this.getPlayer().getCurrentLocation().removeNpc(this.getCurrentEnemy().getName());
				return "You defeated " + this.getCurrentEnemy().getName() + ".";
			}
			else {
				this.triggerEnemyAttack();
				return "You attacked " + this.getCurrentEnemy().getName() + " for " + this.getPlayer().getWeapon().getAttack() + " damage. \n"
						+ this.getCurrentEnemy().getName() + " attacked you for " + this.getCurrentEnemy().getAttack() + " damage.";
			}
		}
		
		public void triggerEnemyAttack() {
			this.getPlayer().changeHealth(0-this.getCurrentEnemy().getAttack());
		}
		
		
		/////////////////////////////////////////////////////////////////////		
		// SPAWN NEW ENEMY AT LOCATION
		public void spawnEnemy(Location location) {
			location.addNpc(this.getSpawner().newEnemy());
		}
		
		public Spawner getSpawner() {
			return this.spawner;
		}
		
		public void setSpawner(Spawner spawner) {
			this.spawner = spawner;
		}
		
		// 20% CHANCE TO SPAWN A BANDIT AT LOCATION
		public void spawnOnChance(Location location) {
			if(Math.random() < 0.20) {
				this.spawnEnemy(location);
			}
		}
		
		
		/////////////////////////////////////////////////////////////////////
		// GET AND SET DEFAULT ARMOR AND WEAPON
		// GETS EQUIPPED WHENEVER PLAYER UNEQUIPS ITEM AND REMOVED
		// WHENEVER PLAYER EQUIPS ITEM BUT NOT TO INVENTORY
		public BodyArmor getDefaultBodyArmor() {
			return this.defaultBodyArmor;
		}
		
		public void setDefaultBodyArmor(BodyArmor bodyArmor) {
			this.defaultBodyArmor = bodyArmor;
		}
		
		public Headgear getDefaultHeadgear() {
			return this.defaultHeadgear;
		}
		
		public void setDefaultHeadgear(Headgear headgear) {
			this.defaultHeadgear = headgear;
		}
		
		public Boots getDefaultBoots() {
			return this.defaultBoots;
		}
		
		public void setDefaultBoots(Boots boots) {
			this.defaultBoots = boots;
		}
		
		public Gloves getDefaultGloves() {
			return this.defaultGloves;
		}
		
		public void setDefaultGloves(Gloves gloves) {
			this.defaultGloves = gloves;
		}
		
		public Weapon getDefaultWeapon() {
			return this.defaultWeapon;
		}
		
		public void setDefaultWeapon(Weapon weapon) {
			this.defaultWeapon = weapon;
		}
		
		
		
		
		/////////////////////////////////////////////////////////////////////
		// HANDLING BATTLE PARAMETERS
		public boolean inBattle() {
			return this.inBattle;
		}
		
		public void setInBattle(boolean inBattle) {
			this.inBattle = inBattle;
		}
		
		public Enemy getCurrentEnemy() {
			return this.currentEnemy;
		}
		
		public void setCurrentEnemy(Enemy enemy) {
			this.currentEnemy = enemy;
		}
		
		
		
		
		
		
		
		
}
