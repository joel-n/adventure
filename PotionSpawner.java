package game;

public class PotionSpawner extends Spawner {
	
	public PotionSpawner() {
		
	}
	
	public Potion newObject() {
		return new Potion("small_potion",1,10,true,20);
	}

}
