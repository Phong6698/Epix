package ch.bbcag.epix.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.tilemap.TileMap;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Rainbow.java.java Copyright Berufsbildungscenter 2015
 */

public class Rainbow extends MapObject {
	
	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public Rainbow(TileMap tm, boolean right) {
		
		super(tm);
		
		facingRight = right;
		
		setMoveSpeed(0.8);
		if(right) dx = getMoveSpeed();
		else dx = -getMoveSpeed();
		
		width = 32;
		height = 32;
		cwidth = 16;
		cheight = 16;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Player/Player_shot.png"
				)
			);
			
			sprites = new BufferedImage[2];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
			hitSprites = new BufferedImage[2];
			for(int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(
					i * width,
					height,
					width,
					height
				);
			}
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(10);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setHit() {
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		dx = 0;
	}
	
	public boolean shouldRemove() { return remove; }
	
	public void update() {
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dx == 0 && !hit) {
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOnce()) {
			remove = true;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		draw_(g);
		
	}
	
	public void draw_(java.awt.Graphics2D g) {
		
		if(facingRight) {
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y - (height/2 - cheight/2 )/2 + ymap - height / 2), null);
		}
		else {
			g.drawImage(animation.getImage(), (int)(x + xmap -width / 2 + width), (int)(y- (height/2 - cheight/2 )/2 + ymap - height / 2), -width, height, null);
		}
		g.drawRect((int) (x + xmap -cwidth / 2),  (int) (y + ymap - cheight / 2),getCWidth(), getCHeight());

	}
	
}


















