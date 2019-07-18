package game;

import java.util.HashMap;


public class Game {

	private HashMap<String, Location> world;
	private Player player;
	
	private Quest quest;
	
	// battle parameters
	private boolean inBattle;
	private Enemy currentEnemy;
	
	private Spawner npcSpawner;
	private Spawner itemSpawner;
	
	// looting
	private boolean isLooting;
	private Chest currentChest;
	
	// default armor/weapon slots
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
				"You cannot go in that direction.",true);
		this.addLocation(valley);
		Location plains = new Location("plains", "West Plains. A desolate plain.",
				"You cannot go in that direction. There is nothing but dust over there.",true);
		this.addLocation(plains);
		Location mountain = new Location("mountain", "Northern Mountains. A labyrinth of razor sharp rocks.",
				"You cannot go in that direction. The Mountain is not so easily climbed.",true);
		this.addLocation(mountain);
		Location shore = new Location("shore", "Western Shore. The water is calm.",
				"You cannot go in that direction. There might be dangerous beasts in the water.",true);
		this.addLocation(shore);
		Location woods = new Location("woods", "King's Forest. A bright forest with high pines.",
				"You cannot go in that direction. Be careful not to get lost in the woods.",true);
		this.addLocation(woods);
		Location hills = new Location("hills", "Evalon hills.",
				"You cannot go in that direction.",true);
		this.addLocation(hills);
		Location cave = new Location("cave", "Blood Cave. Few of those who venture here ever return.",
				"The air smells foul over there, better not go in that direction.",true);
		this.addLocation(cave);
		Location innercave = new Location("innercave", "Blood Cave. This path must lead somewhere.",
				"Better not go over there.",true);
		
		// CONNECTING LOCATIONS
		// IT DOES NOT MATTER ON WHICH LOCATION THE METHOD ADDPATHS IS CALLED
		valley.addPaths(valley, "east", plains, "west");
		valley.addPaths(valley, "north", mountain, "south");
		valley.addPaths(valley, "west", shore, "east");
		valley.addPaths(valley, "south", woods, "north");
		woods.addPaths(woods, "east", hills, "west");
		mountain.addPaths(mountain, "cave", cave, "exit");
		
		// cave.addPaths(cave, "inwards", innercave, "entrance");
		
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
		
		Weapon swordEvalon = new Weapon("EvalonianSword", 15, 400, true, 1, 15, false);
		hills.addItem(swordEvalon);
		
		Potion potion2 = new Potion("largepotion", 2, 200, true, 25);
		Potion potion3 = new Potion("stuckpotion", 2, 200, false, 25);
		Potion potion4 = new Potion("heavypotion", 2000, 200, true, 25);
		
		Item potion5 = new Potion("potion", 1, 100, true, 10);
		Item potion6 = new Potion("potion", 1, 100, true, 10);
		woods.addItem(potion6);
		
		Purse purse = new Purse("purse",0,0,true,100);
		valley.addItem(purse);
		
		Chest chest = new Chest("chest", 50, 100, false, 50);
		valley.addItem(chest);
		chest.addItem(potion2);
		chest.addItem(potion3);
		chest.addItem(potion4);
		
		
		// ENEMY LOOT
		BodyArmor chestplate = new BodyArmor("chestplate", 20, 200, true, 20);
		
		// ADDING ENEMY TO WORLD
		Enemy enemy1 = new Enemy("Enemy", true, "Come at me!", 50, chestplate, 200, 10);
		mountain.addNpc(enemy1);
		
		EnemyGuardian guardian = new EnemyGuardian("Guardian", true, "I guard this cave.", 100, potion5, 600, 15, innercave, "An entrance reveals itself behind the fallen Guardian.", "inwards", "entrance");
		cave.addNpc(guardian);
		
		// ADDING SPAWNER TO WORLD
		this.setNpcSpawner(new BanditSpawner()); 
		
		// ADDING PLAYER TO THE WORLD
		this.getPlayer().setLocation(valley);
		
		// QUEST
		Quest noquest = new Quest("noquest","nodesc","nocomp",false,true,0,0,0,0);
		this.setCurrentQuest(noquest);
		
		
		Quest firstquest = new Quest("A New Journey","Find the lost sword of Evalon.","You have found the lost sword of Evalon!",false,false,1,0,1,200);
		
		Scroll scroll = new Scroll("Questscroll",1,1,true,firstquest);
		mountain.addItem(scroll);
		
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
			case "help": return this.help();
			default: return "You cannot do that.";
			}
		}
		else if(this.isLooting() == true) {
			switch(text[0]) {
			case "take": return this.takeChestItem(text[1]);
			case "place": return this.placeItemInChest(text[1]);
			case "look": return this.look();
			case "equip": return this.equipItem(text[1]);
			case "unequip": return this.unequipItem(text[1]);
			case "inventory": return this.inventory();
			case "equipment": return this.equipment();
			case "drink": return this.drink(text[1]);
			case "exit": return this.exitChest();
			case "help": return this.help();
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
			case "read": return this.read(text[1]);
			case "look": return this.look();
			case "quest": return this.quest();
			case "inventory": return this.inventory();
			case "equipment": return this.equipment();
			case "help": return this.help();
			case "loot": return this.loot(text[1]);
			case "attack": return this.engageEnemy(text[1]);
			case "talk": return this.talk(text[1]);
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
				this.spawnNpcOnChance(this.getPlayer().getCurrentLocation());
				return this.getPlayer().getCurrentLocation().describeYourself() + "\n" +
				"You can move in the following directions: " + this.getPlayer().getCurrentLocation().getPaths().keySet().toString() + ". \n";
			}
		}
		
		
		// returns description of current location, its available paths and items
		public String look() {
			if(this.isLooting() == true) {
				return this.getCurrentChest().printContent();
				// return this.getCurrentChest().getName() + " contains: "	+ this.getCurrentChest().getContent().keySet().toString() + ".";
			}
			else {
				return "You are at " + this.getPlayer().getCurrentLocation().getDescription() + "\n" +
						"You can move in the following directions: " + this.getPlayer().getCurrentLocation().getPaths().keySet().toString() + "\n" +
						this.getPlayer().getCurrentLocation().printItems() +
						this.getPlayer().getCurrentLocation().printNpcs();
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
			else if(this.getPlayer().getCurrentLocation().getItem(itemName).getClassName().equals("game.Purse")) {
				int gold = new Integer(((Purse)this.getPlayer().getCurrentLocation().getItem(itemName)).getGold());
				this.getPlayer().changeGold(((Purse)this.getPlayer().getCurrentLocation().getItem(itemName)).getGold());
				this.getPlayer().getCurrentLocation().removeItem(itemName);
				return "You gain " + gold + " gold.";
			}
			else if (this.getPlayer().getInventory().getContent().get(itemName) == null) { // if player inventory Itemstack is empty, no problem
				this.handleIncQuestrelatedItem(this.getPlayer().getCurrentLocation().getItem(itemName));
				this.getPlayer().addItem(this.getPlayer().getCurrentLocation().getItem(itemName));
				this.getPlayer().getCurrentLocation().removeItem(itemName);
				return "You take " + itemName + ". \n"
						+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight.";
			}
			else if(this.getPlayer().getInventory().getContent().get(itemName).getNumber() >= this.getPlayer().getInventory().getItemSlots()) { // more than 50 items in stack?
				return "You cannot carry any more items of this type.";
			}
			else {
				this.handleIncQuestrelatedItem(this.getPlayer().getCurrentLocation().getItem(itemName));
				this.getPlayer().addItem(this.getPlayer().getCurrentLocation().getItem(itemName));
				this.getPlayer().getCurrentLocation().removeItem(itemName);
				return "You take " + itemName + ". \n"
						+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight.";
			}
		}
		
		
		public String loot(String chestName) {
			if(this.getPlayer().getCurrentLocation().getItem(chestName) == null) {
				return "This item is not here.";
			}
			else if(!(this.getPlayer().getCurrentLocation().getItem(chestName).getClassName().equals("game.Chest"))) {
				return "You cannot loot this object.";
			}
			else {
				this.setIsLooting(true);
				this.setCurrentChest((Chest) this.getPlayer().getCurrentLocation().getItem(chestName));
				return this.getCurrentChest().printContent();
			}
		}
		
		/////////////////////////////////////////////////////////////////////	
		/////////////////////////////////////////////////////////////////////	
		// CHEST
		// TAKES CHEST CAPACITY IN CONSIDERATION:
		public String takeChestItem(String itemName) {
			if(this.getCurrentChest().getItem(itemName) == null) {
				return "There is no " + itemName + " in " + this.getCurrentChest().getName() + ".";
			} else if (this.getCurrentChest().getItem(itemName).isRemovable() == false) {
				return "You cannot take this item.";
			} else if (this.getPlayer().getCarryCapacity() < this.getCurrentChest().getItem(itemName).getWeight()) {
				return "This item is to heavy, drop some items you don't want to carry.";
			}
			else if(this.getCurrentChest().getItem(itemName).getClassName().equals("game.Purse")) {
				int gold = ((Purse)this.getCurrentChest().getItem(itemName)).getGold();
				this.getPlayer().changeGold(((Purse)this.getCurrentChest().getItem(itemName)).getGold());
				this.getCurrentChest().removeItem(itemName);
				return "You gain " + gold + " gold.";
			}
			else {
				this.handleIncQuestrelatedItem(this.getCurrentChest().getItem(itemName));
				this.getPlayer().addItem(this.getCurrentChest().getItem(itemName));
				this.getCurrentChest().removeItem(itemName);
				return "You take " + itemName + ". \n"
						+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight. \n"
						+ this.getCurrentChest().getName() + " now contains: "
						+ this.getCurrentChest().getContent().keySet().toString() + ".";
			}
		}
		

		// NEED TO CHECK IF ITEMSTACK IS FULL
		public String placeItemInChest(String itemName) {
			if(this.getPlayer().getItem(itemName) == null) {
				return "You do not have this item in your inventory.";
			}
			else if(this.getCurrentChest().getContent().get(itemName) == null) {
				this.handleDecQuestrelatedItem(this.getPlayer().getItem(itemName));
				this.getCurrentChest().addItem(this.getPlayer().getItem(itemName));
				this.getPlayer().removeItem(itemName);
				return "You put " + itemName + " in " + this.getCurrentChest().getName() + ". \n"
							+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight.";
			}
			else if(this.getCurrentChest().getContent().get(itemName).getNumber() >= this.getCurrentChest().getItemSlots()) {
				return this.getCurrentChest().getName() + " cannot contain any more items of this type. \n";
			}
			else {
				this.handleDecQuestrelatedItem(this.getPlayer().getItem(itemName));
				this.getCurrentChest().addItem(this.getPlayer().getItem(itemName));
				this.getPlayer().removeItem(itemName);
				return "You put " + itemName + " in " + this.getCurrentChest().getName() + ". \n"
							+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight.";
				}
		}
		
		public String exitChest() {
			if (this.isLooting() == false) {
				return "You are not looting";
			}
			else {
				this.setIsLooting(false);
				return "You are no longer looting.";
			}
		}
		
		/////////////////////////////////////////////////////////////////////	
		/////////////////////////////////////////////////////////////////////					
		// EQUIP DOES NOT AFFECT CARRIED WEIGHT
		// switchArmor METHODS AFFECTS MAX HEALTH
		public String equipItem(String itemName) {
			if(this.getPlayer().getItem(itemName) == null) {
				return "You do not have this item in your inventory.";
			}
			else {
				switch (this.getPlayer().getItem(itemName).getClassName()) {
					case "game.BodyArmor":
						this.getPlayer().switchBodyArmor(itemName);					
						break;
					case "game.Headgear":
						this.getPlayer().switchHeadgear(itemName);
						break;
					case "game.Gloves":
						this.getPlayer().switchGloves(itemName);
						break;
					case "game.Boots":
						this.getPlayer().switchBoots(itemName);
						break;
					case "game.Weapon":
						this.getPlayer().switchWeapon(itemName);
						break;
					default: return "You cannot equip this item.";
				}
				return "You equip " + itemName + ".";
			}
		}
		
		
		// UNEQUIP DOES NOT AFFECT CARRIED WEIGHT		
		public String unequipItem(String itemName) {
			if(itemName == "unarmored") {
				return "You do not have any armor to unequip."; // handles unarmored situation
			}
			else if(itemName.equals(this.getPlayer().getBodyArmor().getName())) {
				this.getPlayer().changeMaxHealth(0-this.getPlayer().getBodyArmor().getArmor());
				this.getPlayer().addUnequippedItemToInventory(this.getPlayer().getBodyArmor());
				this.getPlayer().setBodyArmor(this.getDefaultBodyArmor());		// set armor to default on unequip
				return "You unequip " + itemName + ".";
			}
			else if (itemName.equals(this.getPlayer().getHeadgear().getName())) {
				this.getPlayer().changeMaxHealth(0-this.getPlayer().getHeadgear().getArmor());
				this.getPlayer().addUnequippedItemToInventory(this.getPlayer().getHeadgear());
				this.getPlayer().setHeadgear(this.getDefaultHeadgear());		// set headgear to default on unequip 
				return "You unequip " + itemName + ".";
			}
			else if (itemName.equals(this.getPlayer().getGloves().getName())) {
				this.getPlayer().changeMaxHealth(0-this.getPlayer().getGloves().getArmor());
				this.getPlayer().addUnequippedItemToInventory(this.getPlayer().getGloves());
				this.getPlayer().setGloves(this.getDefaultGloves());			// set gloves to default on unequip 
				return "You unequip " + itemName + ".";
			}
			else if (itemName.equals(this.getPlayer().getBoots().getName())) {
				this.getPlayer().changeMaxHealth(0-this.getPlayer().getBoots().getArmor());
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
				this.handleDecQuestrelatedItem(this.getPlayer().getItem(itemName));
				this.getPlayer().getCurrentLocation().addItem(this.getPlayer().getItem(itemName));
				this.getPlayer().removeItem(itemName);
				return "You drop " + itemName + ". \n"
						+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight.";
			}
		}
		
		
		// returns information on the players items, carry capacity and gold
		public String inventory() {
			if(this.getPlayer().getInventory().getContent().isEmpty()) {
				return "You have no items in your inventory." + "\n"
						+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight. \n"
						+ "You have " + this.getPlayer().getGold() + " gold.";	
			}
			else {
			return "Your  " + this.getPlayer().getInventory().printContent()
				+ "You can carry " + this.getPlayer().getCarryCapacity() + " more units of weight. \n"
						+ "You have " + this.getPlayer().getGold() + " gold.";				
			}

		}
		
		
		// display equipped items
		// ADDING .replace("[", "").replace("]", "") AFTER STRING WOULD REMOVE BRACKETS
		public String equipment() {
			return this.getPlayer().presentEquippedItems();
		}
		
		// returns the commands available to the player
		public String help() {
			if(this.inBattle()) {
				return "You can use the commands [attack] and [escape] while in battle.";
			}
			else {
				return "You can use the commands: \n" + this.getPlayer().getPlayerCommands().keySet().toString() + ".";
			}
		}
		
		
		public String talk(String npcName) {
			if(this.getPlayer().getCurrentLocation().getNpc(npcName) == null) {
				return "This person is not here.";
			}
			else {
				return npcName + " says: " + this.getPlayer().getCurrentLocation().getNpc(npcName).getTalkLine();
			}
		}
		
		
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
				
				this.handleQuestrelatedEnemy(this.getCurrentEnemy());
				
				if(this.getCurrentEnemy().getLoot() != null) {
					this.getPlayer().getCurrentLocation().addItem(this.getCurrentEnemy().getLoot());
				}
				
				this.getPlayer().gainXp(this.getCurrentEnemy().getXpYield());
				this.getPlayer().getCurrentLocation().removeNpc(this.getCurrentEnemy().getName());
				if(this.getCurrentEnemy().getClass().getName().equals("game.EnemyGuardian")) {
					// adds paths between current location and location in the guardian, path names are specified in the guardian construction
					this.getPlayer().getCurrentLocation().addPaths(this.getPlayer().getCurrentLocation(), ((EnemyGuardian)this.getCurrentEnemy()).getPathTo(),
																	((EnemyGuardian)this.getCurrentEnemy()).getGuardedLocation(), ((EnemyGuardian)this.getCurrentEnemy()).getPathFrom());
					return "You defeated " + this.getCurrentEnemy().getName() + ". \n"
							+ ((EnemyGuardian)this.getCurrentEnemy()).getRevelationMessage() + "\n";
				}
				else {
					return "You defeated " + this.getCurrentEnemy().getName() + ".";					
				}
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
		/////////////////////////////////////////////////////////////////////		
		// QUESTS
		public Quest getCurrentQuest() {
			return this.quest;
		}
		
		public void setCurrentQuest(Quest quest) {
			this.quest = quest;
		}
		
		public String read(String scroll) {
			if(this.getPlayer().getItem(scroll) == null) {
				return "You do not have this item. \n";
			}
			else if(!(this.getPlayer().getItem(scroll).getClassName().equals("game.Scroll"))) {
				return "You cannot read " + scroll + ". \n";
			}
			else if(!(this.getCurrentQuest().isCompleted())) {
				return "You need to complete your current quest before accepting another. \n";
			}
			else {
				this.setCurrentQuest(((Scroll) this.getPlayer().getItem(scroll)).getQuest());
				this.getPlayer().removeItem(scroll);
				return "You accepted the quest '" + this.getCurrentQuest().getName() + "'.\n"
						+ "Task: " + this.getCurrentQuest().getDescription() + "\n";
			}
		}
		
		
		// SENDS COMPLETION MESSAGE AND REWARDS XP IF SENDMESSAGENEXT IS TRUE
		// CANNOT BE TRIGGERED AGAIN SINCE SENDMESSAGENEXT IS SET TO FALSE AFTER
		// SENDMESSAGENEXT CANNOT BE SET TO TRUE AGAIN AFTER QUEST IS COMPLETED
		// QUEST COMPLETION CAN NEVER BE UNDONE
		public String quest() {
			if(this.getCurrentQuest().getName().equals("noquest")) { // INITIAL QUEST CALLED "noquest"
				return "You have completed no quests, look for something to do!";
			}
			else if(this.getCurrentQuest().sendMessageNext()) {
				this.getCurrentQuest().setSendMessageNext(false);
				this.getPlayer().gainXp(this.getCurrentQuest().getXpYield());
				return this.getCurrentQuest().getCompletionMessage() + "\n" +
						"You gain " + this.getCurrentQuest().getXpYield() + " xp. \n";
			}
			else if(this.getCurrentQuest().isCompleted()) {
				return "You have completed your last quest, " + this.getCurrentQuest().getName() + ". \n"
						+ "You can now serach for a new quest. \n";
			}
			else {
				return "Current Quest: \n"
						+ this.getCurrentQuest().getName() + "\n"
						+ this.getCurrentQuest().getDescription() + "\n";

			}
		}
		
		// SENDMESSAGENEXT CANNOT BE SET TO TRUE AGAIN AFTER QUEST IS COMPLETED
		// QUEST COMPLETION CAN NEVER BE UNDONE
		private void handleIncQuestrelatedItem(Item item) {
			if(item.getQuestId() == this.getCurrentQuest().getId()) {
				this.getCurrentQuest().incrementProgress();
			}
			if(!(this.getCurrentQuest().isCompleted()) && this.getCurrentQuest().getProgress() >= this.getCurrentQuest().getGoal()) {
				this.getCurrentQuest().setQuestCompleted(true);
				this.getCurrentQuest().setSendMessageNext(true);
			}
		}
		
		// DECREASES QUESTPROGESS RELATED TO THE ITEM
		// DOES NOT AFFECT COMPLETION STATUS
		private void handleDecQuestrelatedItem(Item item) {
			if(item.getQuestId() == this.getCurrentQuest().getId()) {
				this.getCurrentQuest().decrementProgress();
			}
		}
		
		// INCREASES ENEMY-RELATED QUEST PROGRESS
		private void handleQuestrelatedEnemy(Enemy enemy) {
			if(enemy.getQuestId() == this.getCurrentQuest().getId()) {
				this.getCurrentQuest().incrementProgress();
			}
			if(this.getCurrentQuest().getProgress() >= this.getCurrentQuest().getGoal()) {
				this.getCurrentQuest().setQuestCompleted(true);
				this.getCurrentQuest().setSendMessageNext(true);
			}
		}
		
		/////////////////////////////////////////////////////////////////////	
		/////////////////////////////////////////////////////////////////////		
		// SPAWN NEW ENEMY AT LOCATION
		// CANNOT SPAWN TWO ENEMIES WITH THE SAME NAME STRING AT THE SAME LOCATION
		public void spawnEnemy(Location location) {
			location.addNpc((Npc) this.getNpcSpawner().newObject());
		}
		
		public void spawnItem(Location location) {
			location.addItem((Item) this.getPotionSpawner().newObject());
		}
		
		public Spawner getNpcSpawner() {
			return this.npcSpawner;
		}
		
		public void setNpcSpawner(BanditSpawner spawner) {
			this.npcSpawner = spawner;
		}
		
		public Spawner getPotionSpawner() {
			return this.itemSpawner;
		}
		
		public void setPotionSpawner(PotionSpawner spawner) {
			this.itemSpawner = spawner;
		}
		
		
		// 20% CHANCE TO SPAWN A BANDIT AT LOCATION
		public void spawnNpcOnChance(Location location) {
			if(location.isOutdoors() && Math.random() < 0.20) {
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
		
		
		
		/////////////////////////////////////////////////////////////////////
		// HANDLING LOOTING PARAMETERS
		public boolean isLooting() {
			return this.isLooting;
		}
		
		public void setIsLooting(boolean isLooting) {
			this.isLooting = isLooting;
		}
		
		public Chest getCurrentChest() {
			return this.currentChest;
		}
		
		public void setCurrentChest(Chest chest) {
			this.currentChest = chest;
		}
		
}
