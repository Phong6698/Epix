package ch.bbcag.epix.view;

import java.sql.SQLException;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) throws SQLException {

		GameFrame game = new GameFrame(1);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
