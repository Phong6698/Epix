package ch.bbcag.epix.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.view.GameFrame;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			LevelAuswahlCardButtonListener.java.java Copyright Berufsbildungscenter 2015
 */

public class LevelAuswahlCardButtonListener implements ActionListener {

	private JPanel card;
	private User user;
	private JFrame epix;
	
	public LevelAuswahlCardButtonListener(JPanel card, User user, JFrame epix) {
		this.setCard(card);
		this.setUser(user);
		this.setEpix(epix);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Level 1")) {
			getEpix().dispose();
			new GameFrame(1, getUser(), getEpix());
			}
		else if (e.getActionCommand().equals("Level 2")) {
			getEpix().dispose();
			new GameFrame(2, getUser(), getEpix());
		}
		else if (e.getActionCommand().equals("Boss Level")) {
			getEpix().dispose();
			new GameFrame(3, getUser(), getEpix());
		}
		else if (e.getActionCommand().equals("Zur\u00FCck")) {
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public JFrame getEpix() {
		return epix;
	}


	public void setEpix(JFrame epix) {
		this.epix = epix;
	}

}
