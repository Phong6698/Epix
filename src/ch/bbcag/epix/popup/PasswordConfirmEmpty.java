package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			PasswordConfirmEmpty.java.java Copyright Berufsbildungscenter 2015
 */

public class PasswordConfirmEmpty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordConfirmEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Passwort bestätigen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}
