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

import ch.bbcag.epix.controller.EpixController;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.listener.LevelAuswahlCardButtonListener;
import ch.bbcag.epix.listener.MenuCardButtonListener;
import ch.bbcag.epix.listener.UpgradeCardButtonListener;

/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			EpixView.java.java Copyright Berufsbildungscenter 2015
 */

public class EpixView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	/*
	 * 
	 */
	protected String backgroundPath = "Resources/Backgrounds/EpixTitleScreen.png";
	protected String coinSpinPath = "Resources/Backgrounds/Coin spin.gif";
	
	/*
	 * Cards
	 */
	protected JPanel cards = new JPanel(new CardLayout());
	
	protected JPanel menuCard = new JPanel();
	protected JPanel upgradeCard = new JPanel();
	protected JPanel levelAuswahlCard = new JPanel();
	
	
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
	protected JButton upgradeZurückButton = new JButton("Zurück");
	
	//levelAuswahlCard
	protected JButton level1Button = new JButton("Level 1");
	protected JButton level2Button = new JButton("Level 2");
	protected JButton bossLevelButton = new JButton("Boss Level");
	protected JButton levelZurückBtton = new JButton("Zur\u00FCck");
	
	
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
	protected JLabel healthLabel = new JLabel("MaxHealth");
	protected JLabel jumpLabel = new JLabel("MaxJump");
	protected JLabel speedLabel = new JLabel("MaxSpeed");
	protected JLabel damageLabel = new JLabel("Damage");
	
	//levelAuswahlCard
	protected JLabel levelAuswahlCardCoinImage = new JLabel("");
	protected JLabel levelAuswahlCardCoinLabel;
	protected JLabel levelAuswahlCardPlayerLabel;
	protected JLabel levelAuswahlCardBackgroundImage = new JLabel("");

	
	
	
	public EpixView(User user) {
		
		
		this.setUser(user);
		
		JFrame epix = new JFrame();

		epix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		epix.setResizable(false);
		epix.setBounds(100, 100, 801, 523);
		epix.setLocationRelativeTo(null);
		epix.getContentPane().removeAll();
		
		
		/*
		 * menuCard
		 */
		menuCard.setLayout(null);
		
		//coin
		menuCardCoinImage.setBounds(0, 10, 64, 64);
		menuCard.add(menuCardCoinImage);		
		menuCardCoinImage.setIcon(new ImageIcon(coinSpinPath));				
		menuCardCoinLabel = new JLabel("" + getUser().getCoin());
		menuCardCoinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		menuCardCoinLabel.setBounds(67, 10, 56, 64);
		menuCard.add(menuCardCoinLabel);
		
		//player
		menuCardPlayerLabel = new JLabel("Willkommen zurück, " + getUser().getUsername());
		menuCardPlayerLabel.setBounds(598, 18, 239, 36);
		menuCard.add(menuCardPlayerLabel);

		
		spielenButton.setBounds(485, 319, 89, 23);
		menuCard.add(spielenButton);
				
		ranglisteButton.setBounds(485, 353, 89, 23);
		menuCard.add(ranglisteButton);

		menuCardBackgroundImage.setIcon(new ImageIcon(backgroundPath));
		menuCardBackgroundImage.setBounds(0, 0, 801, 523);
		menuCard.add(menuCardBackgroundImage);

		
		cards.add(menuCard, "menuCard");
		
		
		/*
		 * upgradeCard
		 */
		upgradeCard.setLayout(null);
		
		//coin
		upgradeCardCoinImage.setBounds(0, 10, 64, 64);		
		upgradeCardCoinImage.setIcon(new ImageIcon(coinSpinPath));
		upgradeCard.add(upgradeCardCoinImage);				
		upgradeCardCoinLabel = new JLabel(""+ getUser().getCoin());
		upgradeCardCoinLabel.setBounds(67, 10, 56, 64);
		upgradeCardCoinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		upgradeCard.add(upgradeCardCoinLabel);
		
		//player
		upgradeCardPlayerLabel = new JLabel("Willkommen zurück, " + getUser().getUsername());
		upgradeCardPlayerLabel.setBounds(598, 18, 239, 36);
		upgradeCard.add(upgradeCardPlayerLabel);
			
		//maxHealthBar
		healthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		healthLabel.setBounds(191, 127, 110, 36);
		upgradeCard.add(healthLabel);
		JProgressBar maxHealthBar = new JProgressBar();
		maxHealthBar.setValue(20);
		maxHealthBar.setBounds(327, 127, 185, 36);
		upgradeCard.add(maxHealthBar);		
		plusHealthButton.setBounds(522, 130, 41, 30);		
		upgradeCard.add(plusHealthButton);
		
		//maxJumpBar	
		jumpLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		jumpLabel.setBounds(191, 174, 110, 36);
		upgradeCard.add(jumpLabel);
		JProgressBar maxJumpBar = new JProgressBar();
		maxJumpBar.setValue(40);		
		maxJumpBar.setBounds(327, 174, 185, 36);
		upgradeCard.add(maxJumpBar);		
		plusJumpButton.setBounds(522, 177, 41, 30);
		upgradeCard.add(plusJumpButton);
		
		//maxSpeed
		speedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		speedLabel.setBounds(191, 221, 110, 36);
		upgradeCard.add(speedLabel);
		JProgressBar maxSpeedBar = new JProgressBar();
		maxSpeedBar.setValue(60);		
		maxSpeedBar.setBounds(327, 221, 185, 36);
		upgradeCard.add(maxSpeedBar);	
		plusSpeedButton.setBounds(522, 224, 41, 30);
		upgradeCard.add(plusSpeedButton);
		
		//damageBar
		damageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		damageLabel.setBounds(191, 268, 110, 36);
		upgradeCard.add(damageLabel);
		JProgressBar damageBar = new JProgressBar();
		damageBar.setValue(80);	
		damageBar.setBounds(327, 268, 185, 36);
		upgradeCard.add(damageBar);	
		plusDamageButton.setBounds(522, 271, 41, 30);
		upgradeCard.add(plusDamageButton);
		
		
		levelAuswählenButton.setBounds(395, 361, 168, 30);
		upgradeCard.add(levelAuswählenButton);
				
		upgradeZurückButton.setBounds(254, 361, 131, 30);
		upgradeCard.add(upgradeZurückButton);
		
		upgradeCardBackgroundImage.setBounds(0, 0, 801, 523);		
		upgradeCardBackgroundImage.setIcon(new ImageIcon(backgroundPath));
		upgradeCard.add(upgradeCardBackgroundImage);
		
		cards.add(upgradeCard, "upgradeCard");
		
		
		/*
		 * levelAuswahlCard
		 */
		levelAuswahlCard.setLayout(null);
		
		level1Button.setBounds(67, 159, 171, 170);	
		EpixController.getInstance().checkLevelSaved(user, level1Button);
		levelAuswahlCard.add(level1Button);
		
		
		level2Button.setBounds(308, 159, 171, 170);	
		EpixController.getInstance().checkLevelSaved(user, level2Button);
		levelAuswahlCard.add(level2Button);
		
		
		bossLevelButton.setBounds(550, 159, 171, 170);	
		EpixController.getInstance().checkLevelSaved(user, bossLevelButton);
		levelAuswahlCard.add(bossLevelButton);
		
		levelZurückBtton.setBounds(334, 378, 121, 28);		
		levelAuswahlCard.add(levelZurückBtton);
		
		levelAuswahlCardCoinImage.setBounds(0, 10, 64, 64);				
		levelAuswahlCardCoinImage.setIcon(new ImageIcon(coinSpinPath));
		levelAuswahlCard.add(levelAuswahlCardCoinImage);
				
		levelAuswahlCardCoinLabel = new JLabel("" + getUser().getCoin());
		levelAuswahlCardCoinLabel.setBounds(67, 10, 56, 64);
		levelAuswahlCardCoinLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		levelAuswahlCard.add(levelAuswahlCardCoinLabel);
		
		levelAuswahlCardPlayerLabel = new JLabel("Willkommen zurück, "  + getUser().getUsername());
		levelAuswahlCardPlayerLabel.setBounds(598, 18, 239, 36);
		levelAuswahlCard.add(levelAuswahlCardPlayerLabel);
		
		levelAuswahlCardBackgroundImage.setIcon(new ImageIcon(backgroundPath));
		levelAuswahlCardBackgroundImage.setBounds(0, 0, 801, 523);
		levelAuswahlCard.add(levelAuswahlCardBackgroundImage);
	
		cards.add(levelAuswahlCard, "levelAuswahlCard");
		
		
		/*
		 * Listener
		 */
		//menuCard
		spielenButton.addActionListener(new MenuCardButtonListener(cards));
		
		//upgradeCard
		upgradeZurückButton.addActionListener(new UpgradeCardButtonListener(cards));
		levelAuswählenButton.addActionListener(new UpgradeCardButtonListener(cards));
		
		//levelAuswahlCard
		levelZurückBtton.addActionListener(new LevelAuswahlCardButtonListener(cards, getUser(), epix));
		level1Button.addActionListener(new LevelAuswahlCardButtonListener(cards, getUser(), epix));
		level2Button.addActionListener(new LevelAuswahlCardButtonListener(cards, getUser(), epix));
		bossLevelButton.addActionListener(new LevelAuswahlCardButtonListener(cards, getUser(), epix));
		
		
		epix.setContentPane(cards);
		epix.setVisible(true);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
