package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			EmailEmpty.java.java Copyright Berufsbildungscenter 2015
 */

public class EmailEmpty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailEmpty() {
		JOptionPane.showMessageDialog(null,"Bitte Email ausfüllen!", null, JOptionPane.WARNING_MESSAGE, null);
	};

}
