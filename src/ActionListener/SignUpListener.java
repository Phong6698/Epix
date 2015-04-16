package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ch.bbcag.epix.view.Login;


public class SignUpListener implements ActionListener {
	
	private JFrame login;
	

	public SignUpListener(JFrame login){
		this.setLogin(login);
	}

	@Override public void actionPerformed(ActionEvent e) {
		getLogin().dispose();
		
		new Login();
	}	
	
	public JFrame getLogin() {
		return login;
	}

	public void setLogin(JFrame login) {
		this.login = login;
	}

}
