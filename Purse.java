package game;

public class Purse extends Item {

	private int gold;
	
	public Purse(String name,int weight, int price, boolean removable, int gold) {
		super(name,weight,price,removable);
		this.setGold(gold);
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public int getGold() {
		return this.gold;
	}
	
}
