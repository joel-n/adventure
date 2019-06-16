package game;

import game.Game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GameFrame extends JFrame {
	
	private Game game;
	
	public Game getGame() {
		return this.game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	// FRAME
		public void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Frame");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);

	        JLabel emptyLabel = new JLabel("");
	        emptyLabel.setPreferredSize(new Dimension(600, 600));
	        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
	        
	        
	        JPanel levelpanel = new JPanel();
	        
	        JTextArea output = new JTextArea("",20,20);
	        output.setEditable(false);	       
	        	                
	        JTextArea xp = new JTextArea("",1,1);
	        xp.setEditable(false);
	        
	        JTextArea levelbar = new JTextArea("",1,1);
	        levelbar.setEditable(false);
	        
	        
	        XpBar xpbar = new XpBar();
	              
	        

	        JTextField input = new JTextField("", 30);
	        
	        
	        
	        input.addKeyListener(new KeyAdapter() {
	        	public void keyPressed(KeyEvent event) {
	        		if(event.getKeyCode() == KeyEvent.VK_ENTER) {
	        		handleInput(input,output);
	        		updateXpAndLevel(xp,levelbar,xpbar);
	        		}
	            }
	        });
	        

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	        
	        Container container = frame.getContentPane();
	        container.setLayout(new BorderLayout());
	        levelpanel.setLayout(new BorderLayout());
	        container.add(levelpanel, BorderLayout.PAGE_START);
	        container.add(output, BorderLayout.CENTER);
	        container.add(input, BorderLayout.PAGE_END);
	        
	        levelpanel.add(levelbar, BorderLayout.LINE_START);
	        levelpanel.add(xp, BorderLayout.LINE_END);
	        
	        levelpanel.add(xpbar, BorderLayout.CENTER);
	        
	        // MESSAGE AT GAME START
	        output.setText("Welcome to the world of Evalon! \n"
	        		+ "Type \"help\" and press Enter to get a list of available commands. ");

	    }
		
	    public void handleInput(JTextField input, JTextArea output) {
	    	String argument = input.getText();
	    	output.setText(argument + "\n" + output.getText() + "\n "); // printing what user wrote directly
	    	
	    	this.presentNew(output, this.getGame().executeCommand(argument));
	    	
	    	input.setText("");
	    }
	    
	    public void presentNew(JTextArea output, String string) {
	    	output.setText(string + "\n" + output.getText());
	    }
	    
	    public void updateXpAndLevel(JTextArea xp,JTextArea levelbar, XpBar xpbar) {
	    	xp.setText("XP: " + String.valueOf(this.getGame().getPlayer().getXp()) + "/" + String.valueOf(this.getGame().getPlayer().getNextLevelLimit()));
	    	levelbar.setText("Level: " + String.valueOf(this.getGame().getPlayer().getLevel()));
	    	
	    	xpbar.setPercent((int) (Math.floor(100*(this.getGame().getPlayer().getXp())/this.getGame().getPlayer().getNextLevelLimit())));
	    	xpbar.repaint();
	    }
	    
	    
	    /*
	    public static void main(String[] arguments) {
	    	GameFrame frame = new GameFrame();
	    	frame.createAndShowGUI();
	    }
	    */
	    
	    
	    /*
		public static void main(String[] arguments) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	createAndShowGUI();
	            }
	        });
		}
		*/
	
}
