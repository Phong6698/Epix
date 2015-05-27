package ch.bbcag.epix.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import ch.bbcag.epix.entity.User;

/**
 * PlayerDAO
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public interface PlayerDao {

	
	/**
	 * Holt alle User aus der Datenbank
	 * @return Liste mit den User
	 * @throws SQLException
	 */
	public abstract List<User> findAllUsers() throws SQLException;
	
	
	/**
	 * Fügt in der Datenbank einen neuen Benutzer ein
	 * @param user {@link User}
	 * @throws SQLException
	 */
	public abstract void registrieren(User user) throws SQLException;

	
	/**
	 * Holt die Daten des Spielers aus der Datenbank
	 * @param username {@link String}
	 * @return Das Objekt User
	 * @throws SQLException
	 */
	public abstract User playerLogin(String username) throws SQLException;
	
	
	/**
	 * Updatet die Coins des Spielers in der Datenbank mit der neuen Coin Anzahl
	 * @param user {@link User}
	 * @param coins neue Coin Anzahl
	 * @throws SQLException
	 */
	public abstract void coinsUpdate(User user, int coins) throws SQLException;
	
	
	/**
	 * Updatet die collecten Coins des Spielers in der Datenbank mit der neuen collecten Coins Anzahl
	 * @param user {@link User}
	 * @param collectedCoins coins neue collectedCoins Anzahl
	 * @throws SQLException
	 */
	public abstract void collectedCoinsUpdate(User user, int collectedCoins) throws SQLException;
	
	
	/**
	 * Spieler Informationen werden von der Datenbank abgerufen
	 * @param user {@link User}
	 * @throws SQLException
	 */
	public abstract void getPlayerStats(User user) throws SQLException;	
	
	
	/**
	 * Eine Liste mit den 10 Spieler die am meisten Münzen haben
	 * @return Eine Liste mit den 10 Spieler die am meisten Münzen haben
	 * @throws SQLException
	 */
	public abstract Vector<?> getRangliste() throws SQLException;
}
