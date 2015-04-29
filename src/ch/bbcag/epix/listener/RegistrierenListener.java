package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ch.bbcag.epix.view.Registration;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			RegistrierenListener.java.java Copyright Berufsbildungscenter 2015
 */

public class RegistrierenListener implements ActionListener {
	
	private JFrame login;
	

	public RegistrierenListener(JFrame login){
		this.setLogin(login);
	}

	public void actionPerformed(ActionEvent e) {
		getLogin().dispose();
		
		
		new Registration();
	}	
	
	public JFrame getLogin() {
		return login;
	}

	public void setLogin(JFrame login) {
		this.login = login;
	}

}
