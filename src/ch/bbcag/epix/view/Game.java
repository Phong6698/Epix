package ch.bbcag.epix.view;

import javax.swing.JFrame;

import ch.bbcag.epix.entity.User;

public class Game {
	
	public static void main(String[] args) {
		
		User user = new User();
		user.setUsername("Epix");
		user.setCoin(100);
		user.setMaxHealth(100);

		GameFrame game = new GameFrame(1, user);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
