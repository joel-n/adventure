package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HealthBar extends JPanel {
	
	// int w = getWidth();
	// int h = getHeight();
	
	private int percent;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0,0,1800,24);
		g.setColor(Color.RED);
		g.fillRect(0, 0, 18*percent, 24);
		g.fillOval(18*percent-18, 0, 24, 24);
	}
	
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	public int getPercent() {
		return this.percent;
	}
	
	
}
