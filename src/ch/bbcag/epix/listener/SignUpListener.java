package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ch.bbcag.epix.controller.EpixController;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.view.Login;


public class SignUpListener implements ActionListener {
	
	

	//Instanzvariablen
	private JTextField user = null;
	private JPasswordField password = null;
	private JPasswordField passwordConfirm = null;
	private JTextField email = null;
	private JFrame login;
	/**
	 * Konstruktor fuer LoginListner setzt alle Parameter in Instanzvariablen
	 */
	public SignUpListener(JTextField username, JTextField email, JPasswordField password,JPasswordField passwordConfirm, JFrame login){
		this.setUser(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setPasswordConfirm(passwordConfirm);
		this.setLogin(login);
	}
	
	/**
	 * ActionListner fuer den Button registrieren, traegt einen neuen User in die DB ein
	 */
	public void actionPerformed(ActionEvent e) {
//		getLogin().dispose();
//		new Login();
		
		User newUser = new User();
		
		String user = this.getUser().getText();
		String password = this.getPassword().getText();
		String passwordConfirm = this.getPasswordConfirm().getText();
		String email = this.getEmail().getText();
		
		newUser.setUsername(user);
		newUser.setPassword(password);
		newUser.setPasswordConfirm(passwordConfirm);
		newUser.setEmail(email);

		EpixController.getInstance().registrieren(newUser);
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

	public JFrame getLogin() {
		return login;
	}

	public void setLogin(JFrame login) {
		this.login = login;
	}

	public JPasswordField getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(JPasswordField passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}
