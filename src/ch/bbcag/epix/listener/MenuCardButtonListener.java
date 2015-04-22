package ch.bbcag.epix.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ch.bbcag.epix.Main.Game;
import ch.bbcag.epix.entity.User;

public class MenuCardButtonListener implements ActionListener {

	private JPanel card;
	
	public MenuCardButtonListener(JPanel card) {
		this.setCard(card);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Spielen")) {
		
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "upgradeCard");
		}
		else if (e.getActionCommand().equals("Rangliste")) {
			
		}
		
	}

	public JPanel getCard() {
		return card;
	}

	public void setCard(JPanel card) {
		this.card = card;
	}

}
