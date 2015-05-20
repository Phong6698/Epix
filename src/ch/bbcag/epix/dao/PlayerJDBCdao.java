package ch.bbcag.epix.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ch.bbcag.epix.entity.User;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			UserJDBCdao.java.java Copyright Berufsbildungscenter 2015
 */

public class PlayerJDBCdao extends Database implements PlayerDao {
	
	//Variable fuer Verbindung
		private Connection con = null;
		
		/**
		 * Auslesen aller User aus der DB
		 */
		public List<User> findAllUsers() throws SQLException {
			String sql = "SELECT * FROM player";
			List<User> p = new ArrayList<User>();
			
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
 
			while (rs.next()) {
				User user = new User();
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
		public void registrieren(User user) throws SQLException {
			String sql = "INSERT INTO player (Username, Password, Email, Coins, PlayerWeapon_ID, Health, Jump, Damage, Speed, MaxSpeed, CollectedCoins) VALUES (?, ?, ?,0, 1, 50, -6.5 ,5 ,0.2,3.2,0)";
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());	
			ps.executeUpdate();
			closeCon();
		}

		@Override
		public User playerLogin(String username) throws SQLException {
			String sql = "SELECT * FROM player WHERE Username = ?;";
			User player = new User();
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				player.setUsername(rs.getString("Username"));
				player.setEmail(rs.getString("Email"));
				player.setCoin(rs.getInt("Coins"));
				player.setCollectedCoin(rs.getInt("CollectedCoins"));
				break;
			}
			closeCon();
			
			return player;
		}
		
		public void coinsUpdate(User player, int coins) throws SQLException{
			String sql = "UPDATE player SET coins = ? WHERE Username = ? ;";
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, coins);
			ps.setString(2, player.getUsername());
			ps.executeUpdate();
			closeCon();
		}
		
		public void collectedCoinsUpdate(User player, int collectedCoins) throws SQLException {
			String sql = "UPDATE player SET CollectedCoins = ? WHERE Username = ? ;";
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, collectedCoins);
			ps.setString(2, player.getUsername());
			ps.executeUpdate();
			closeCon();			
		}

		public void getPlayerStats(User user) throws SQLException {
			String sql = "SELECT * FROM player Where Username = ?";
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				user.setMaxHealth(rs.getInt("Health"));
				user.setMaxJump(rs.getDouble("Jump"));
				user.setDamage(rs.getInt("Damage"));
				user.setMoveSpeed(rs.getDouble("Speed"));
				user.setMaxMoveSpeed(rs.getDouble("MaxSpeed"));
				break;
			}
			closeCon();
		}

		@Override
		public Vector getRangliste() throws SQLException {
			String sql = "SELECT * FROM player ORDER BY CollectedCoins DESC;";
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Vector<Vector> data = new Vector<Vector>();
			int i = 0;
			while(rs.next() && i < 10) {
				Vector<Object> row = new Vector<Object>();
				row.addElement(rs.getString("Username"));
				row.addElement(rs.getInt("CollectedCoins"));				
				data.addElement(row);
				i = i + 1;
			};
			return data;
		}



}
