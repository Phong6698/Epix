package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ch.bbcag.epix.controller.EpixController;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.view.Login;

/**
 * ActionListner fuer den Button registrieren, traegt einen neuen User in die DB ein
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class SignUpListener implements ActionListener {
	
	//Instanzvariablen
	private JTextField user = null;
	private JPasswordField password = null;
	private JPasswordField passwordConfirm = null;
	private JTextField email = null;
	private JFrame registrationView = null;

	
	/**
	 * Konstruktor fuer Registrieren
	 * @param username {@link JTextField}
	 * @param email {@link JTextField}
	 * @param password {@link JPasswordField}
	 * @param passwordConfirm {@link JPasswordField}
	 */
	public SignUpListener(JTextField username, JTextField email, JPasswordField password,JPasswordField passwordConfirm){
		this.setUser(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setPasswordConfirm(passwordConfirm);
	}
	
	
	/**
	 * Konstruktor fuer zurueck
	 * @param registrationView {@link JFrame}
	 */
	public SignUpListener(JFrame registrationView){
		this.setRegistrationView(registrationView);
	}
	
	
	/*
	 * ActionListner fuer den Button registrieren, traegt einen neuen User in die DB ein
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Registrieren")){
			User newUser = new User();
			
			String user = this.getUser().getText();
			@SuppressWarnings("deprecation")
			String password = this.getPassword().getText();
			@SuppressWarnings("deprecation")
			String passwordConfirm = this.getPasswordConfirm().getText();
			String email = this.getEmail().getText();
			
			newUser.setUsername(user);
			newUser.setPassword(password);
			newUser.setPasswordConfirm(passwordConfirm);
			newUser.setEmail(email);
	
			EpixController.getInstance().registrieren(newUser);
		} else if(e.getActionCommand().equals("Zur\u00FCck")){
			getRegistrationView().dispose();
			new Login();
		}
	}
	

	//Getter
	public JTextField getUser() {
		return user;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public JTextField getEmail() {
		return email;
	}

	//Setter
	public void setUser(JTextField user) {
		this.user = user;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public void setEmail(JTextField email) {
		this.email = email;
	}

	public JPasswordField getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(JPasswordField passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public JFrame getRegistrationView() {
		return registrationView;
	}

	public void setRegistrationView(JFrame registrationView) {
		this.registrationView = registrationView;
	}

}
