package ch.bbcag.epix.dao;


import java.sql.SQLException;



import java.util.List;

import ch.bbcag.epix.entity.Player;

public interface UserDao {

	/**
	 * Methode um auslesen aller User
	 * @author Elia Perenzin
	 * @return Liste mit allen User
	 * @throws SQLException
	 */
	public abstract List<Player> findAllUsers() throws SQLException;
	
	/**
	 * Methode um einen neuen User einzutragen
	 * @author Elia Perenzin
	 * @param Player
	 * @throws SQLException
	 */
	public abstract void registrieren(Player user) throws SQLException;

}
