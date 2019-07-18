package game;

import java.util.ArrayList;

public class ItemStack {

	private ArrayList<Item> items;
	private int number;
	
	public ItemStack(int capacity) {
		ArrayList<Item> itemsList = new ArrayList<Item>(capacity);
		this.setItemList(itemsList);
		this.setNumber(0);
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public ArrayList<Item> getItemList(){
		return this.items;
	}
	
	public void setItemList(ArrayList<Item> list) {
		this.items = list;
	}
	
	// ADDING AUTOMATICALLY INCREMENTS NUMBER IN STACK
	public void addItem(Item item) {
		this.getItemList().add(item);
		this.incrementNumber();
	}
	
	public Item getItem() {
		return this.getItemList().get(0);
	}
	
	// REMOVING AUTOMATICALLY DECREMENTS NUMBER IN STACK
	public void removeItem() {
		this.getItemList().remove(0);
		this.decrementNumber();
	}
	
	public void incrementNumber() {
		this.setNumber(this.getNumber() + 1);
	}
	
	public void decrementNumber() {
		this.setNumber(this.getNumber() - 1);
	}
	
}
