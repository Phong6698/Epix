package ch.bbcag.epix.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ch.bbcag.epix.listener.SignUpListener;
import ch.bbcag.epix.listener.ZurueckListener;

public class Registration extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	/*
	 * JTextField
	 */
	protected JTextField username = new JTextField();
	protected JTextField email = new JTextField();
	
	/*
	 * JPasswordField
	 */
	protected JPasswordField password = new JPasswordField();
	protected JPasswordField passwordConfirm = new JPasswordField();
	
	/*
	 * JButton
	 */
	protected JButton registrierenButton = new JButton("Registrieren");
	protected JButton zurueckButton = new JButton("Zurück");
	
	/*
	 * JLabel
	 */
	protected JLabel titelLabel = new JLabel("Epix Registration");
	protected JLabel usernameLabel = new JLabel("Username");
	protected JLabel emailLabel = new JLabel("E-mail");
	protected JLabel passwordLabel = new JLabel("Passwort");
	protected JLabel passwordConfirmLabel = new JLabel("Passwort best\u00E4tigen");


	public Registration() {
		JFrame registration = new JFrame();
		
		registration.setTitle("Epix");
		registration.setBounds(500, 400, 419, 238);
		registration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registration.getContentPane().setLayout(null);
		registration.setVisible(true);
		registration.setResizable(false);		
		
		
		titelLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titelLabel.setBounds(48, 11, 141, 23);
		registration.getContentPane().add(titelLabel);
				
		usernameLabel.setBounds(48, 45, 102, 24);
		registration.getContentPane().add(usernameLabel);
				
		username.setColumns(10);
		username.setBounds(181, 47, 176, 20);
		registration.add(username);
		
		emailLabel.setBounds(48, 70, 102, 24);
		registration.add(emailLabel);
		
		email.setBounds(181, 72, 176, 20);
		registration.add(email);
					
		passwordLabel.setBounds(48, 96, 102, 24);
		registration.add(passwordLabel);
		
		password.setBounds(181, 98, 176, 20);
		registration.add(password);

		passwordConfirmLabel.setBounds(48, 123, 123, 24);
		registration.add(passwordConfirmLabel);

		passwordConfirm.setBounds(181, 125, 176, 20);
		registration.add(passwordConfirm);
						
		registrierenButton.setBounds(251, 154, 106, 23);
		registrierenButton.addActionListener(new SignUpListener(username, email, password, passwordConfirm, registration));
		registration.add(registrierenButton);
		
		zurueckButton.setBounds(158, 154,  84, 23);
		zurueckButton.addActionListener(new ZurueckListener(registration));
		registration.add(zurueckButton);
	}
}
