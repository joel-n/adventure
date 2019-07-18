package game;

public class Command {

	private String commandName;
	private String description;

	
	public Command(String name, String desc) {
		this.setName(name);
		this.setDescription(desc);
	}
	
	public void setName(String string) {
		this.commandName = string;
	}
	
	public String getName() {
		return this.commandName;
	}
	
	
	public void setDescription(String string) {
		this.description = string;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	
}
