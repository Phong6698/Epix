package ch.bbcag.epix.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * Button Listener fuer Menu Card
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class MenuCardButtonListener implements ActionListener {

	private JPanel card;
	
	
	/**
	 * Konstruktor
	 * @param card {@link JPanel}
	 */
	public MenuCardButtonListener(JPanel card) {
		this.setCard(card);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Spielen")) {	
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "upgradeCard");
		} else if (e.getActionCommand().equals("Rangliste")) {
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "ranglisteCard");
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
