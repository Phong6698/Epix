package ch.bbcag.epix.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Registration extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	protected JTextField username = new JTextField();
	protected JTextField email = new JTextField();
	protected JPasswordField password = new JPasswordField();
	protected JPasswordField passwordConfirm = new JPasswordField();
	
	protected JButton registrierenButton = new JButton("Registrieren");
	
	protected JLabel titelLabel = new JLabel("Epix Registration");
	protected JLabel usernameLabel = new JLabel("Username");
	protected JLabel emailLabel = new JLabel("E-mail");
	protected JLabel passwordLabel = new JLabel("Passwort");
	protected JLabel passwordConfirmLabel = new JLabel("Passwort best\u00E4tigen");


	public Registration() {
		this.setTitle("Epix");
		this.setBounds(500, 400, 419, 238);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		
		
		titelLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titelLabel.setBounds(48, 11, 141, 23);
		this.getContentPane().add(titelLabel);
				
		usernameLabel.setBounds(48, 45, 102, 24);
		this.getContentPane().add(usernameLabel);
				
		username.setColumns(10);
		username.setBounds(181, 47, 176, 20);
		this.add(username);
		
		email.setBounds(181, 72, 176, 20);
		this.add(email);
				
		emailLabel.setBounds(48, 70, 102, 24);
		this.add(emailLabel);
		
		password.setBounds(181, 98, 176, 20);
		this.add(password);
				
		passwordLabel.setBounds(48, 96, 102, 24);
		this.add(passwordLabel);
				
		passwordConfirm.setBounds(181, 125, 176, 20);
		this.add(passwordConfirm);
				
		passwordConfirmLabel.setBounds(48, 123, 123, 24);
		this.add(passwordConfirmLabel);
		
		registrierenButton.setBounds(251, 154, 106, 23);
		this.add(registrierenButton);
	}

	
	public static void main(String[] args) {
		new Registration();
	}

}
