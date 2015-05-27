package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ch.bbcag.epix.view.Login;

/**
 * Zurueck Button Listener fuer Registrieren
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class ZurueckListener implements ActionListener {
	
	private JFrame loginView;
	
	
	/**
	 * Konstruktor
	 * @param loginView {@link JFrame}
	 */
	public ZurueckListener(JFrame loginView) {
		this.setLoginView(loginView);
	}

	public void actionPerformed(ActionEvent e) {
			loginView.dispose();
			new Login();
	}
	
	
	/*
	 * Getter und Setter
	 */
	public void setLoginView(JFrame loginView) {
		this.loginView = loginView;
	}
	
}
