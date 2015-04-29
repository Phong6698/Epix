package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			FalsePassword.java.java Copyright Berufsbildungscenter 2015
 */

public class FalsePassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FalsePassword() {
		JOptionPane.showMessageDialog(null,"Passwörter stimmen nicht überein", null, JOptionPane.INFORMATION_MESSAGE, null);
	};

}
