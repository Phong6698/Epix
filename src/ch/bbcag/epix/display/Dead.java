package ch.bbcag.epix.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Dead {
	
	private BufferedImage deadImage;
	private Font font;
	
	public Dead() {
		try {
			deadImage = ImageIO.read(getClass().getResourceAsStream("/Displays/Dead.png"));
			font = new Font("Arial", Font.PLAIN, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {

		g.drawImage(deadImage, 0, 0, null);

		g.setColor(new Color(1f, 0f, 0f, .3f));
		Rectangle backgroundBar = new Rectangle(0, 0, 448, 288);
		g.fill(backgroundBar);


	}

}
