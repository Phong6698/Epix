package ch.bbcag.epix.tilemap;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.view.EpixView;

/**
 * Klasse fuer Hintergrund Bild
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	
	/*
	 * Mit dieser Methode kann man den Hintergrund aufrufen
	 */
	
	
	/**
	 * Konstruktor
	 * @param s {@link String} Pfad wo das Bild gespeichert ist
	 * @param ms
	 */
	public Background(String s, double ms) {
		
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			moveScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Die Positionen der Objekte werden festgelegt
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % EpixView.WIDTH;
		this.y = (y * moveScale) % EpixView.HEIGHT;
	}
	
	
	/**
	 * Die Kamera wird festgelegt
	 * @param dx
	 * @param dy
	 */
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	

	/**
	 * Update
	 */
	public void update() {
		x += dx;
		y += dy;
	}
	
	
	/**
	 * Draw
	 * @param g {@link Graphics2D}
	 */
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			g.drawImage(
				image,
				(int)x + EpixView.WIDTH,
				(int)y,
				null
			);
		}
		if(x > 0) {
			g.drawImage(
				image,
				(int)x - EpixView.WIDTH,
				(int)y,
				null
			);
		}
	}
}