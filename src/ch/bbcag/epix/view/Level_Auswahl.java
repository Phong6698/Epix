package ch.bbcag.epix.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.listener.TutorialListener;

/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Level_Auswahl.java.java Copyright Berufsbildungscenter 2015
 */

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
	private final JButton btnNewButton = new JButton("Level 1");
	private final JButton btnLevel = new JButton("Level 2");
	private final JButton btnBossLevel = new JButton("Boss Level");
	private final JButton btnNewButton_1 = new JButton("Zur\u00FCck");
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
	 * Das Auswahl Menü wird herstellt
	 */
	public Level_Auswahl() {
		
		JFrame epix = new JFrame();
		epix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		epix.setBounds(100, 100, 801, 523);
		epix.setLocationRelativeTo(null);
		epix.getContentPane().setLayout(null);
		
		btnNewButton.setBounds(67, 159, 171, 170);
		btnNewButton.addActionListener(new TutorialListener(epix));
		epix.getContentPane().add(btnNewButton);
		
		btnLevel.setBounds(308, 159, 171, 170);		
		epix.getContentPane().add(btnLevel);
		
		btnBossLevel.setBounds(550, 159, 171, 170);		
		epix.getContentPane().add(btnBossLevel);
		
		btnNewButton_1.setBounds(334, 378, 121, 28);		
		epix.getContentPane().add(btnNewButton_1);
		
		coinImage.setBounds(0, 10, 64, 64);
				
		coinImage.setIcon(new ImageIcon("Resources2/Coin spin.gif"));
		epix.getContentPane().add(coinImage);
				
		coinLabel = new JLabel("100"/*+ getPlayer().getCoin()*/);
		coinLabel.setBounds(67, 10, 56, 64);
		coinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		epix.getContentPane().add(coinLabel);
		
		playerLabel = new JLabel("Willkommen zurück, "/* + getPlayer().getUsername()*/);
		playerLabel.setBounds(598, 18, 239, 36);
		epix.getContentPane().add(playerLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
