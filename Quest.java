package game;

public class Quest {
	
	private String name;
	private String description;
	private String completionMessage;
	private boolean sendMessageNext;
	private boolean completed;
	private int id;
	private boolean givesReward;
	private Item reward;
	private int xpYield;
	
	private int progress;
	private int goal;
	
	public Quest(String name, String description, String completionMessage, boolean sendMessageNext, boolean completed, int id, int xpYield, int progress, int goal, Item reward) {
		this.setName(name);
		this.setDescription(description);
		this.setCompletionMessage(completionMessage);
		this.setSendMessageNext(sendMessageNext);
		this.setQuestCompleted(completed);
		this.setId(id);
		this.setXpYield(xpYield);
		this.setProgress(progress);
		this.setGoal(goal);
		this.setGivesReward(true);
		this.setReward(reward);
	}
	
	public Quest(String name, String description, String completionMessage, boolean sendMessageNext, boolean completed, int id, int xpYield, int progress, int goal) {
		this.setName(name);
		this.setDescription(description);
		this.setCompletionMessage(completionMessage);
		this.setSendMessageNext(sendMessageNext);
		this.setQuestCompleted(completed);
		this.setId(id);
		this.setXpYield(xpYield);
		this.setProgress(progress);
		this.setGoal(goal);
		this.setGivesReward(false);
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
	
	public void setSendMessageNext(boolean sendMessageNext) {
		this.sendMessageNext = sendMessageNext;
	}
	
	public boolean sendMessageNext() {
		return this.sendMessageNext;
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public int getProgress() {
		return this.progress;
	}
	
	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	public int getGoal() {
		return this.goal;
	}
	
	public void setXpYield(int xpYield) {
		this.xpYield = xpYield;
	}
	
	public int getXpYield() {
		return this.xpYield;
	}
	
	public void incrementProgress() {
		this.setProgress(this.getProgress() + 1);
	}
	
	public void decrementProgress() {
		this.setProgress(this.getProgress() - 1);
	}
}