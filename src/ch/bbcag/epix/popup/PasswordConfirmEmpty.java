package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PasswordConfirmEmpty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordConfirmEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Passwort best�tigen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}