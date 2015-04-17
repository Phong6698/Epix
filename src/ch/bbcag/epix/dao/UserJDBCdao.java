package ch.bbcag.epix.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ch.bbcag.epix.entity.Player;
import DBC.Database;

public class UserJDBCdao extends Database implements UserDao {
	
	//Variable fuer Verbindung
		private Connection con = null;
		
		/**
		 * Auslesen aller User aus der DB
		 */
		public List<Player> findAllUsers() throws SQLException {
			String sql = "SELECT * FROM player";
			List<Player> p = new ArrayList<Player>();
			
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Player user = new Player();
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				p.add(user);
			}
			closeCon();
			return p;
		}

		/**
		 * Eintragen eines neuen Users in DB
		 */
		public void registrieren(Player user) throws SQLException {
			String sql = "INSERT INTO player (Username, Password, Email, Coins, PlayerWeapon_ID) VALUES (?, ?, ?,0, 1)";
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
			closeCon();
		}

		@Override
		public Player playerLogin(String username) throws SQLException {
			String sql = "SELECT * FROM player WHERE Username = ?;";
			Player player = new Player();
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				player.setUsername(rs.getString("Username"));
				player.setEmail(rs.getString("Email"));
				player.setCoin(rs.getInt("Coins"));
				break;
			}
			closeCon();
			
			
			return player;
		}
}
