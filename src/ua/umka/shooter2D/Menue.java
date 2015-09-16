package ua.umka.shooter2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import ua.umka.shooter2D.GamePanel;

public class Menue {
	
	//Fields
	private int buttonWidth;
	private int buttonHeight;
	private Color color1;
	private String s;
	
	//Constructor
	public Menue(){
		buttonWidth = 270;
		buttonHeight = 60;
		
		color1 = Color.white;
		s = "Розпочнімо гру!";
	}
	
	//Functions
	
	public void draw(Graphics2D g){
		g.setColor(color1);
		g.setStroke(new BasicStroke(3));
		g.drawRect(GamePanel.WIDTH/2-buttonWidth/2, GamePanel.HEIGHT/2-buttonHeight/2, buttonWidth, buttonHeight);
		g.setStroke(new BasicStroke(1));
		g.setColor(color1);
		g.setFont(new Font("Isocpeur", Font.BOLD, 33));
		long length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.drawString(s, (int)(GamePanel.WIDTH/2-length/2), (int)(GamePanel.HEIGHT/2 + buttonHeight/4));
		}

}
