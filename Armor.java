<<<<<<< HEAD
package game;

public class Armor extends Item {

	private int armor;
	// private int attackBuff;
	// private int healthBuff;
	
	public Armor(String name,int weight, int price, boolean removable, int armor) {
		super(name,weight,price,removable);
		this.setArmor(armor);
	}
	
	public int getArmor() {
		return this.armor;
	}
	
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	
	
	
}
=======
package game;

public class Armor extends Item {

	private int armor;
	// private int attackBuff;
	// private int healthBuff;
	
	public Armor(String name,int weight, int price, boolean removable, int armor) {
		super(name,weight,price,removable);
		this.setArmor(armor);
	}
	
	public int getArmor() {
		return this.armor;
	}
	
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	
	
	
}
>>>>>>> 5a41785323f74f228043c65d87c056aeff7196d9
