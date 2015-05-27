package ch.bbcag.epix.dao;

import java.sql.SQLException;

/**
 * LevelDao
 * @author Chiramet Phong Penglerd, Miguel Jorge || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public interface LevelDao {
	
	
	/**
	 *	holt die ID_Level nach levelName
	 * @param levelName {@link String}
	 * @return ID_Level vom Level
	 * @throws SQLException
	 */
	public abstract int getID_Level(String levelName) throws SQLException;

}
