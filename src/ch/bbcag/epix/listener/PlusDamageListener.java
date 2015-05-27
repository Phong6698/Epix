package ch.bbcag.epix.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import ch.bbcag.epix.controller.EpixController;
import ch.bbcag.epix.entity.User;


/**
 * Plus Damage Listener
 *
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class PlusDamageListener implements ActionListener {
	private User user;
	private JProgressBar maxDamageBar;
	private JFrame epix;
	private JLabel menuCardCoinLabel;
	private JPanel card;

	
	/**
	 * Konstruktor
	 * @param user {@link User}
	 * @param maxDamageBar {@link JProgressBar}
	 * @param epix {@link JFrame}
	 * @param menuCardCoinLabel {@link JLabel}
	 * @param card {@link JPanel}
	 */
	public PlusDamageListener(User user, JProgressBar maxDamageBar, JFrame epix, JLabel menuCardCoinLabel, JPanel card) {
		this.setUser(user);
		this.setmaxDamageBar(maxDamageBar);
		this.setEpix(epix);
		this.setMenuCardCoinLabel(menuCardCoinLabel);
		this.card = card;
	}

	public void actionPerformed(ActionEvent e) {
		if (user.getCoin() >= 5) {
			user.setCoin(user.getCoin() - 5);
			user.setDamage(user.getDamage() + 2);
			EpixController.getInstance().coinsUpdate(user, user.getCoin() -5);			
			menuCardCoinLabel.setText("" + user.getCoin());
			maxDamageBar.setValue((int) ((user.getDamage()-5)*2.5));
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "upgradeCard");
		}
	}

	
	/*
	 * Getter und Setter
	 */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JProgressBar getmaxDamageBar() {
		return maxDamageBar;
	}

	public void setmaxDamageBar(JProgressBar maxJumpBar) {
		this.maxDamageBar = maxJumpBar;
	}

	public JFrame getEpix() {
		return epix;
	}

	public void setEpix(JFrame epix) {
		this.epix = epix;
	}

	public JLabel getMenuCardCoinLabel() {
		return menuCardCoinLabel;
	}

	public void setMenuCardCoinLabel(JLabel menuCardCoinLabel) {
		this.menuCardCoinLabel = menuCardCoinLabel;
	}

	public JPanel getCard() {
		return card;
	}

	public void setCard(JPanel card) {
		this.card = card;
	}

}
