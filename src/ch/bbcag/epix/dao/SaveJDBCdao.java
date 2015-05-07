package ch.bbcag.epix.dao;

import java.sql.Connection;
import java.sql.SQLException;

import ch.bbcag.epix.entity.User;

public class SaveJDBCdao extends Database implements SaveDao{
	
	//Variable fuer Verbindung
	private Connection con = null;

	@Override
	public void save(User user, int level_ID) throws SQLException {
		
		String sql = "INSERT INTO save (User_ID, Level_ID) VALUES ( ?, ?)";
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setInt(2, level_ID);
		ps.executeUpdate();
		closeCon();				
	}

	@Override
	public boolean checkLevelSaved(User user, int level_ID) throws SQLException {
		String sql = "SELECT * FROM save WHERE User_ID = ? AND Level_ID = ?;";
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setInt(2, level_ID);		
		rs = ps.executeQuery();
		while (rs.next()) {
			if(!rs.getBoolean("ID_Save")); {
				return true;
			}
		}
		closeCon();	
		return false;
	}

}
