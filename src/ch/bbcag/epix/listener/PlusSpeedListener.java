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
 * Plus Speed Listener
 *
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class PlusSpeedListener implements ActionListener {
	private User user;
	private JProgressBar maxSpeedBar;
	private JFrame epix;
	private JLabel menuCardCoinLabel;
	private JPanel card;

	
	/**
	 * Konstruktor
	 * @param user {@link User}
	 * @param maxSpeedBar {@link JProgressBar}
	 * @param epix {@link JFrame}
	 * @param menuCardCoinLabel {@link JPanel}
	 * @param card {@link JPanel}
	 */
	public PlusSpeedListener(User user, JProgressBar maxSpeedBar, JFrame epix, JLabel menuCardCoinLabel, JPanel card) {
		this.setUser(user);
		this.setmaxSpeedBar(maxSpeedBar);
		this.setEpix(epix);
		this.setMenuCardCoinLabel(menuCardCoinLabel);
		this.card = card;
	}

	public void actionPerformed(ActionEvent e) {
		if (user.getCoin() >= 5) {
			user.setCoin(user.getCoin() - 5);
			user.setMaxMoveSpeed(user.getMaxMoveSpeed() +0.1);
			user.setMoveSpeed(user.getMoveSpeed() +0.1);
			EpixController.getInstance().coinsUpdate(user, user.getCoin());
			menuCardCoinLabel.setText("" + user.getCoin());
			maxSpeedBar.setValue((int) ((user.getMoveSpeed()*10) - 32)*5);
			System.out.println(maxSpeedBar.getValue());
			System.out.println(user.getMoveSpeed());
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

	public JProgressBar getmaxSpeedBar() {
		return maxSpeedBar;
	}

	public void setmaxSpeedBar(JProgressBar maxSpeedBar) {
		this.maxSpeedBar = maxSpeedBar;
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
