package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UsernameVergeben extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameVergeben() {
		JOptionPane.showMessageDialog(null,"Username ist bereits vergeben", null, JOptionPane.INFORMATION_MESSAGE, null);
	};

}
