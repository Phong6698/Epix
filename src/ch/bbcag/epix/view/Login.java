package ch.bbcag.epix.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.glass.events.MouseEvent;
import com.sun.xml.internal.bind.v2.model.core.Adapter;

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
	protected JButton loginbButton = new JButton("Login");

	/*
	 * JLabel
	 */
	protected JLabel usernameLabel = new JLabel("Username");
	protected JLabel passwordLabel = new JLabel("Passwort");	
	protected JLabel titelLabel = new JLabel("Epix Login");


	public Login() {
		this.setTitle("Epix");
		this.setBounds(500, 400, 419, 238);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		
			
		
		titelLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titelLabel.setBounds(48, 11, 141, 23);
		this.add(titelLabel);
		
		usernameLabel.setBounds(48, 45, 102, 24);
		this.add(usernameLabel);

		username.setBounds(160, 47, 197, 20);
		this.add(username);
		username.setColumns(10);
		
		passwordLabel.setBounds(48, 76, 102, 24);
		this.add(passwordLabel);
				
		password.setBounds(160, 78, 197, 20);
		this.add(password);
								
		registrierenButton.setBounds(160, 120, 106, 23);
		registrierenButton.addActionListener(new ActionListener() {
			
			@Override public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Registration();
			}
		});
		this.add(registrierenButton);
				
		loginbButton.setBounds(273, 120, 84, 23);
		this.add(loginbButton);			
	}
	

	public static void main(String[] args) {
		new Login();
	   }
}
