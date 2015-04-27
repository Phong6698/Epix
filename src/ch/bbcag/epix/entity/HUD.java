package ch.bbcag.epix.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HUD {

private Player player;
	
	private BufferedImage image;
	private Font font;
	
	public HUD(Player p) {
		
		setPlayer(p);

		font = new Font("Arial", Font.PLAIN, 10);
		
	}
	
	public void draw(Graphics2D g) {
		
//		g.drawImage(image, 0, 10, null);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(getPlayer().getHealth() + "/" + getPlayer().getMaxHealth(), 15, 20);
		

		 //Player 1 health bar
		 g.setColor(new Color(1f,0f,0f,.3f ));
	     Rectangle backgroundBar = new Rectangle(10, 10, 60, 3);
	     g.fill(backgroundBar);
		
	     g.setColor(Color.RED);
	     Rectangle healthBar = new Rectangle(10, 10, (int)(((float)getPlayer().getHealth() / (float)getPlayer().getMaxHealth()) * 60), 3);
	     g.fill(healthBar);

       
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
