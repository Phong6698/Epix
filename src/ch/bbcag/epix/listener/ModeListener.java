package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import ch.bbcag.epix.view.EpixView;

public class ModeListener implements ActionListener {
	

	
	public ModeListener() {
	}

	public void actionPerformed(ActionEvent arg0) {
	
		if(((JComboBox) arg0.getSource()).getSelectedItem().toString().equals("Singleplayer")){
			EpixView.setMultiplayer(false);
			System.out.println("single");
		} else if(((JComboBox) arg0.getSource()).getSelectedItem().toString().equals("Multiplayer")){
			EpixView.setMultiplayer(true);
			System.out.println("multi");
		}
		
	}
}
