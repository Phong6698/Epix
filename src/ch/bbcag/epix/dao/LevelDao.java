package ch.bbcag.epix.dao;

import java.sql.SQLException;

public interface LevelDao {
	
	public abstract int getID_Level(String levelName) throws SQLException;

}
