package ch.bbcag.epix.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import User.User;
import DBC.Database;

public class UserJDBCdao extends Database implements UserDao {
	
	//Variable fuer Verbindung
		private Connection con = null;
		
		/**
		 * Auslesen aller User aus der DB
		 */
		public List<User> findAllUsers() throws SQLException {
			String sql = "SELECT * FROM USER";
			List<User> p = new ArrayList<User>();
			
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("ID_User"));
				user.setPassword(rs.getString("Password"));
				p.add(user);
			}
			closeCon();
			return p;
		}

		/**
		 * Eintragen eines neuen Users in DB
		 */
		public void registrieren(User user) throws SQLException {
			String sql = "INSERT INTO USER (Username, Email, Password, Coins, MaxHealth, MaxJump, MoveSpeed, PlayerWeapon_ID) VALUES (?, ?, ?,0,0,0,0,1)";
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			closeCon();
		}

}
