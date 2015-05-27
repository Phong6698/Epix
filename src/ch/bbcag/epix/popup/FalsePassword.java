package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Popup wenn das Passwort falsch ist
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class FalsePassword extends JFrame {

	private static final long serialVersionUID = 1L;

	public FalsePassword() {
		JOptionPane.showMessageDialog(null,"Passw�rter stimmen nicht �berein", null, JOptionPane.INFORMATION_MESSAGE, null);
	};

}
