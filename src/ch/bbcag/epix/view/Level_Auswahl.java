package ch.bbcag.epix.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.bbcag.epix.entity.User;

public class Level_Auswahl extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User player;
	
	private JPanel contentPane;
	JLabel coinImage = new JLabel("");
	JLabel coinLabel;
	JLabel playerLabel;
	JLabel backgroundImage = new JLabel("");
	private final JLabel lblNewLabel = new JLabel("New label");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Level_Auswahl frame = new Level_Auswahl();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Level_Auswahl() {
		
		JFrame epix = new JFrame();
		epix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		epix.setBounds(100, 100, 801, 523);
		epix.setLocationRelativeTo(null);
		epix.getContentPane().setLayout(null);
		coinImage.setBounds(0, 10, 64, 64);
				
		coinImage.setIcon(new ImageIcon("Resources2/Coin spin.gif"));
		epix.getContentPane().add(coinImage);
				
		coinLabel = new JLabel("100"+ getPlayer().getCoin());
		coinLabel.setBounds(67, 10, 56, 64);
		coinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		epix.getContentPane().add(coinLabel);
		
		playerLabel = new JLabel("Willkommen zurück, " + getPlayer().getUsername());
		playerLabel.setBounds(598, 18, 239, 36);
		epix.getContentPane().add(playerLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setBounds(103, 178, 137, 149);
		
		epix.getContentPane().add(lblNewLabel);
		
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
