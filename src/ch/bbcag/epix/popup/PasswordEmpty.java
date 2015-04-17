package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PasswordEmpty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Passwort ausfüllen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}
