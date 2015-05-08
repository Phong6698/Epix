package ch.bbcag.epix.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Finished {

	private BufferedImage finishedImage;
	private Font font;
	
	public Finished() {
		try {
			finishedImage = ImageIO.read(getClass().getResourceAsStream("/Displays/Finished.png"));
			font = new Font("Arial", Font.PLAIN, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {

		g.drawImage(finishedImage, 0, 0, null);

		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("test", 50, 50);

		g.setColor(new Color(141, 150, 149, 50));
		Rectangle background = new Rectangle(0, 0, 448, 288);
		g.fill(background);
		
//		g.setColor(new Color(141, 150, 149, 50));
//		Rectangle background = new Rectangle(0, 0, 448, 288);
//		g.fill(background);


	}

}
