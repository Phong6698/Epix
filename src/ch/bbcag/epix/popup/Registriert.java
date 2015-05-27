package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Popup wenn der Benutzer sich erfolgreicht registriert hat
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Registriert.java.java Copyright Berufsbildungscenter 2015
 */

public class Registriert extends JFrame {

	private static final long serialVersionUID = 1L;

	public Registriert() {
		JOptionPane.showMessageDialog(null,"Sie wurden erfolgreich registriert!", null, JOptionPane.INFORMATION_MESSAGE, null);
	};

}
