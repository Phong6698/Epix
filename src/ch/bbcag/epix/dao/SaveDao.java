package ch.bbcag.epix.dao;

import java.sql.SQLException;

import ch.bbcag.epix.entity.User;

/**
 * 
 *
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public interface SaveDao {
	
	public abstract void save(User user, int level_ID) throws SQLException;
	
	public abstract boolean checkLevelSaved(User user, int level_ID) throws SQLException;
	
	public abstract void saveUpgrades(User user) throws SQLException;
}
