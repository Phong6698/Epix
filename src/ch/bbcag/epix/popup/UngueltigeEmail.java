package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Popup wenn das E-mail ungueltig ist
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			UngueltigeEmail.java.java Copyright Berufsbildungscenter 2015
 */

public class UngueltigeEmail extends JFrame {

	private static final long serialVersionUID = 1L;

	public UngueltigeEmail() {
		JOptionPane.showMessageDialog(null,"Bitte g�ltige E-Mail Adresse eingeben", null, JOptionPane.ERROR_MESSAGE, null);
	};

}
