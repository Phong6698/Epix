package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Popup wenn Username Feld leer ist
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			UsernameEmpty.java.java Copyright Berufsbildungscenter 2015
 */
public class UsernameEmpty extends JFrame {

	private static final long serialVersionUID = 1L;

	public UsernameEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Username ausf�llen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}
