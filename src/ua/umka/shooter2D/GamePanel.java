package ua.umka.shooter2D;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.*;

public class GamePanel extends JPanel implements Runnable{
	
	//Field
	public static int WIDTH = 400;
	public static int HEIGHT = 600;
	
	private Thread thread;
	
	private BufferedImage image;
	private Graphics2D g;
	
	public static GameBack background;
	public static Player player;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Enemy> enemies;
	
	//Constructor
	public GamePanel(){
		super();
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		addKeyListener(new Listeners());
	}
	//Funtions
	
	public void start(){
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		background = new GameBack();
		player = new Player();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		//TODO remove there enemies
		enemies.add(new Enemy (1, 1));
		enemies.add(new Enemy (1, 1));
		
		while(true){ // TODO Status
			
			gameUpdate();
			gameRender();
			gameDraw();
			
			try {
				thread.sleep(33);// TODO FPS
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void gameUpdate(){
		//Background update
		background.update();
		
		//Player update
		player.apdate();
		
		//Bullets update
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).update();
			boolean remove = bullets.get(i).remove();
			if(remove){
				bullets.remove(i);
				i--;
			}
		}
		
		//Enemies update
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).update();
		}
		
		//Bullets-enemies collide
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();
			
			for(int j = 0; j < bullets.size(); j++){
				Bullet b = bullets.get(j);
				double bx = b.getX();
				double by = b.getY();
				double dx = ex - bx;
				double dy = ey - by;
				double dist = Math.sqrt(dx * dx + dy * dy);
				if((int)dist < e.getR() + b.getR()){
					e.hit();
					bullets.remove(j);
					break;
				}
			}
			boolean remove = e.remove();
			if(remove){
				enemies.remove(i);
				i--;
			}
		}

	}
	public void gameRender(){
		//Background draw
		background.draw(g);
		
		//Player draw
		player.drav(g);
		
		//Bullets draw
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).draw(g);
		}
		
		//Emenies draw
		for (int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
	}
	
	private void gameDraw(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

}