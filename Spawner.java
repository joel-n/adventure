package game;

public class Spawner {

	private Enemy spawnedEnemy;
	
	public Spawner() {
		this.setSpawnedEnemy(new Enemy("Bandit", true, "Talk is cheap.", 50, null, 20, 10));
	}
	
	public void setSpawnedEnemy(Enemy enemy) {
		this.spawnedEnemy = enemy;
	}
	
	public Enemy getSpawnedEnemy() {
		return this.spawnedEnemy;
	}
	
}
