package ch.bbcag.epix.Main;

import javax.swing.JFrame;

public class Game {
	private static JFrame window = new JFrame("Epix");
	private boolean visible;
	
	public static void game() {
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(false);
	}

	public boolean isVisible() {
		return visible;
	}

	public static void setVisible(boolean visible) {
		window.setVisible(visible);
	}
}
