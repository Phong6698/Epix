package ch.bbcag.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.MapObject;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.TileMap;

public class MagicianShot extends MapObject {

	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	

	protected boolean flinching;
	protected long flinchTimer;

	public MagicianShot(TileMap tm, boolean shotright, Player player) {
		super(tm);

		facingRight = right;

		moveSpeed = 4.8;
		if (shotright)
			dx = -moveSpeed;
		else dx = moveSpeed;

		width = 32;
		height = 32;
		cwidth = 1;
		cheight = 1;
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/Wizard_Shot.png"));

			sprites = new BufferedImage[2];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}

			hitSprites = new BufferedImage[2];
			for (int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(i * width, height, width, height);
			}

			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(100);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setHit() {
		if (hit)
			return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(100);
		cwidth = 0;
		cheight = 0;
		
		dx = 0;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update(Magician m, Player player) {

		// update position

		checkTileMapCollision();
		if (m.gety() < player.gety()) {
			setPosition(xtemp, ytemp + player.gety() / 100);
		} else if (m.gety() > player.gety()) {
			setPosition(xtemp, ytemp - player.gety() / 100);
		} else if (m.gety() == player.gety()) {
			setPosition(xtemp, ytemp);
		}
		
		if (dx == 0  && !hit) {
			setHit();
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

	public void draw(Graphics2D g) {
		
		setMapPosition();

		super.draw(g);

	}
}