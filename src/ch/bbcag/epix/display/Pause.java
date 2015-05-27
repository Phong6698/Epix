package ch.bbcag.epix.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


/**
 * Display fuer pausierung des Spiels
 *
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class Pause {
	
	private BufferedImage pauseImage;	
	
	private Rectangle2D resumeRect;
	private Rectangle2D restartRect;
	private Rectangle2D quitRect;
	
	
	/**
	 * Konstruktor
	 */
	public Pause() {
		try {
			pauseImage = ImageIO.read(getClass().getResourceAsStream("/Displays/Pause.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Draw
	 * @param g {@link Graphics2D}
	 */
	public void draw(Graphics2D g) {

		g.setColor(new Color(141, 150, 149, 50));
		Rectangle background = new Rectangle(0, 0, 448, 288);
		g.fill(background);
		
		g.drawImage(pauseImage, 0, 0, null);
		
		g.setFont(new Font("Arial", Font.BOLD, 14));
		int intValue = Integer.parseInt( "ff5030",16); 		
		g.setColor(new Color(intValue));
		
		g.drawString("Resume", 200, 156);
		setResumeRect(g.getFontMetrics().getStringBounds("Resume", g));
		getResumeRect().setRect(200,156 - g.getFontMetrics().getAscent(), getResumeRect().getWidth(), getResumeRect().getHeight());

		g.drawString("Restart", 200, 172);		
		setRestartRect(g.getFontMetrics().getStringBounds("Restart", g));
		getRestartRect().setRect(200,172 - g.getFontMetrics().getAscent(), getRestartRect().getWidth(), getRestartRect().getHeight());

		g.drawString("Quit", 200, 188);
		setQuitRect(g.getFontMetrics().getStringBounds("Quit", g));
		getQuitRect().setRect(200,188 - g.getFontMetrics().getAscent(), getQuitRect().getWidth(), getQuitRect().getHeight());

	}

	
	/*
	 * Getter und Setter
	 */
	public Rectangle2D getResumeRect() {
		return resumeRect;
	}

	public void setResumeRect(Rectangle2D resumeRect) {
		this.resumeRect = resumeRect;
	}

	public Rectangle2D getRestartRect() {
		return restartRect;
	}

	public void setRestartRect(Rectangle2D restartRect) {
		this.restartRect = restartRect;
	}

	public Rectangle2D getQuitRect() {
		return quitRect;
	}

	public void setQuitRect(Rectangle2D quitRect) {
		this.quitRect = quitRect;
	}
}
	
