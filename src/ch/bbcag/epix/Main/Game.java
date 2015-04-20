package ch.bbcag.epix.Main;

import java.sql.SQLException;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) throws SQLException {	
		
		JFrame window = new JFrame("Epix");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}
