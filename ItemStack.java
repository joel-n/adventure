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
	
	public void incrementNumber() {
		this.setNumber(this.getNumber() + 1);
	}
	
	public void decrementNumber() {
		this.setNumber(this.getNumber() - 1);
	}
	
	public ArrayList<Item> getItemList(){
		return this.items;
	}
	
	public void setItemList(ArrayList<Item> list) {
		this.items = list;
	}
	
	public void addItem(Item item) {
		this.getItemList().add(item);
	}
	
	public Item getItem() {
		return this.getItemList().get(0);
	}
	
	public void removeItem() {
		this.getItemList().remove(0);
	}
	
}
