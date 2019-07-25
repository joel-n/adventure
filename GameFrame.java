package game;

import game.Game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class GameFrame extends JFrame {
	
	private Game game;
	private JButton changeableButton;
	
	public Game getGame() {
		return this.game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public JButton getChangeableButton() {
		return this.changeableButton;
	}
	
	public void setChangeableButton(JButton button) {
		this.changeableButton = button;
	}
	
	///////////////////////////////////////////////// HELP METHODS
	// USED TO CHANGE UI WHEN SWITCHING BETWEEN LOOTING AND BATTLE
    private boolean inBattle() {
    	return this.getGame().inBattle();
    }

    private boolean isLooting() {
    	return this.getGame().isLooting();
    }
    
    private boolean isTrading() {
    	return this.getGame().isTrading();
    }
    
    
    public void changeButton(JTextField input, JTextArea output, JPanel buttonPanel) {
    	if(this.isLooting() || this.isTrading()) {
    		this.getChangeableButton().setText("Exit");
    		for(ActionListener al : this.getChangeableButton().getActionListeners()) {
        		this.getChangeableButton().removeActionListener(al);
    		}
    		this.getChangeableButton().addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent event) {
    				handleInputForButton(input,output,"exit",buttonPanel);
    			}
    		});
    		buttonPanel.repaint();
        }
    	else if(this.inBattle()) {
    		this.getChangeableButton().setText("Escape");
    		for(ActionListener al : this.getChangeableButton().getActionListeners()) {
        		this.getChangeableButton().removeActionListener(al);
    		}
    		this.getChangeableButton().addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent event) {
    				handleInputForButton(input,output,"escape",buttonPanel);
    			}
    		});
    		buttonPanel.repaint();
        }
    	else {
    		this.getChangeableButton().setText("Look");
    		for(ActionListener al : this.getChangeableButton().getActionListeners()) {
        		this.getChangeableButton().removeActionListener(al);
    		}
    		this.getChangeableButton().addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent event) {
    				handleInputForButton(input,output,"look",buttonPanel);
    			}
    		});
    		buttonPanel.repaint();
    	}
    }


	// FRAME
		public void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Evalon");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);

	        JLabel emptyLabel = new JLabel("");
	        emptyLabel.setPreferredSize(new Dimension(600, 600));
	        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
	        
	        
	        JPanel levelpanel = new JPanel();
	        
	        JTextArea output = new JTextArea("",20,20);
	        output.setEditable(false);
	        
	        // Makes sure window does not scroll down, but rather stays at the top
	        DefaultCaret caret = (DefaultCaret) output.getCaret();
	        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	        
	        JScrollPane scrollableOutput = new JScrollPane(output);
	        scrollableOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        
	        JTextArea xp = new JTextArea("",1,1);
	        xp.setEditable(false);
	        xp.setOpaque(false);
	        xp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        
	        JTextArea levelbar = new JTextArea("",1,1);
	        levelbar.setEditable(false);
	        levelbar.setOpaque(false);
	        levelbar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        
	        
	        XpBar xpbar = new XpBar();
	              
	        HealthBar healthbar = new HealthBar();
	        JTextField healthtext = new JTextField("");
	        healthtext.setEditable(false);
	        healthtext.setOpaque(false);
	        healthtext.setBorder(javax.swing.BorderFactory.createEmptyBorder());

	        JTextField input = new JTextField("", 30);
	        
	        JPanel buttonPanel = new JPanel();
	        
	        input.addKeyListener(new KeyAdapter() {
	        	public void keyPressed(KeyEvent event) {
	        		if(event.getKeyCode() == KeyEvent.VK_ENTER) {
	        		handleInput(input,output);
	        		updateXpAndLevel(xp,levelbar,xpbar);
	        		updateHealthBar(healthbar, healthtext);
	        		changeButton(input,output,buttonPanel);
	        		}
	            }
	        });
	        
	        
	        JButton inventoryButton = new JButton("Inventory");
	        inventoryButton.setPreferredSize(new Dimension(100, 50));
	        inventoryButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent event) {
	        		handleInputForButton(input,output,"inventory",buttonPanel);
	        	}
	        });
	        
	        JButton equipmentButton = new JButton("Equipment");
	        equipmentButton.setPreferredSize(new Dimension(100, 80));
	        equipmentButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent event) {
	        		handleInputForButton(input,output,"equipment",buttonPanel);
	        	}
	        });
	        
	        JButton changeableButton = new JButton("Look");
	        changeableButton.setPreferredSize(new Dimension(100, 80));
	        changeableButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent event) {
	        		handleInputForButton(input,output,"look",buttonPanel);
	        	}
	        });
	        
	        
	        JPanel inputPanel = new JPanel();
	        

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	        
	        Container container = frame.getContentPane();
	        container.setLayout(new BorderLayout());
	        levelpanel.setLayout(new BorderLayout());
	        container.add(levelpanel, BorderLayout.PAGE_START);
	        container.add(scrollableOutput, BorderLayout.CENTER); // added if output should be scrollable
	        //container.add(output, BorderLayout.CENTER); // added if should not be scrollable
	        container.add(inputPanel, BorderLayout.PAGE_END);
	        
	        inputPanel.setLayout(new BorderLayout());
	        inputPanel.add(buttonPanel, BorderLayout.PAGE_START);
	        inputPanel.add(input, BorderLayout.PAGE_END);
	        
	        // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
	        buttonPanel.setLayout(new GridLayout(1,3));
	        buttonPanel.add(inventoryButton);
	        buttonPanel.add(equipmentButton);
	        
	        this.setChangeableButton(changeableButton);
	        buttonPanel.add(this.getChangeableButton());
	        
	        levelpanel.add(xpbar, BorderLayout.CENTER);
	        
	        xpbar.add(levelbar, BorderLayout.LINE_START);
	        xpbar.add(xp, BorderLayout.LINE_END);
	        
	        levelpanel.add(healthbar, BorderLayout.PAGE_END);
	        healthbar.add(healthtext, BorderLayout.PAGE_END);
	        
	        // MESSAGE AT GAME START
	        output.setText("Welcome to the world of Evalon! \n"
	        		+ "Type \"help\" and press Enter to get a list of available commands. ");
	        
	    }
		
	    public void handleInput(JTextField input, JTextArea output) {
	    	String argument = input.getText();
	    	output.setText(argument + "\n \n" + output.getText() + "\n "); // printing what user wrote directly
	    	
	    	this.presentNew(output, this.getGame().executeCommand(argument));
	    	
	    	input.setText("");
	    }
	    
	    public void handleInputForButton(JTextField input, JTextArea output, String argument, JPanel buttonPanel) {
	    	this.presentNew(output, this.getGame().executeCommand(argument) + "\n");
	    	input.setText("");
	    	
    		changeButton(input,output,buttonPanel);
    		// buttonPanel.revalidate();
    		// buttonPanel.repaint();
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
	    
	    public void updateHealthBar(HealthBar healthbar, JTextField healthtext) {
	    	healthbar.setPercent((int) (Math.floor(100*(this.getGame().getPlayer().getHealth())/this.getGame().getPlayer().getMaxHealth())));
	    	healthtext.setText("Health: " + this.getGame().getPlayer().getHealth() +"/" +this.getGame().getPlayer().getMaxHealth());
	    	healthbar.repaint();
	    }
	    
	    
	    /////////////////////////////////////////////////////////////
	    // MIGHT BE UNNECESSARY/INCORRECT TO ADD REMOVE BUTTONS
	    // CALLING CHANGE BUTTON AND REPAINTING BETTER
	    public void removeChangeableButton(JPanel buttonPanel) {
	    	buttonPanel.remove(this.getChangeableButton());
	    }
	    
	    public void addChangeableButton(JPanel buttonPanel) {
	    	buttonPanel.add(this.getChangeableButton());
	    }
	    /////////////////////////////////////////////////////////////

}
