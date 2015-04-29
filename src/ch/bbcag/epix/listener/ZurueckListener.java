package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ch.bbcag.epix.view.Login;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			ZurueckListener.java.java Copyright Berufsbildungscenter 2015
 */

public class ZurueckListener implements ActionListener {
	
	private JFrame loginView;
	
	public ZurueckListener(JFrame loginView) {
		this.setLoginView(loginView);
	}

	public void actionPerformed(ActionEvent e) {
			loginView.dispose();
			new Login();
	}
	
	public void setLoginView(JFrame loginView) {
		this.loginView = loginView;
	}
	
}
