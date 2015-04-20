package ch.bbcag.epix.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import ch.bbcag.epix.entity.User;

public class EpixView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User player;
	
	//JButton
	JButton spielenButton = new JButton("Spielen");
	JButton ranglisteButton = new JButton("Rangliste");
	
	//JButton
	JLabel coinImage = new JLabel("");
	JLabel coinLabel;
	JLabel playerLabel;
	JLabel backgroundImage = new JLabel("");
	

	public EpixView(User player) {
		this.setPlayer(player);
		
		JFrame epix = new JFrame();
		epix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		epix.setBounds(100, 100, 801, 523);
		epix.setLocationRelativeTo(null);
		epix.getContentPane().setLayout(null);
				
		coinImage.setIcon(new ImageIcon("Resources2/Coin spin.gif"));
		coinImage.setBounds(0, 10, 64, 64);
		epix.getContentPane().add(coinImage);
				
		coinLabel = new JLabel("" + getPlayer().getCoin());
		coinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		coinLabel.setBounds(67, 10, 56, 64);
		epix.getContentPane().add(coinLabel);
		
		playerLabel = new JLabel("Willkommen zurück, " + getPlayer().getUsername());
		playerLabel.setBounds(598, 18, 239, 36);
		epix.getContentPane().add(playerLabel);
		
		spielenButton.setBounds(485, 319, 89, 23);
		epix.getContentPane().add(spielenButton);
		
		ranglisteButton.setBounds(485, 353, 89, 23);
		epix.getContentPane().add(ranglisteButton);

		backgroundImage.setIcon(new ImageIcon("Resources2/PixelGame.png"));
		backgroundImage.setBounds(0, 0, 785, 485);
		epix.getContentPane().add(backgroundImage);


		epix.setVisible(true);
	}


	public User getPlayer() {
		return player;
	}


	public void setPlayer(User player) {
		this.player = player;
	}
}
