package ch.bbcag.epix.view;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ch.bbcag.epix.Main.Game;
import ch.bbcag.epix.listener.LoginListener;
import ch.bbcag.epix.listener.RegistrierenListener;

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;

	/*
	 * JTextField
	 */
	protected JTextField username = new JTextField();
	
	/*
	 * JPasswordField
	 */
	protected JPasswordField password = new JPasswordField();
	
	/*
	 * JButton
	 */
	protected JButton registrierenButton = new JButton("Registrieren");
	protected JButton loginbButton = new JButton("Login")  ;

	/*
	 * JLabel
	 */
	protected JLabel usernameLabel = new JLabel("Username");
	protected JLabel passwordLabel = new JLabel("Passwort");	
	protected JLabel titelLabel = new JLabel("Epix Login");

	

	public Login() {
		
		Game.game();	
		JFrame login = new JFrame();
		
		
		login.setTitle("Epix");
		login.setBounds(500, 400, 419, 238);
		login.setLocationRelativeTo(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.getContentPane().setLayout(null);
		login.setResizable(false);
	
		titelLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titelLabel.setBounds(48, 11, 141, 23);
		login.add(titelLabel);
		
		usernameLabel.setBounds(48, 45, 102, 24);
		login.add(usernameLabel);

		username.setBounds(160, 47, 197, 20);
		login.add(username);
		username.setColumns(10);
		
		passwordLabel.setBounds(48, 76, 102, 24);
		login.add(passwordLabel);
				
		password.setBounds(160, 78, 197, 20);
		login.add(password);
								
		registrierenButton.setBounds(251, 120, 106, 23);
		registrierenButton.addActionListener(new RegistrierenListener(login));
		login.add(registrierenButton);
				
		loginbButton.setBounds(160, 120, 84, 23);
		loginbButton.addActionListener(new LoginListener(username, password, login));
		login.add(loginbButton);
		
		login.setVisible(true);
	}
	
	public static void main(String[] args) throws SQLException {
		Game.game();
		Game.setVisible(true);
	}
}
