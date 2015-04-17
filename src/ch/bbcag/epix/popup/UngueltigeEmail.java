package ch.bbcag.epix.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UngueltigeEmail extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UngueltigeEmail() {
		JOptionPane.showMessageDialog(null,"Bitte gültige E-Mail Adresse eingeben", null, JOptionPane.ERROR_MESSAGE, null);
	};

}
