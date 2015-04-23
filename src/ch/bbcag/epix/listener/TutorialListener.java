package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ch.bbcag.epix.view.Game;

public class TutorialListener implements ActionListener {
	
	private JFrame frame;
	
	public TutorialListener(JFrame frame) {
		this.frame = frame;
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
