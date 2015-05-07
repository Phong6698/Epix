package ch.bbcag.epix.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.bbcag.epix.entity.User;

public class LevelJDBCdao extends Database implements LevelDao{
	
	//Variable fuer Verbindung
	private Connection con = null;

	@Override
	public int getID_Level(String levelName) throws SQLException {
		
		String sql = "SELECT * FROM level WHERE LevelName = ?";
		
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, levelName);
		rs = ps.executeQuery();
		
		int id_Level = 0;

		while (rs.next()) {
			id_Level = rs.getInt("ID_Level");
		}
		closeCon();
		
		return id_Level;
	}

}
