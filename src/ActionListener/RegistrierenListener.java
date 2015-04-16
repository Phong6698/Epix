package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ch.bbcag.epix.view.Registration;


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
