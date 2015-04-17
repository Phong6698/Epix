package ch.bbcag.epix.view;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ch.bbcag.epix.entity.Player;


public class EpixView extends JFrame {
	
	private Player player;

	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 */
	public EpixView(Player player) {
		
		this.setPlayer(player);
		
		JLabel  lblNewLabel = new JLabel("");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnSpielen = new JButton("Spielen");
		
		
		btnSpielen.setBounds(430, 306, 113, 23);
		btnSpielen.setVisible(true);
		contentPane.add(btnSpielen);
		
		JButton btnNewButton = new JButton("Rangliste");
		btnNewButton.setBounds(430, 340, 113, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("" + getPlayer().getCoin());
		lblNewLabel_2.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(68, 0, 56, 64);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setIcon(new ImageIcon("Resources/Coin spin.gif"));
		lblNewLabel_1.setBounds(0, 0, 64, 64);
		contentPane.add(lblNewLabel_1);
		
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("Resources/PixelGame.png"));
		lblNewLabel.setBounds(0, 0, 785, 485);
		contentPane.add(lblNewLabel);
		
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}
}
