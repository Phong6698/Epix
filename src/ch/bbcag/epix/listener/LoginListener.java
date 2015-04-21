package ch.bbcag.epix.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;

import ch.bbcag.epix.popup.LoginFailed;
import ch.bbcag.epix.Main.Game;
import ch.bbcag.epix.Main.GamePanel;
import ch.bbcag.epix.controller.EpixController;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.gamestate.GameState;
import ch.bbcag.epix.gamestate.GameStateManager;
import ch.bbcag.epix.gamestate.Level_1_State;
import ch.bbcag.epix.tilemap.TileMap;
import ch.bbcag.epix.view.EpixView;

public class LoginListener implements ActionListener {

	private JFrame loginView;
	private JTextField username;
	private JPasswordField password;

	public LoginListener(JTextField username, JPasswordField password, JFrame loginView) {
		this.setLoginView(loginView);
		this.setUsername(username);
		this.setPassword(password);
	}

	public void actionPerformed(ActionEvent e) {

		User loginUser = new User();

		String user = this.getUsername().getText();
		String password = this.getPassword().getText();

		loginUser.setPassword(password);
		loginUser.setUsername(user);

		if (EpixController.getInstance().login(loginUser) == true) {
			loginView.dispose();
			User player = EpixController.getInstance().playerLogin(user);
			new EpixView(player);
			
		}

	}

	public JFrame getLoginView() {
		return loginView;
	}

	public void setLoginView(JFrame login2) {
		this.loginView = login2;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

}
