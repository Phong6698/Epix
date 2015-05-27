package ch.bbcag.epix.dao;

import java.sql.SQLException;

import ch.bbcag.epix.entity.User;

/**
 * SaveDao
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public interface SaveDao {
	
	/**
	 * Speichert das beendete Level
	 * @param user {@link User}
	 * @param level_ID level_ID die ID des beendeten Levels
	 * @throws SQLException
	 */
	public abstract void save(User user, int level_ID) throws SQLException;
	
	
	/**
	 * Ueberprueft ob das Level vom Spieler gemacht wurde
	 * @param user {@link User}
	 * @param level_ID
	 * @return boolean ob das Level vom Spieler schon gemacht wurde
	 * @throws SQLException
	 */
	public abstract boolean checkLevelSaved(User user, int level_ID) throws SQLException;
	
	
	/**
	 * Speichert die Upgrades vom Spieler
	 * @param user {@link User}
	 * @throws SQLException
	 */
	public abstract void saveUpgrades(User user) throws SQLException;
}
