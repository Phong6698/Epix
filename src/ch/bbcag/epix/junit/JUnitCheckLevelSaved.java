package ch.bbcag.epix.junit;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ch.bbcag.epix.dao.SaveDao;
import ch.bbcag.epix.dao.SaveJDBCdao;
import ch.bbcag.epix.entity.User;



@RunWith(Parameterized.class)
public class JUnitCheckLevelSaved {
		private static SaveDao SAVE_DAO;
		private static User user;
		
		private String username;
		private int level_ID;
		private boolean result;

		//Setzt alle jedes mal die Werte aus der Liste in das Objekt user
		public JUnitCheckLevelSaved(String username, int level_ID, boolean result) {
			this.username = username;
			this.level_ID = level_ID;
			this.result = result;
			
		}

		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			JUnitCheckLevelSaved.user = new User(); //Erstellt neuer User
			JUnitCheckLevelSaved.SAVE_DAO = new SaveJDBCdao();
			
		}

		@AfterClass
		public static void tearDownAfterClass() throws Exception {
			JUnitCheckLevelSaved.user = null; //Loescht akteueller user nach dem test
			JUnitCheckLevelSaved.SAVE_DAO = null;
		}

		//Daten fuer den Test
		@Parameters
		public static Collection<Object[]> values() {
			return Arrays.asList(new Object[][] { 
					{ "1", 0, true },
					{ "1", 1, true }, 
					{ "1", 2, true },
					{ "1", 3, false }, 
					{ "test", 0, false },
					{ "test", 2, false } });
		}

		@Test
		public void testCheckLevelSaved() {
			user.setUsername(username);
			
			try {
				Assert.assertEquals(SAVE_DAO.checkLevelSaved(user, level_ID), result);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	

}
