package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			UsernameEmpty.java.java Copyright Berufsbildungscenter 2015
 */
public class UsernameEmpty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Username ausfüllen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}
