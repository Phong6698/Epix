package ch.bbcag.epix.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import ch.bbcag.epix.dao.UserDao;
import ch.bbcag.epix.dao.UserJDBCdao;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.popup.EmailEmpty;
import ch.bbcag.epix.popup.FalsePassword;
import ch.bbcag.epix.popup.LoginFailed;
import ch.bbcag.epix.popup.PasswordEmpty;
import ch.bbcag.epix.popup.Registriert;
import ch.bbcag.epix.popup.UngueltigeEmail;
import ch.bbcag.epix.popup.UsernameEmpty;
import ch.bbcag.epix.popup.UsernameVergeben;
import ch.bbcag.epix.utils.CryptUtils;

public class EpixController {
	private static EpixController instance = new EpixController();
	private final static UserDao USER_DAO = new UserJDBCdao();

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
	 * 
	 */
	public void registrieren(Player newUser) {
		List<Player> dbUsers = null;
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

								for (Player dbUser : dbUsers) {
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

	public boolean login(Player user) {
		List<Player> dbUsers = null;
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

				for (Player dbUser : dbUsers) {
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
	
	public Player playerLogin(String username) {
		try {
			Player player = USER_DAO.playerLogin(username);
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

};
