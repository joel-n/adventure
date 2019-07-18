package game;

public class Scroll extends Item {
	
	private Quest quest;
	
	public Scroll(String name,int weight, int price, boolean removable, Quest quest) {
		super(name,weight,price,removable);
		this.setQuest(quest);
	}
	
	public Quest getQuest() {
		return this.quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}
}
