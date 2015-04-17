package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ch.bbcag.epix.view.Login;



public class ZurueckListener implements ActionListener {
	
	private JFrame loginView;
	
	public ZurueckListener(JFrame loginView) {
		this.setLoginView(loginView);
	}

	public void actionPerformed(ActionEvent e) {
			loginView.dispose();
			Login frame = new Login();
			frame.setVisible(true);
	}
	
	public void setLoginView(JFrame loginView) {
		this.loginView = loginView;
	}
	
}
