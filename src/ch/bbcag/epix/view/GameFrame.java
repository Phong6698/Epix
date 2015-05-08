package ch.bbcag.epix.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import ch.bbcag.epix.display.Dead;
import ch.bbcag.epix.display.Finished;
import ch.bbcag.epix.display.Pause;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.gamestate.GameStateManager;

/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			GameFrame.java.java Copyright Berufsbildungscenter 2015
 */

public class GameFrame extends JFrame implements Runnable, KeyListener, MouseListener{	
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
	private boolean paused;
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
	
	//displays
	private Pause pauseDisplay;
	private Dead deadDisplay;
	private Finished finishedDisplay;
	
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
		
		addMouseListener(this);
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
		
		addMouseListener(this);
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
			
			if(!paused){				
				update();			
			}
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			setPauseDisplay(new Pause(this));
			setDeadDisplay(new Dead());
			setFinishedDisplay(new Finished());
			drawToScreen();
			draw();
			
			
		}
		
	}
	
	private void update() {
		gsm.update();
		
		
		
		
	}
	private void draw() {
		gsm.draw(g);
		
		if(paused) {
			getPauseDisplay().draw(g);
		}
		if(gsm.isFinished()){
			getFinishedDisplay().draw(g);
			
//			//if finished close frame
//			if(gsm.isFinished()) {	
//				running = false;
//				this.dispose();
//				EpixView epix = new EpixView(getUser());
//				CardLayout cardLayout = (CardLayout) epix.cards.getLayout();
//				cardLayout.show(epix.cards, "levelAuswahlCard");					
//			}
		}
		if(gsm.isDead()) {
			getDeadDisplay().draw(g);
		}
		
		

	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, (int)(WIDTH * SCALE), (int)(HEIGHT * SCALE), null);
		g2.dispose();
	}
	
	public void mouseClicked(MouseEvent e) {
//		if (getPauseDisplay().getResumeRect().contains(e.getPoint())) {
//		 	System.out.println("clicked");
//		}
//		System.out.println(getPauseDisplay().getResumeRect());
//		System.out.println(e.getPoint());
    }
	
	 public void mousePressed(MouseEvent e) {
	 }
	
	public void keyTyped(KeyEvent key) {}
	
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
		
		int k = key.getKeyCode();
		
		if(k == KeyEvent.VK_ESCAPE && paused ) {
			System.out.println("Continue");
			paused = false;
			
		} else if(k == KeyEvent.VK_ESCAPE ) {
			System.out.println("Pause");
			paused = true;
		
		
			
		}
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

	public Pause getPauseDisplay() {
		return pauseDisplay;
	}

	public Dead getDeadDisplay() {
		return deadDisplay;
	}

	public Finished getFinishedDisplay() {
		return finishedDisplay;
	}

	public void setPauseDisplay(Pause pauseDisplay) {
		this.pauseDisplay = pauseDisplay;
	}

	public void setDeadDisplay(Dead deadDisplay) {
		this.deadDisplay = deadDisplay;
	}

	public void setFinishedDisplay(Finished finishedDisplay) {
		this.finishedDisplay = finishedDisplay;
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