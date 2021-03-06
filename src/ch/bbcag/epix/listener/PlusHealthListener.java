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
 * Plus Health Listener
 *
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class PlusHealthListener implements ActionListener {
	private User user;
	private JProgressBar maxHealthBar;
	private JFrame epix;
	private JLabel menuCardCoinLabel;
	private JPanel card;

	
	/**
	 * 
	 * @param user {@link User}
	 * @param maxHealthBar {@link JProgressBar}
	 * @param epix {@link JFrame}
	 * @param menuCardCoinLabel {@link JLabel}
	 * @param card {@link JPanel}
	 */
	public PlusHealthListener(User user, JProgressBar maxHealthBar, JFrame epix, JLabel menuCardCoinLabel, JPanel card) {
		this.setUser(user);
		this.setMaxHealthBar(maxHealthBar);
		this.setEpix(epix);
		this.setMenuCardCoinLabel(menuCardCoinLabel);
		this.card = card;
	}

	public void actionPerformed(ActionEvent e) {
		if (user.getCoin() >= 5) {
		user.setCoin(user.getCoin() - 5);
		user.setMaxHealth(user.getMaxHealth() + 5);
		EpixController.getInstance().coinsUpdate(user, user.getCoin());
		menuCardCoinLabel.setText("" + user.getCoin());
		maxHealthBar.setValue(user.getMaxHealth()-50);
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

	public JProgressBar getMaxJumpBar() {
		return maxHealthBar;
	}

	public void setMaxHealthBar(JProgressBar maxHealthBar) {
		this.maxHealthBar = maxHealthBar;
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
