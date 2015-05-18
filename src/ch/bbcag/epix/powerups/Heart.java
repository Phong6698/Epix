package ch.bbcag.epix.powerups;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Powerup;
import ch.bbcag.epix.tilemap.TileMap;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class Heart extends Powerup {
	

	private BufferedImage[] sprites;

	/*
	 * Herz Upgrade wird herstellt
	 * - Verbesserungen werden festgelegt
	 * - Animation wird festgelegt 
	 */
	
	public Heart(TileMap tm) {
		super(tm);
	
		plusHealth = 40;
		
		expireTime = 0;
		
		width = 32;
		height = 32;
		cwidth = 32;
		cheight = 32;
		
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Powerups/PowerUps_Heart.png"));

			sprites = new BufferedImage[4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(100);

		right = true;
		facingRight = true;
	}
	
	
	/*
	 * Animation wird aktualisiert
	 */
	public void update() {

		checkTileMapCollision();
		animation.update();

	}
	
	public void draw(Graphics2D g) {

		setMapPosition();

		super.draw(g);

	}
	

}
