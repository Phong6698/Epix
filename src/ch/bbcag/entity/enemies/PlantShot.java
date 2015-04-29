package ch.bbcag.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.MapObject;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.TileMap;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			PlantShot.java.java Copyright Berufsbildungscenter 2015
 */
public class PlantShot extends MapObject {

	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;

	/**
	 * Konstruktor
	 * 
	 * @param tm
	 * @param shotright
	 * @param player
	 */
	public PlantShot(TileMap tm, boolean shotright, Player player) {
		super(tm);

		facingRight = right;

		moveSpeed = 4.8;
		
		if (shotright){
			dx = -moveSpeed;
		} else { 
			dx = moveSpeed;
		}

		width = 32;
		height = 32;
		cwidth = 1;
		cheight = 1;
		
		/*
		 * Bilder laden
		 */
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/PlantShooting_Shot.png"));

			sprites = new BufferedImage[2];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}

			setHitSprites(new BufferedImage[2]);
			for (int i = 0; i < getHitSprites().length; i++) {
				getHitSprites()[i] = spritesheet.getSubimage(i * width, height, width, height);
			}

			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(100);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Animation wechseln wenn der Schuss abprallt
	 */
	public void setHit() {
		if (isHit())
			return;
		setHit(true);
		animation.setFrames(getHitSprites());
		animation.setDelay(100);
		cwidth = 0;
		cheight = 0;
		dx = 0;
	}

	
	/**
	 * Schuss updaten
	 * 
	 * @param player
	 * @param e
	 */
	public void update(Player player, ShootingPlant e) {

		// update position

		checkTileMapCollision();
		if (e.gety() < player.gety()) {
			setPosition(xtemp, ytemp + player.gety() / 100);
		} else if (e.gety() > player.gety()) {
			setPosition(xtemp, ytemp - player.gety() / 100);
		} else if (e.gety() == player.gety()) {
			setPosition(xtemp, ytemp);
		}
		
		if (dx == 0  && !isHit()) {
			setHit();
		}

		// update animation
		animation.update();
		if (isHit() && animation.hasPlayedOnce()) {
			setRemove(true);
		}
		
		// update animation
		animation.update();
		if (hit && animation.hasPlayedOnce()) {
			remove = true;
		}
		
		//löschen falls es die y koordinate <= 0
		if (this.gety() <= 0) {
			remove = true;
		}
	}

	
	/**
	 * Schuss zeichnen
	 */
	public void draw(Graphics2D g) {

		setMapPosition();
		super.draw(g);
	}

	
	/*
	 * Getter und Setter
	 */
	public boolean isHit() {
		return hit;
	}

	public boolean isRemove() {
		return remove;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public BufferedImage[] getHitSprites() {
		return hitSprites;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public void setHitSprites(BufferedImage[] hitSprites) {
		this.hitSprites = hitSprites;
	}

}
