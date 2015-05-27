package ch.bbcag.epix.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Datenbank Verbindung
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class Database {
	private Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	
	/**
	 * Verbindungsinforamtionen fuer die DB
	 * @return Connection
	 * @throws SQLException
	 */
	protected Connection getCon() throws SQLException {
		String filename = "data.csv"; //File mit allen Informationen
		File file = new File(filename);
		String [] database = new String[3];
		
		//Liest alle Informationen aus dem CSV File und speichert sie in eine Liste
		try{
			Scanner inputStream = new Scanner(file);
			int i = 0;
			while (inputStream.hasNext()){
				String data = inputStream.next();
				database[i] = data;
				i++;
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File nicht gefunden");
		}
		
		String db = database[0];
		String user = database[1];
		String pw = database[2];
		
		//Connection aufbauen
		setCon(DriverManager.getConnection(db, user, pw));
		return con;
	}
	
	/**
	 * Setter fuer Connection
	 * @param con {@link Connection}
	 */
	private void setCon(Connection con) {
		this.con = con;
	}
	
	/**
	 * Methode fuer schliessung der DB Verbindung
	 * @throws SQLException
	 */
	protected void closeCon() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
				con = null;
			}
		}
		catch (SQLException e) {

		}
	}

	
}
