<<<<<<< HEAD
package game;

public class Npc {
	
	private String name;
	private boolean attackable;
	private String talkLine; 
	// possible later to implement custom cannotattackLine "not able to attack this person, too strong/is friend" etc
	
	public Npc(String name, boolean attackable, String talkLine) {
		this.setName(name);
		this.setAttackable(attackable);
		this.setTalkLine(talkLine);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isAttackable() {
		return this.attackable;
	}
	
	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}
	
	public String getTalkLine() {
		return this.talkLine;
	}
	
	public void setTalkLine(String talkLine) {
		this.talkLine = talkLine;
	}

}
=======
package game;

public class Npc {
	
	private String name;
	private boolean attackable;
	private String talkLine; 
	// possible later to implement custom cannotattackLine "not able to attack this person, too strong/is friend" etc
	
	public Npc(String name, boolean attackable, String talkLine) {
		this.setName(name);
		this.setAttackable(attackable);
		this.setTalkLine(talkLine);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isAttackable() {
		return this.attackable;
	}
	
	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}
	
	public String getTalkLine() {
		return this.talkLine;
	}
	
	public void setTalkLine(String talkLine) {
		this.talkLine = talkLine;
	}

}
>>>>>>> 5a41785323f74f228043c65d87c056aeff7196d9
