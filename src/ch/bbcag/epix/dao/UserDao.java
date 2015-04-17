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
	 * @param user
	 * @throws SQLException
	 */
	public abstract void registrieren(Player user) throws SQLException;

	/**
	 * Methode welches ein Objekt {@link Player} erstellt wenn sich der User ein loggt
	 * @author Chiramet Phong Penglerd
	 * @param username
	 * @throws SQLException
	 */
	public abstract Player playerLogin(String username) throws SQLException;
	
}
