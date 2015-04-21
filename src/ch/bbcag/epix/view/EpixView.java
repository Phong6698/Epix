package ch.bbcag.epix.view;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.listener.MenuCardButtonListener;
import ch.bbcag.epix.listener.UpgradeCardButtonListener;

public class EpixView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User player;
	
	/*
	 * Cards
	 */
	protected JPanel cards = new JPanel(new CardLayout());
	
	protected JPanel menuCard = new JPanel();
	protected JPanel upgradeCard = new JPanel();
	
	
	/*
	 * JButton
	 */
	//menuCard
	protected JButton spielenButton = new JButton("Spielen");
	protected JButton ranglisteButton = new JButton("Rangliste");
	
	//upgradeCard
	protected JButton plusHealthButton = new JButton("+");
	protected JButton plusJumpButton = new JButton("+");
	protected JButton plusSpeedButton = new JButton("+");
	protected JButton plusDamageButton = new JButton("+");
	protected JButton levelAuswählenButton = new JButton("Level ausw\u00E4hlen");
	protected JButton zurückButton = new JButton("Zurück");
	
	
	/*
	 * JLabel
	 */
	//menuCard
	protected JLabel menuCardCoinImage = new JLabel("");
	protected JLabel menuCardCoinLabel;
	protected JLabel menuCardPlayerLabel;
	protected JLabel menuCardBackgroundImage = new JLabel("");
	
	//upgradeCard
	protected JLabel upgradeCardCoinImage = new JLabel("");
	protected JLabel upgradeCardCoinLabel;
	protected JLabel upgradeCardPlayerLabel;
	protected JLabel upgradeCardBackgroundImage = new JLabel("");
	protected JLabel HealthLabel = new JLabel("MaxHealth");
	protected JLabel JumpLabel = new JLabel("MaxJump");
	protected JLabel SpeedLabel = new JLabel("MaxSpeed");
	protected JLabel DamageLabel = new JLabel("Damage");
	
	
	
	public EpixView(User player) {
		this.setPlayer(player);
		
//		JFrame epix = new JFrame();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100, 100, 801, 523);
		this.setLocationRelativeTo(null);
		this.getContentPane().removeAll();
		
		
		/*
		 * menuCard
		 */
		getContentPane().setLayout(null);
		menuCard.setLayout(null);
		menuCardCoinImage.setBounds(0, 0, 64, 64);
		menuCard.add(menuCardCoinImage);
		
		menuCardCoinImage.setIcon(new ImageIcon("Resources2/Coin spin.gif"));
				
		menuCardCoinLabel = new JLabel("" + getPlayer().getCoin());
		menuCardCoinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		menuCardCoinLabel.setBounds(67, 10, 56, 64);
		menuCard.add(menuCardCoinLabel);
		
		menuCardPlayerLabel = new JLabel("Willkommen zurück, " + getPlayer().getUsername());
		menuCardPlayerLabel.setBounds(598, 18, 239, 36);
		menuCard.add(menuCardPlayerLabel);
		
		spielenButton.setBounds(485, 319, 89, 23);
		menuCard.add(spielenButton);
		
		
		ranglisteButton.setBounds(485, 353, 89, 23);
		menuCard.add(ranglisteButton);

		menuCardBackgroundImage.setIcon(new ImageIcon("Resources2/PixelGame.png"));
		menuCardBackgroundImage.setBounds(0, 0, 801, 523);
		menuCard.add(menuCardBackgroundImage);

		
		cards.add(menuCard, "menuCard");
		
		
		/*
		 * upgradeCard
		 */
		upgradeCard.setLayout(null);
		
		upgradeCardCoinImage.setBounds(0, 10, 64, 64);
		
		upgradeCardCoinImage.setIcon(new ImageIcon("Resources2/Coin spin.gif"));
		upgradeCard.add(upgradeCardCoinImage);
				
		upgradeCardCoinLabel = new JLabel(""+ getPlayer().getCoin());
		upgradeCardCoinLabel.setBounds(67, 10, 56, 64);
		upgradeCardCoinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		upgradeCard.add(upgradeCardCoinLabel);
		
		upgradeCardPlayerLabel = new JLabel("Willkommen zurück, " + getPlayer().getUsername());
		upgradeCardPlayerLabel.setBounds(598, 18, 239, 36);
		upgradeCard.add(upgradeCardPlayerLabel);
		
		HealthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		HealthLabel.setBounds(191, 127, 110, 36);
		upgradeCard.add(HealthLabel);
		
		JumpLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JumpLabel.setBounds(191, 174, 110, 36);
		upgradeCard.add(JumpLabel);
		
		SpeedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		SpeedLabel.setBounds(191, 221, 110, 36);
		upgradeCard.add(SpeedLabel);
		
		DamageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		DamageLabel.setBounds(191, 268, 110, 36);
		upgradeCard.add(DamageLabel);
		
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(23);
		progressBar.setBounds(327, 127, 185, 36);
		upgradeCard.add(progressBar);
		
		plusHealthButton.setBounds(522, 130, 41, 30);
		upgradeCard.add(plusHealthButton);
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setValue(54);
		
		progressBar_1.setBounds(327, 174, 185, 36);
		upgradeCard.add(progressBar_1);
		
		plusJumpButton.setBounds(522, 177, 41, 30);
		upgradeCard.add(plusJumpButton);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setValue(38);
		
		progressBar_2.setBounds(327, 221, 185, 36);
		upgradeCard.add(progressBar_2);
		
		plusSpeedButton.setBounds(522, 224, 41, 30);
		upgradeCard.add(plusSpeedButton);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressBar_3.setValue(69);
		
		progressBar_3.setBounds(327, 268, 185, 36);
		upgradeCard.add(progressBar_3);
		
		plusDamageButton.setBounds(522, 271, 41, 30);
		upgradeCard.add(plusDamageButton);
		

		
		levelAuswählenButton.setBounds(395, 361, 168, 30);
		upgradeCard.add(levelAuswählenButton);
				
		zurückButton.setBounds(254, 361, 131, 30);
		upgradeCard.add(zurückButton);
		
		upgradeCardBackgroundImage.setBounds(0, 0, 801, 523);
		
		upgradeCardBackgroundImage.setIcon(new ImageIcon("Resources2/PixelGame.png"));
		upgradeCard.add(upgradeCardBackgroundImage);
		
		cards.add(upgradeCard, "upgradeCard");
		
		
		/*
		 * Listener
		 */
		spielenButton.addActionListener(new MenuCardButtonListener(cards));
		zurückButton.addActionListener(new UpgradeCardButtonListener(cards));
		
		
		this.setContentPane(cards);
		this.setVisible(true);
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}
}
