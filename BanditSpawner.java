package game;

public class BanditSpawner extends Spawner{
	
	public BanditSpawner() {
	}
		
	public Enemy newObject() {
		return new Enemy("Bandit", true, "Talk is cheap.", 50, null, 20, 10);
	}
}
