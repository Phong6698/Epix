package ch.bbcag.epix.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Player;

/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *         HUD.java.java Copyright Berufsbildungscenter 2015
 */

public class HUD {

	private Player player;

	private BufferedImage coin;
	private BufferedImage hud;
	
	private Font font;

	public HUD(Player p) {

		setPlayer(p);

		try {
			coin = ImageIO.read(getClass().getResourceAsStream("/HUD/Coin spin 16x16.gif"));
			hud = ImageIO.read(getClass().getResourceAsStream("/HUD/HUD.png"));

			font = new Font("Arial", Font.PLAIN, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g) {
		
		
		g.drawImage(hud, 11, 15, null);
		
		// Player Information
		g.drawImage(coin, 100, 36, null);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(getPlayer().getHealth() + "/" + getPlayer().getMaxHealth(), 51, 31);
		g.drawString(getPlayer().getUsername(), 20, 47);
		g.drawString("" + getPlayer().getCoin(), 117, 47);

		// Player Coordinates
		g.drawString("x: " + getPlayer().getx(), 400, 43);
		g.drawString("y: " + getPlayer().gety(), 400, 57);

		// Player health bar
		g.setColor(new Color(1f, 0f, 0f, .3f));
		Rectangle backgroundBar = new Rectangle(20, 31, 120, 5);
		g.fill(backgroundBar);

		g.setColor(Color.RED);
		Rectangle healthBar = new Rectangle(20, 31, (int) (((float) getPlayer().getHealth() / (float) getPlayer().getMaxHealth()) * 120), 5);
		g.fill(healthBar);

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
