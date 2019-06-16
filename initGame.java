package game;

import game.GameFrame;
import game.Game;

public class initGame {

	public static void main(String[] arguments) {
    	Game gameInstance = new Game();
		GameFrame frame = new GameFrame();
    	frame.createAndShowGUI();
    	frame.setGame(gameInstance);
    	
    	
    }	
	
}
