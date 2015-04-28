package ch.bbcag.epix.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HUD {

private Player player;
	
	private BufferedImage coin;
	private Font font;
	
	public HUD(Player p) {
		
		setPlayer(p);
		
		try {
			coin = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/Coin spin.gif"
				)
			);
			font = new Font("Arial", Font.PLAIN, 10);
		}
		catch(Exception e) {
			e.printStackTrace();
		}


		
		
		
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(coin, 0, 10, null);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(getPlayer().getHealth() + "/" + getPlayer().getMaxHealth(), 51, 20);
		

		 //Player 1 health bar
		 g.setColor(new Color(1f,0f,0f,.3f ));
	     Rectangle backgroundBar = new Rectangle(20, 20, 120, 5);
	     g.fill(backgroundBar);
		
	     g.setColor(Color.RED);
	     Rectangle healthBar = new Rectangle(20, 20, (int)(((float)getPlayer().getHealth() / (float)getPlayer().getMaxHealth()) * 120), 5);
	     g.fill(healthBar);

       
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}