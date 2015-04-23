package ch.bbcag.epix.view;

import java.sql.SQLException;

public class Game {

	
	public static void main(String[] args) throws SQLException {

		GameFrame game = new GameFrame(1);
		game.setDefaultCloseOperation(game.EXIT_ON_CLOSE);
		
	}

}
