package game;

public class Item {

	private String name;
	private int weight;
	private int price;
	private boolean removable;
	
	public Item(String name,int weight, int price, boolean removable) {
		this.setName(name);
		this.setWeight(weight);
		this.setPrice(price);
		this.setRemovable(removable);
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
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public boolean isRemovable() {
		return this.removable;
	}
	
	public void setRemovable(boolean removable) {
		this.removable = removable;
	}
	
	public String getClassName() {
		return this.getClass().getName();
	}
	
}
