package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Popup wenn das E-mail Feld leer ist
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class EmailEmpty extends JFrame {

	private static final long serialVersionUID = 1L;

	public EmailEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Email ausf\u00FCllen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}
