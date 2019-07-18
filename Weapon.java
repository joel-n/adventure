package game;

public class Weapon extends Item {

	private int attack;
	private boolean isRanged;
	
	
	public Weapon(String name,int weight, int price, boolean removable, int attack, boolean ranged) {
		super(name,weight,price,removable);
		this.setAttack(attack);
		this.setRanged(ranged);
	}
	
	public Weapon(String name,int weight, int price, boolean removable, int questId, int attack, boolean ranged) {
		super(name,weight,price,removable,questId);
		this.setAttack(attack);
		this.setRanged(ranged);
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public int getAttack() {
		return this.attack;
	}
	
	public void setRanged(boolean ranged) {
		this.isRanged = ranged;
	}
	
	public boolean getRanged() {
		return this.isRanged;
	}
	
	
}
