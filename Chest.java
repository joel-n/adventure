package game;

import java.util.HashMap;

public class Chest extends Item {

	private HashMap<String, Item> content;
	private int itemWeight;
	
	public Chest(String name, int weight, int price, boolean removable) {
		super(name, weight, price, removable);
		HashMap<String, Item> content = new HashMap<String, Item>();
		this.setContent(content);
		this.setItemWeight(0);
	}
	
	public void setContent(HashMap<String, Item> content) {
		this.content = content;
	}
	
	public HashMap<String, Item> getContent(){
		return this.content;
	}
	
	@Override
	public int getWeight() {
		return (this.getWeight() + this.getItemWeight()); 
	}
	
	// ADD AND REMOVE ITEM; WEIGHT IS UPDATED 
	public void addItem(Item item) {
		this.getContent().put(item.getName(), item);
		this.setItemWeight(this.getItemWeight() + item.getWeight());
	}
	
	public void removeItem(String itemName) {
		this.setItemWeight(this.getItemWeight() - this.getItem(itemName).getWeight());
		this.getContent().remove(itemName);
	}
	
	public Item getItem(String itemName) {
		return this.getContent().get(itemName);
	}
	
	public void setItemWeight(int weight) {
		this.itemWeight = weight;
	}
	
	public int getItemWeight() {
		return this.itemWeight;
	}
	
}
