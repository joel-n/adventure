package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class XpBar extends JPanel {
	
	// int w = getWidth();
	// int h = getHeight();
	
	private int percent;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0,0,500,25);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 5*percent, 25);
	}
	
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	public int getPercent() {
		return this.percent;
	}
	
	
}
