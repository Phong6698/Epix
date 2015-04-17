package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EmailEmpty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Email ausfüllen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}
