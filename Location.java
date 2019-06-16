package game;

import java.util.HashMap;

public class Location {

	private String name;
	private String description;
	private HashMap<String, Location> paths;
	private HashMap<String, Item> items;
	
	public Location(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void setLocationHashes(HashMap<String, Location> pathHash, HashMap<String, Item> itemHash) {
		this.paths = pathHash;
		this.items = itemHash;
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
	
	public String getName() {
		return this.name;
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
	
	
	public void doCommand(String string) {
		
	}

}