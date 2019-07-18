<<<<<<< HEAD
package game;

public class Potion extends Item {

	private int healing;
	
	public Potion(String name,int weight, int price, boolean removable, int healing) {
		super(name,weight,price,removable);
		this.setHealing(healing);
		}
	
	public int getHealing() {
		return this.healing;
	}
	
	public void setHealing(int amount) {
		this.healing = amount;
	}
	
}
=======
package game;

public class Potion extends Item {

	private int healing;
	
	public Potion(String name,int weight, int price, boolean removable, int healing) {
		super(name,weight,price,removable);
		this.setHealing(healing);
		}
	
	public int getHealing() {
		return this.healing;
	}
	
	public void setHealing(int amount) {
		this.healing = amount;
	}
	
}
>>>>>>> 5a41785323f74f228043c65d87c056aeff7196d9
