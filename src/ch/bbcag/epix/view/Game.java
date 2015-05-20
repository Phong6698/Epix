package ch.bbcag.epix.view;

import javax.swing.JFrame;

import ch.bbcag.epix.entity.User;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Game.java.java Copyright Berufsbildungscenter 2015
 */

public class Game {
	
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("Epix");
//		user.setCoin(0);
		user.setMaxHealth(10000);
		user.setMoveSpeed(3);
		user.setMaxJump(-10);
		user.setDamage(20);
		user.setMoveSpeed(3);
		user.setMaxMoveSpeed(3.2);
		
		GameFrame game = new GameFrame(2, user);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
