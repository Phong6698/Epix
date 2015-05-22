package ch.bbcag.epix.audio;

import javax.sound.sampled.*;

/**
 * AudioPlayer Klasse zum Sounds Abspielen 
 *
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class AudioPlayer {
	
	private Clip clip;
	
	public AudioPlayer(String s) {
		
		try {
			
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream(
						s
					)
				);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			AudioInputStream dais =
				AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Play Audio
	 * @param volumeAmount  Lautstärke 
	 */
	public void play(float volumeAmount) {
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(volumeAmount);
		if(clip == null) return;
		stop();
		clip.setFramePosition(2);
		clip.start();
	}
	
	
	/**
	 * Play Audio in Loop
	 * @param volumeAmount  Lautstärke 
	 */
	public void playLoop(float volumeAmount) {
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(volumeAmount);
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.loop(1000);
	}
	
	
	/**
	 * Stop Audio
	 */
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	
	/**
	 * Close Audio
	 */
	public void close() {
		stop();
		clip.close();
	}
	
}














