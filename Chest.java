package game;

import java.util.HashMap;

public class Chest extends Item {

	private HashMap<String, ItemStack> content;
	private int itemWeight;
	
	public Chest(String name, int weight, int price, boolean removable) {
		super(name, weight, price, removable);
		HashMap<String, ItemStack> content = new HashMap<String, ItemStack>();
		this.setContent(content);
		this.setItemWeight(0);
	}
	
	public void setContent(HashMap<String, ItemStack> content) {
		this.content = content;
	}
	
	public HashMap<String, ItemStack> getContent(){
		return this.content;
	}
	
	@Override
	public int getWeight() {
		return (this.getWeight() + this.getItemWeight()); 
	}
	
	// ADD AND REMOVE ITEM; WEIGHT IS UPDATED 
	public void addItem(Item item) {
		if(this.getContent().get(item.getName()) == null) {
			ItemStack itemStack = new ItemStack(50);
			itemStack.addItem(item);
			this.getContent().put(item.getName(), itemStack);
		}
		else {
			this.getContent().get(item.getName()).addItem(item);
			this.setItemWeight(this.getItemWeight() + item.getWeight());
		}
	}
	
	public void removeItem(String itemName) {
		if(this.getContent().get(itemName).getNumber() <= 1) {
			this.setItemWeight(this.getItemWeight() - this.getItem(itemName).getWeight()); // modify weight before removing item
			this.getContent().remove(itemName);
		}
		else {
			this.setItemWeight(this.getItemWeight() - this.getItem(itemName).getWeight()); // modify weight before removing item
			this.getContent().get(itemName).removeItem();
		}
	}
	
	// returns null if item does not exist
	public Item getItem(String itemName) {
		if(this.getContent().get(itemName) == null) {
			return null;
		}
		else {
			return this.getContent().get(itemName).getItem();
		}
	}
	
	public void setItemWeight(int weight) {
		this.itemWeight = weight;
	}
	
	public int getItemWeight() {
		return this.itemWeight;
	}
	
	// TO DO
	// public String printContent() {
		// String s = new String("");
		// for()
		// return this.getContent().keySet().toString();
	// }
	
}
