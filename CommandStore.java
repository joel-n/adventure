<<<<<<< HEAD
package game;
import java.util.HashMap;

public class CommandStore extends HashMap<String, Command> {

	
	// might not need to define name and command but simply load
	// into the HashMap when initializing game
	// private String commandName;
	// private Command command;
	
	
	public void addCommand(Command command) {
		this.put(command.getName(), command);
	}
	
	
	
}
=======
package game;
import java.util.HashMap;

public class CommandStore extends HashMap<String, Command> {

	
	// might not need to define name and command but simply load
	// into the HashMap when initializing game
	// private String commandName;
	// private Command command;
	
	
	public void addCommand(Command command) {
		this.put(command.getName(), command);
	}
	
	
	
}
>>>>>>> 5a41785323f74f228043c65d87c056aeff7196d9
