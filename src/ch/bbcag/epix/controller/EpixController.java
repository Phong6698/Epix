package ch.bbcag.epix.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;

import ch.bbcag.epix.dao.LevelDao;
import ch.bbcag.epix.dao.LevelJDBCdao;
import ch.bbcag.epix.dao.PlayerDao;
import ch.bbcag.epix.dao.PlayerJDBCdao;
import ch.bbcag.epix.dao.SaveDao;
import ch.bbcag.epix.dao.SaveJDBCdao;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.popup.EmailEmpty;
import ch.bbcag.epix.popup.FalsePassword;
import ch.bbcag.epix.popup.LoginFailed;
import ch.bbcag.epix.popup.PasswordEmpty;
import ch.bbcag.epix.popup.Registriert;
import ch.bbcag.epix.popup.UngueltigeEmail;
import ch.bbcag.epix.popup.UsernameEmpty;
import ch.bbcag.epix.popup.UsernameVergeben;
import ch.bbcag.epix.utils.CryptUtils;

/**
 * Epix Controller
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *         Copyright Berufsbildungscenter 2015
 */

public class EpixController {
	private static EpixController instance = new EpixController();
	private final static PlayerDao USER_DAO = new PlayerJDBCdao();
	private final static SaveDao SAVE_DAO = new SaveJDBCdao();
	private final static LevelDao LEVEL_DAO = new LevelJDBCdao();

	/**
	 * Konstruktor der Klasse GMCController nur Privat
	 */
	private EpixController() {
	}

	public static EpixController getInstance() {
		return EpixController.instance;
	}

	/**
	 * Hier wird ueberprueft ob die eingaben des Users gueltig sind und wenn
	 * dies zutrifft wird er in die DB eingetragen
	 * @param newUser {@link User}
	 */
	public void registrieren(User newUser) {
		List<User> dbUsers = null;
		boolean userAlreadyExists = true;
		final Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

		if (newUser.getPassword().isEmpty()) {
			new PasswordEmpty();
		} else {
			newUser.setPassword(CryptUtils.base64encode(newUser.getPassword()));
			newUser.setPasswordConfirm(CryptUtils.base64encode(newUser.getPasswordConfirm()));

			if (newUser.getPassword().equals(newUser.getPasswordConfirm())) {
				if (newUser.getUsername().isEmpty()) {
					new UsernameEmpty();
				} else {
					if (newUser.getEmail().isEmpty()) {
						new EmailEmpty();
					} else {
						if (!pattern.matcher(newUser.getEmail()).matches()) {
							new UngueltigeEmail();
						} else {
							try {
								dbUsers = USER_DAO.findAllUsers();

								for (User dbUser : dbUsers) {
									if (newUser.getUsername().equals(dbUser.getUsername())) {
										new UsernameVergeben();
										break;
									} else {
										userAlreadyExists = false;
									}

									if (userAlreadyExists == false) {
										USER_DAO.registrieren(newUser);
										new Registriert();
										break;
									}
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				new FalsePassword();
			}
		}
	}

	
	/**
	 * Ueberprüft das Login
	 * @param user {@link User}
	 * @return ob login erfolgreich ist
	 */
	public boolean login(User user) {
		List<User> dbUsers = null;
		boolean login = false;
		if (user.getUsername().isEmpty()) {
			new UsernameEmpty();
		} else {
			if (user.getPassword().isEmpty()) {
				new PasswordEmpty();
			} else {
				user.setPassword(CryptUtils.base64encode(user.getPassword()));

				try {
					dbUsers = USER_DAO.findAllUsers();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				for (User dbUser : dbUsers) {
					if (user.getUsername().equals(dbUser.getUsername())) {
						if (user.getPassword().equals(dbUser.getPassword())) {
							login = true;
						}
					}
				}
				if (login == false) {
					new LoginFailed();
				}
			}
		}
		System.out.println(login);
		return login;
	}

	
	/**
	 * Holt die Daten des Spielers aus der Datenbank
	 * @param username {@link String}
	 * @return Das Objekt User
	 */
	public User playerLogin(String username) {
		try {
			User player = USER_DAO.playerLogin(username);
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * Updatet die Coins des Spielers in der Datenbank mit der neuen Coin Anzahl
	 * @param user {@link User} 
	 * @param coins neue Coin Anzahl
	 */
	public void coinsUpdate(User user, int coins) {
		try {
			USER_DAO.coinsUpdate(user, coins);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Updatet die collecten Coins des Spielers in der Datenbank mit der neuen collecten Coins Anzahl
	 * @param user {@link User} 
	 * @param collectedCoins coins neue collectedCoins Anzahl
	 */
	public void collectedCoinsUpdate(User user, int collectedCoins) {
		try {
			USER_DAO.collectedCoinsUpdate(user, collectedCoins);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Speichert das beendete Level
	 * @param user {@link User} 
	 * @param level_ID die ID des beendeten Levels
	 */
	public void save(User user, int level_ID) {
		try {
			SAVE_DAO.save(user, level_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Speichert die Upgrade des Spielers
	 * @param user {@link User} 
	 */
	public void saveUpgrades(User user) {
		try {
			SAVE_DAO.saveUpgrades(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * holt die ID_Level nach levelName
	 * @param levelName {@link String} 
	 * @return ID_Level vom Level
	 */
	public int getID_Level(String levelName) {
		int id_Level = 0;
		try {
			id_Level = LEVEL_DAO.getID_Level(levelName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_Level;
	}

	
	/**
	 * Ueberprueft ob das Level vom Spieler gemacht wurde und verwaltet die Freischaltung des Levels
	 * @param user {@link User} 
	 * @param levelButton {@link JButton} 
	 * @param preLevelButton {@link JButton} 
	 */
	public void checkLevelSaved(User user, JButton levelButton, JButton preLevelButton) {
		int id_Level;
		int preID_Level;
		try {
			id_Level = LEVEL_DAO.getID_Level(levelButton.getText());
			preID_Level = LEVEL_DAO.getID_Level(preLevelButton.getText());
			if (!SAVE_DAO.checkLevelSaved(user, id_Level) && SAVE_DAO.checkLevelSaved(user, preID_Level)) {
				levelButton.setEnabled(true);
			} else if (!SAVE_DAO.checkLevelSaved(user, id_Level) && !SAVE_DAO.checkLevelSaved(user, preID_Level)){
				levelButton.setEnabled(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Spieler Informationen werden von der Datenbank abgerufen
	 * @param user {@link User}
	 */
	public void getPlayerStats(User user){
		try {
			USER_DAO.getPlayerStats(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Eine Liste mit den 10 Spieler die am meisten Münzen haben
	 * @return Eine Liste mit den 10 Spieler die am meisten Münzen haben
	 */
	public Vector<?> getRangliste() {
		try {
			return USER_DAO.getRangliste();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
};
