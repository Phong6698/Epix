package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import ch.bbcag.epix.view.EpixView;


/**
 * Listener fuer Modus Auswahl
 *
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class ModeListener implements ActionListener {


	public void actionPerformed(ActionEvent arg0) {
	
		if(((JComboBox<?>) arg0.getSource()).getSelectedItem().toString().equals("Singleplayer")){
			EpixView.setMultiplayer(false);
			System.out.println("single");
		} else if(((JComboBox<?>) arg0.getSource()).getSelectedItem().toString().equals("Multiplayer")){
			EpixView.setMultiplayer(true);
			System.out.println("multi");
		}		
	}
}
