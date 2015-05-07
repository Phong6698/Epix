package ch.bbcag.epix.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.gamestate.GameStateManager;

/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			GameFrame.java.java Copyright Berufsbildungscenter 2015
 */

public class GameFrame extends JFrame implements Runnable, KeyListener{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// dimensions
	public static final int WIDTH = 448;
	public static final int HEIGHT = 288;
	public static final double SCALE = 2.5;	
	/* Tiles
	 * 9 Height, 14 Width
	 */
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	//level
	private int level;
	
	// game state manager
	private GameStateManager gsm;
	
	//epix frame
	private JFrame epix;
	
	public GameFrame(int level, User user, JFrame epix) {
		setUser(user);
		setEpix(epix);
		
		this.level = level;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setPreferredSize(new Dimension((int)(WIDTH * SCALE), (int)(HEIGHT * SCALE)));
		this.setBounds(100, 100, (int)(WIDTH * SCALE), (int)(HEIGHT * SCALE));
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.requestFocus();
		this.setVisible(true);
	}
	
	public GameFrame(int level, User user) {
		setUser(user);
		
		this.level = level;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setPreferredSize(new Dimension((int)(WIDTH * SCALE), (int)(HEIGHT * SCALE)));
		this.setBounds(100, 100, (int)(WIDTH * SCALE), (int)(HEIGHT * SCALE));
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.requestFocus();
		this.setVisible(true);
	}

	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager(level, getUser());
		
	}
	
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			update();
			
		}
		
	}
	
	private void update() {
		gsm.update();
		
		//if finished close frame
		if(gsm.isFinished()) {	
			running = false;
			this.dispose();
			EpixView epix = new EpixView(getUser());
			CardLayout cardLayout = (CardLayout) epix.cards.getLayout();
			cardLayout.show(epix.cards, "levelAuswahlCard");			
			
			
			
		}
	}
	private void draw() {
		gsm.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, (int)(WIDTH * SCALE), (int)(HEIGHT * SCALE), null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}

	public JFrame getEpix() {
		return epix;
	}

	public void setEpix(JFrame epix) {
		this.epix = epix;
	}

}