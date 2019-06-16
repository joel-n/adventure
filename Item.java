package game;

public class Item {

	private String name;
	private int weight;
	private int price;
	private boolean removable;
	
	public Item(String name) {
		this.setName(name);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
