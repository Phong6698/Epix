package ch.bbcag.epix.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ch.bbcag.epix.entity.User;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User player;
	
	//JButton
	JLabel coinImage = new JLabel("");
	JLabel coinLabel;
	JLabel playerLabel;
	JLabel backgroundImage = new JLabel("");
	

	public MainPage() {
		this.setPlayer(player);
		
		JFrame epix = new JFrame();
		epix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		epix.setBounds(100, 100, 801, 523);
		epix.setLocationRelativeTo(null);
		epix.getContentPane().setLayout(null);
		coinImage.setBounds(0, 10, 64, 64);
				
		coinImage.setIcon(new ImageIcon("Resources2/Coin spin.gif"));
		epix.getContentPane().add(coinImage);
				
		coinLabel = new JLabel(""+ getPlayer().getCoin());
		coinLabel.setBounds(67, 10, 56, 64);
		coinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		epix.getContentPane().add(coinLabel);
		
		playerLabel = new JLabel("Willkommen zurück, " + getPlayer().getUsername());
		playerLabel.setBounds(598, 18, 239, 36);
		epix.getContentPane().add(playerLabel);
		
		JLabel lblLebenspunkte = new JLabel("MaxHealth");
		lblLebenspunkte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLebenspunkte.setBounds(191, 127, 110, 36);
		epix.getContentPane().add(lblLebenspunkte);
		
		JLabel lblMaxjump = new JLabel("MaxJump");
		lblMaxjump.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaxjump.setBounds(191, 174, 110, 36);
		epix.getContentPane().add(lblMaxjump);
		
		JLabel lblMaxspeed = new JLabel("MaxSpeed");
		lblMaxspeed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaxspeed.setBounds(191, 221, 110, 36);
		epix.getContentPane().add(lblMaxspeed);
		
		JLabel lblDamage = new JLabel("Damage");
		lblDamage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDamage.setBounds(191, 268, 110, 36);
		epix.getContentPane().add(lblDamage);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(23);
		progressBar.setBounds(327, 127, 185, 36);
		epix.getContentPane().add(progressBar);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(522, 130, 41, 30);
		epix.getContentPane().add(btnNewButton);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setValue(54);
		progressBar_1.setBounds(327, 174, 185, 36);
		epix.getContentPane().add(progressBar_1);
		
		JButton button = new JButton("+");
		button.setBounds(522, 177, 41, 30);
		epix.getContentPane().add(button);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setValue(38);
		progressBar_2.setBounds(327, 221, 185, 36);
		epix.getContentPane().add(progressBar_2);
		
		JButton button_1 = new JButton("+");
		button_1.setBounds(522, 224, 41, 30);
		epix.getContentPane().add(button_1);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressBar_3.setValue(69);
		progressBar_3.setBounds(327, 268, 185, 36);
		epix.getContentPane().add(progressBar_3);
		
		JButton button_2 = new JButton("+");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_2.setBounds(522, 271, 41, 30);
		epix.getContentPane().add(button_2);
		
		JButton btnNewButton_1 = new JButton("Level ausw\u00E4hlen");
		btnNewButton_1.setBounds(337, 361, 168, 30);
		epix.getContentPane().add(btnNewButton_1);
		backgroundImage.setBounds(0, 0, 785, 485);
		
				backgroundImage.setIcon(new ImageIcon("Resources2/PixelGame.png"));
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
