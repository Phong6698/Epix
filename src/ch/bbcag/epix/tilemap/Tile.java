package ch.bbcag.epix.tilemap;

import java.awt.image.BufferedImage;

/**
 * Tile
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class Tile {
	
	private BufferedImage image;
	private int type;
	
	// tile types
	public static final int NORMAL = 1;
	public static final int BLOCKED = 0;
	
	
	/*
	 * Die Tile wird gesetzt 
	 */
	
	
	/**
	 * Konstruktor
	 * @param image {@link BufferedImage} Tile Bild
	 * @param type
	 */
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	
	/*
	 * Getter und Setter
	 */
	public BufferedImage getImage() { 
		return image; 
	}
	
	public int getType() { 
		return type; 
	}
	
}
