package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FalsePassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FalsePassword() {
		JOptionPane.showMessageDialog(null,"Passw�rter stimmen nicht �berein", null, JOptionPane.INFORMATION_MESSAGE, null);
	};

}
