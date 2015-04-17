package ch.bbcag.epix.dao;


import java.sql.SQLException;



import java.util.List;

import ch.bbcag.epix.entity.User;

public interface UserDao {

	/**
	 * Methode um auslesen aller User
	 * @author Elia Perenzin
	 * @return Liste mit allen User
	 * @throws SQLException
	 */
	public abstract List<User> findAllUsers() throws SQLException;
	
	/**
	 * Methode um einen neuen User einzutragen
	 * @author Elia Perenzin
	 * @param User
	 * @throws SQLException
	 */
	public abstract void registrieren(User user) throws SQLException;

}
