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

public class PlusJumpListener implements ActionListener {
	private User user;
	private JProgressBar maxJumpBar;
	private JFrame epix;
	private JLabel menuCardCoinLabel;
	private JPanel card;

	public PlusJumpListener(User user, JProgressBar maxJumpBar, JFrame epix, JLabel menuCardCoinLabel, JPanel card) {
		this.setUser(user);
		this.setMaxJumpBar(maxJumpBar);
		this.setEpix(epix);
		this.setMenuCardCoinLabel(menuCardCoinLabel);
		this.card = card;
	}

	public void actionPerformed(ActionEvent e) {
		if (user.getCoin() >= 5) {
			user.setCoin(user.getCoin() - 5);
			user.setMaxJump(-0.2);
			EpixController.getInstance().coinsUpdate(user, -5);
			menuCardCoinLabel.setText("" + user.getCoin());
			maxJumpBar.setValue(maxJumpBar.getValue() + 5);
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "upgradeCard");
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JProgressBar getMaxJumpBar() {
		return maxJumpBar;
	}

	public void setMaxJumpBar(JProgressBar maxJumpBar) {
		this.maxJumpBar = maxJumpBar;
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
