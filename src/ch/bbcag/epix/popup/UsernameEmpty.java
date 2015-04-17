package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UsernameEmpty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Username ausfüllen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}
