package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bbcag.epix.view.EpixView;

public class MultiplayerListener implements ActionListener {
	

	public void actionPerformed(ActionEvent arg0) {
		EpixView.setMultiplayer(true);
	}
}
