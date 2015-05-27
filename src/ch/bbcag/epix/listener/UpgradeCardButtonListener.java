package ch.bbcag.epix.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * Button Listener fuer Upgrade Card
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class UpgradeCardButtonListener implements ActionListener {

	private JPanel card;
	
	
	/**
	 * Konstruktor
	 * @param card {@link JPanel}
	 */
	public UpgradeCardButtonListener(JPanel card) {
		this.setCard(card);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Level ausw\u00E4hlen")) {
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "levelAuswahlCard");
		}
		else if (e.getActionCommand().equals("Zur\u00FCck")) {
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "menuCard");
		}
		
	}

	
	/*
	 * Getter und Setter
	 */
	public JPanel getCard() {
		return card;
	}

	public void setCard(JPanel card) {
		this.card = card;
	}

}