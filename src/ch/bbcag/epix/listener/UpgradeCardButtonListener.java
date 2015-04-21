package ch.bbcag.epix.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class UpgradeCardButtonListener implements ActionListener {

	private JPanel card;
	
	public UpgradeCardButtonListener(JPanel card) {
		this.setCard(card);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Level ausw\u00E4hlen")) {
			
		}
		else if (e.getActionCommand().equals("Zurück")) {
			CardLayout cardLayout = (CardLayout) getCard().getLayout();
			cardLayout.show(getCard(), "menuCard");
		}
		
	}

	public JPanel getCard() {
		return card;
	}

	public void setCard(JPanel card) {
		this.card = card;
	}

}