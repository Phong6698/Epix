package ch.bbcag.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Enemy;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.TileMap;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Plant.java.java Copyright Berufsbildungscenter 2015
 */
public class Plant extends Enemy {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 4, 5 };
	private static final int IDLE = 0;
	private static final int ATTACK = 1;


	/**
	 * Konstruktor
	 * @param tm {@link TileMap}
	 */
	public Plant(TileMap tm) {
		super(tm);
		setFallSpeed(0.1);
		maxFallSpeed = 1.1;

		width = 32;
		height = 32;
		cwidth = 32;
		cheight = 32;

		health = maxHealth = 10;
		damage = 1;

		
		/*
		 * Bilder laden
		 */
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/PlantStanding.png"));
			this.setSprites(new ArrayList<BufferedImage[]>());
			for (int i = 0; i < 2; i++) {

				BufferedImage[] bi = new BufferedImage[getNumFrames()[i]];

				for (int j = 0; j < getNumFrames()[i]; j++) {

					if (i != getAttack()) {
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					} else {
						bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
					}

				}

				getSprites().add(bi);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		currentAction = getIdle();
		animation.setFrames(getSprites().get(currentAction));
		animation.setDelay(100);
		width = 32;
	}

	
	/**
	 * nächste Position
	 */
	private void getNextPosition() {
		
		// falling
		if (falling) {
			dy += getFallSpeed();
		}
	}

	
	/**
	 * Die Pflanze updaten
	 * 
	 * @param e
	 * @param player
	 */
	public void update(Plant e, Player player) {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		} else if (OnScreen(e, player)) {
			if (currentAction != getAttack()) {
				currentAction = getAttack();
				animation.setFrames(getSprites().get(getAttack()));
				animation.setDelay(150);
				width = 64;
			}
		} else {
			if (currentAction != getIdle()) {
				currentAction = getIdle();
				animation.setFrames(getSprites().get(getIdle()));
				animation.setDelay(400);
				width = 32;
			}
		}
		

		//update animation
		animation.update();
		
		if (true) {
			if (e.getx() > player.getx()) {
				facingRight = true;
			} else {
				facingRight = false;
			}
		}
		
	}
	

	/**
	 * Schaut ob der Spieler die Pflanze auf dem Bildschirm sieht
	 * 
	 * @param e
	 * @param player
	 * @return ob der Spieler die Plfanze auf dem Bildschirm sieht
	 */
	private boolean OnScreen(Plant e, Player player) {
		
		if (e.getx() - 48 < player.getx() && e.getx() + 32  - player.getx() > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * zeichnet die Pflanze
	 */
	public void draw(Graphics2D g) {
		
		// if(notOnScreen()) return;
		setMapPosition();
		super.draw(g);
	}

	
	/*
	 * Getter und Setter
	 */
	public ArrayList<BufferedImage[]> getSprites() {
		return sprites;
	}

	public int[] getNumFrames() {
		return numFrames;
	}

	public static final int getIdle() {
		return IDLE;
	}

	public static final int getAttack() {
		return ATTACK;
	}

	public void setSprites(ArrayList<BufferedImage[]> sprites) {
		this.sprites = sprites;
	}
}