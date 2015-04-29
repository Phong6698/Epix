package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			LoginFailed.java.java Copyright Berufsbildungscenter 2015
 */

public class LoginFailed extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginFailed() {
		JOptionPane.showMessageDialog(null,"Username oder Passwort ist falsch!", null, JOptionPane.ERROR_MESSAGE, null);
	};

}
