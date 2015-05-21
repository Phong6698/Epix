	package ch.bbcag.epix.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

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

		float[] scales = { 1f, 1f, 1f, 0.6f };
		float[] offsets = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);
		g.drawImage(hud, rop, 140, 15); 
		
		// Player Information
		g.drawImage(coin, 229, 36, null);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(getPlayer().getHealth() + "/" + getPlayer().getMaxHealth(), 180, 31);
		g.drawString(getPlayer().getUsername(), 149, 47);
		g.drawString("" + getPlayer().getCoin(), 246, 47);

		// Player Coordinates
		g.drawString("x: " + getPlayer().getx(), 20, 43);
		g.drawString("y: " + getPlayer().gety(), 20, 57);

		// Player health bar
		g.setColor(new Color(1f, 0f, 0f, .3f));
		Rectangle backgroundBar = new Rectangle(149, 31, 120, 5);
		g.fill(backgroundBar);

		g.setColor(Color.RED);
		Rectangle healthBar = new Rectangle(149, 31, (int) (((float) getPlayer().getHealth() / (float) getPlayer().getMaxHealth()) * 120), 5);
		g.fill(healthBar);

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
