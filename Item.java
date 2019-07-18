<<<<<<< HEAD
package game;

public class Item {

	private String name;
	private int weight;
	private int price;
	private boolean removable;
	
	private int questId;
	
	public Item(String name,int weight, int price, boolean removable) {
		this.setName(name);
		this.setWeight(weight);
		this.setPrice(price);
		this.setRemovable(removable);
		this.setQuestId(0);
	}
	
	public Item(String name,int weight, int price, boolean removable, int questId) {
		this.setName(name);
		this.setWeight(weight);
		this.setPrice(price);
		this.setRemovable(removable);
		this.setQuestId(questId);
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
	
	public void setQuestId(int id) {
		this.questId = id;
	}
	
	public int getQuestId() {
		return this.questId;
	}

	
}
=======
package game;

public class Item {

	private String name;
	private int weight;
	private int price;
	private boolean removable;
	
	private int questId;
	
	public Item(String name,int weight, int price, boolean removable) {
		this.setName(name);
		this.setWeight(weight);
		this.setPrice(price);
		this.setRemovable(removable);
		this.setQuestId(0);
	}
	
	public Item(String name,int weight, int price, boolean removable, int questId) {
		this.setName(name);
		this.setWeight(weight);
		this.setPrice(price);
		this.setRemovable(removable);
		this.setQuestId(questId);
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
	
	public void setQuestId(int id) {
		this.questId = id;
	}
	
	public int getQuestId() {
		return this.questId;
	}

	
}
>>>>>>> 5a41785323f74f228043c65d87c056aeff7196d9
