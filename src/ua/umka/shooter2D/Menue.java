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
	private int transp = 0;
	
	//Constructor
	public Menue(){
		buttonWidth = 270;
		buttonHeight = 60;
		
		color1 = Color.white;
		s = "Розпочнімо гру!";
	}
	
	//Functions
	
	public void update(){
		if(GamePanel.mouseX > GamePanel.WIDTH/2 - buttonWidth/2 && 
				GamePanel.mouseX < GamePanel.WIDTH/2 + buttonWidth/2 && 
				GamePanel.mouseY > GamePanel.HEIGHT/2 - buttonHeight/2 &&
				GamePanel.mouseY < GamePanel.HEIGHT/2 + buttonHeight/2){
			transp = 60;
			if(GamePanel.leftMouse){
				GamePanel.state = GamePanel.STATES.PLAY;
			}
		}else{
			transp = 0;
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(color1);
		g.setStroke(new BasicStroke(3));
		g.drawRect(GamePanel.WIDTH/2-buttonWidth/2, 
				GamePanel.HEIGHT/2-buttonHeight/2, buttonWidth, buttonHeight);
		g.setColor(new Color (255, 255, 255, transp));
		g.fillRect(GamePanel.WIDTH/2-buttonWidth/2, 
				GamePanel.HEIGHT/2-buttonHeight/2, buttonWidth, buttonHeight);
		g.setStroke(new BasicStroke(1));
		g.setColor(color1);
		g.setFont(new Font("Isocpeur", Font.BOLD, 33));
		long length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.drawString(s, (int)(GamePanel.WIDTH/2-length/2), (int)(GamePanel.HEIGHT/2 + buttonHeight/4));
		}

}
