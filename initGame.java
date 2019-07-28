package game;

import game.GameFrame;
import game.Game;

public class initGame {

	public static void main(String[] arguments) {
    	Game gameInstance = new Game();
		GameFrame frame = new GameFrame();
		frame.setGame(gameInstance); // GAME INSTANCE HAS TO BE SET BEFORE CREATING GUI IF INITIAL UPDATE/PAINT FUNCTIONS ARE TO WORK
    	frame.createAndShowGUI();
	}
	
}
