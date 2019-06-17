package game;

public class Item {

	private String name;
	private int weight;
	private int price;
	private boolean removable;
	
	public Item(String name,int weight, int price, boolean removable) {
		this.setName(name);
		this.weight = weight;
		this.price = price;
		this.removable = removable;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public boolean isRemovable() {
		return this.removable;
	}
	
	public String getClassName() {
		return this.getClass().getName();
	}
	
}
