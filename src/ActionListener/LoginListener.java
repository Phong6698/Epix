package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ch.bbcag.epix.view.EpixView;

public class LoginListener implements ActionListener {

	private JFrame login;

	public LoginListener(JFrame login) {
		this.setLogin(login);
	}

	@Override public void actionPerformed(ActionEvent e) {
		getLogin().dispose();
		EpixView frame = new EpixView();
		frame.setVisible(true);
	}

	public JFrame getLogin() {
		return login;
	}

	public void setLogin(JFrame login) {
		this.login = login;
	}

}
