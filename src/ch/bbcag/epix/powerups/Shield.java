package ch.bbcag.epix.powerups;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Powerup;
import ch.bbcag.epix.tilemap.TileMap;

/**
 * Powerup Shield, fuer eine gewisse Zeit bekkomt der Spieler keinen Schaden
 *
 * @author  Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class Shield extends Powerup {

	private BufferedImage[] sprites;

	/*
	 * Schield wird herstellt
	 * Animation wird gesetzt
	 */
	
	
	/**
	 * Konstruktor
	 * @param tm {@link TileMap}
	 */
	public Shield(TileMap tm) {
		super(tm);
	
		shield = true;
		
		expireTime = 10000;
		
		width = 32;
		height = 32;
		cwidth = 32;
		cheight = 32;
		
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Powerups/PowerUps_Shield.png"));

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
	
	
	public void update() {

		checkTileMapCollision();

		// update animation
		animation.update();
	}
	
	public void draw(Graphics2D g) {

		setMapPosition();

		super.draw(g);
	}
}
