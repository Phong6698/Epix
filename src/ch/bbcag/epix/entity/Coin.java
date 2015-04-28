package ch.bbcag.epix.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.tilemap.TileMap;

public class Coin extends MapObject {
	
	private BufferedImage[] sprites;
	private int coinValue;
	private boolean taken;

	public Coin(TileMap tm) {
		super(tm);
		
		width = 32;
		height = 32;
		cwidth = 30;
		cheight = 30;
		
		setCoinValue(1);
		taken = false;
		
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Items/Coin.png"));

			sprites = new BufferedImage[8];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(60);

		right = true;
		facingRight = true;
	}
	
	private void getNextPosition() {
		// falling
		if(falling) {
			dy += getFallSpeed();
		}
		
	}
	
	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();

		// update animation
		animation.update();

	}
	
	public void draw(Graphics2D g) {

		// if(notOnScreen()) return;

		setMapPosition();

		super.draw(g);

	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}


	public int getCoinValue() {
		return coinValue;
	}

	public void setCoinValue(int coinValue) {
		this.coinValue = coinValue;
	}

}
