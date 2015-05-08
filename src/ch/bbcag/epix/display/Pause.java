package ch.bbcag.epix.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import ch.bbcag.epix.view.GameFrame;

public class Pause implements MouseListener{
	
	private BufferedImage pauseImage;	
	private Rectangle2D resumeRect;
	
	public Pause(GameFrame GameFrame) {
		try {
			pauseImage = ImageIO.read(getClass().getResourceAsStream("/Displays/Pause.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GameFrame.addMouseListener(this);
		

	}
	
	
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
//		System.out.println(getResumeRect());

		g.drawString("Restart", 200, 172);
		g.drawString("Quit", 200, 188);
	}

	public void mouseClicked(MouseEvent e) {
//		if (getResumeRect().contains(e.getPoint())) {
//		 	System.out.println("clicked");
//		}
		System.out.println(getResumeRect());
		System.out.println(e.getPoint());
    }

	public Rectangle2D getResumeRect() {
		return resumeRect;
	}


	public void setResumeRect(Rectangle2D resumeRect) {
		this.resumeRect = resumeRect;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}






}
	
