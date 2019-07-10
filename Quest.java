package game;

public class Quest {
	
	private String name;
	private String description;
	private String completionMessage;
	private boolean active;
	private boolean completed;
	private boolean givesReward;
	private Item reward;
	
	public Quest(String name, String description, String completionMessage, boolean active, boolean completed, boolean givesReward, Item reward) {
		this.setName(name);
		this.setDescription(description);
		this.setCompletionMessage(completionMessage);
		this.setQuestActive(active);
		this.setQuestCompleted(completed);
		this.setGivesReward(givesReward);
		if(this.givesReward()) {
			this.setReward(reward);
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setCompletionMessage(String cm) {
		this.completionMessage = cm;
	}
	
	public String getCompletionMessage() {
		return this.completionMessage;
	}
	
	public void setQuestActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public void setQuestCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public boolean isCompleted() {
		return this.completed;
	}
	
	public void setGivesReward(boolean givesReward) {
		this.givesReward = givesReward;
	}
	
	public boolean givesReward() {
		return this.givesReward;
	}
	
	public void setReward(Item item) {
		this.reward = item;
	}
	
	public Item getReward() {
		return this.reward;
	}
	
	public String getRewardName() {
		return this.getReward().getName();
	}
	
}
