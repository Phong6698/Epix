package ch.bbcag.epix.dao;


import java.sql.SQLException;
import java.util.List;

import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.entity.User;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			UserDao.java.java Copyright Berufsbildungscenter 2015
 */

public interface PlayerDao {

	public abstract List<User> findAllUsers() throws SQLException;
	
	public abstract void registrieren(User user) throws SQLException;

	public abstract User playerLogin(String username) throws SQLException;
	
	public abstract void coinsUpdate(User user, int coins) throws SQLException;
	
}
