package game;

import java.util.HashMap;

public class Location {

	private String name;
	private String description;
	private String onWrongPathMessage;
	private HashMap<String, Location> paths;
	private HashMap<String, Item> items;
	private HashMap<String, Npc> npcs;
	
	private boolean outdoors;
	
	public Location(String name, String description, String wrongPathMessage, boolean outdoors) {
		this.setName(name);
		this.setDescription(description);
		this.setOnWrongPathMessage(wrongPathMessage);
		this.setOutdoors(outdoors);
		HashMap<String, Item> itemHash = new HashMap<String, Item>();
		HashMap<String, Location> locationHash = new HashMap<String, Location>();
		HashMap<String, Npc> npcHash = new HashMap<String, Npc>();
		setLocationHashes(locationHash, itemHash, npcHash);
	}
	
	public void setLocationHashes(HashMap<String, Location> pathHash, HashMap<String, Item> itemHash, HashMap<String, Npc> npcHash) {
		this.paths = pathHash;
		this.items = itemHash;
		this.npcs = npcHash;
	}
	
	public void addPaths(Location location1, String pathName1, Location location2, String pathName2) {
		location1.getPaths().put(pathName1, location2); // location1 has a path (pathname1) to location2
		location2.getPaths().put(pathName2, location1); // location2 has a path (pathname2) to location1
	}
	
	public void addItem(Item item) {
		this.getPlaceInventory().put(item.getName(), item);
	}
	
	public void removeItem(String itemName) {
		this.getPlaceInventory().remove(itemName);
	}

	
	// returns item if found, else returns null
	public Item getItem(String enteredItem) {
		return this.getPlaceInventory().get(enteredItem);
	}
	
	public HashMap<String, Item> getPlaceInventory(){
		return this.items;
	}
	
	
	public HashMap<String, Npc> getLocationNpcs(){
		return this.npcs;
	}
	
	// returns NPC if found, else returns null	
	public Npc getNpc(String npcName) {
		return this.getLocationNpcs().get(npcName);
	}
	
	public void addNpc(Npc npc) {
		this.getLocationNpcs().put(npc.getName(), npc);
	}
	
	public void removeNpc(String npcName) {
		this.getLocationNpcs().remove(npcName);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getOnWrongPathMessage() {
		return this.onWrongPathMessage;
	}
	
	public void setOnWrongPathMessage(String message) {
		this.onWrongPathMessage = message;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public HashMap<String, Location> getPaths() {
		return this.paths;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String describeYourself() {
		return this.getDescription();
	}
	
	
	public void setOutdoors(boolean outdoors) {
		this.outdoors = outdoors;
	}
	
	public boolean isOutdoors() {
		return this.outdoors;
	}

}
