package ch.bbcag.epix.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.bbcag.epix.view.Game;
import ch.bbcag.epix.view.GameFrame;

public class LevelAuswahlCardButtonListener implements ActionListener {

	private JPanel card;
	
	public LevelAuswahlCardButtonListener(JPanel card) {
		this.setCard(card);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Level 1")) {
			new GameFrame(1);
			}
		else if (e.getActionCommand().equals("Level 2")) {
			new GameFrame(2);
		}
		else if (e.getActionCommand().equals("Boss Level")) {
			new GameFrame(3);
		}
		else if (e.getActionCommand().equals("Zurück")) {
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "upgradeCard");
		}
		
	}
	
	public JPanel getCard() {
		return card;
	}

	public void setCard(JPanel card) {
		this.card = card;
	}

}
