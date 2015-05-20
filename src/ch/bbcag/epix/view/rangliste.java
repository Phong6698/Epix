package ch.bbcag.epix.view;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

public class rangliste extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public rangliste() {
		setLayout(null);
		
		table = new JTable();
		table.setBounds(171, 137, 487, 314);
		add(table);
		
		JLabel lblRangliste = new JLabel("Rangliste");
		lblRangliste.setBounds(363, 41, 78, 37);
		add(lblRangliste);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(100, 100, 801, 523);
	}
}
