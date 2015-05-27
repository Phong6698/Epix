package ch.bbcag.epix.entity;

import java.awt.image.BufferedImage;

/**
 * Animation
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class Animation {
	
	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	
	/**
	 * Konstruktor
	 */
	public Animation() {
		playedOnce = false;
	}
	
	
	/**
	 * Frame setten
	 * @param frames {@link BufferedImage}
	 */
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}
	
	

	/**
	 * Update
	 */
	public void update() {
		
		if(delay == -1) return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length) {
			currentFrame = 0;
			playedOnce = true;
		}		
	}
	
	
	/*
	 * Getter und Setter
	 */
	public int getFrame() { 
		return currentFrame; 
	}
	
	public BufferedImage getImage() { 
		return frames[currentFrame]; 
	}
	
	public boolean hasPlayedOnce() { 
		return playedOnce; 
	}
	
	public void setDelay(long d) { 
		delay = d; 
	}
	
	public void setFrame(int i) { 
		currentFrame = i; 
	}
	
}
