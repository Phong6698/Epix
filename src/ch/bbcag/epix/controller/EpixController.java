package ch.bbcag.epix.controller;

import java.util.List;
import java.util.regex.Pattern;
import java.sql.SQLException;

import ch.bbcag.epix.dao.UserDao;
import ch.bbcag.epix.dao.UserJDBCdao;
import ch.bbcag.epix.utils.CryptUtils;
import User.User;

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
	 * @author Elia Perenzin
	 * @param User
	 */
	public void registrieren (User newUser){
		List<User> dbUsers = null;
		boolean userAlreadyExists = true;
		final Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		
		if(newUser.getPassword().isEmpty()){
			System.out.println("Passwort muss ausgef\u00fcllt sein");
		}
		else{
			newUser.setPassword(CryptUtils.base64encode(newUser.getPassword()));
			newUser.setPasswordConfirm(CryptUtils.base64encode(newUser.getPasswordConfirm()));
			
			if(newUser.getPassword().equals(newUser.getPasswordConfirm())){
				if(newUser.getUsername().isEmpty()){
					System.out.println("Username muss ausgef\u00fcllt sein");
				}
				else{
					if(newUser.getEmail().isEmpty()){
						System.out.println("Email muss ausgef\u00fcllt sein");
					}
					else{
						try {
							dbUsers = USER_DAO.findAllUsers();
							
							for (User dbUser : dbUsers){
								if (newUser.getUsername().equals(dbUser.getUsername())){
									System.out.println("Username ist bereits vergeben");
									break;
								}
								else{
									userAlreadyExists = false;
								}
								
								if (userAlreadyExists == false){
								USER_DAO.registrieren(newUser);
								System.out.println("Sie wurden erfolgreich eingetragen");
								break;
								}
							}
						}
						catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
			else{
				System.out.println("Passw\u00f6rter stimmen nicht \u00fcberein");
			}
		}
	}

	public boolean login(User user) {
		List<User> dbUsers = null;
		boolean login = false;

		if (user.getUsername().isEmpty()) {
			System.out.println("Bitte Username ausf\u00fcllen");
		} else {
			if (user.getPassword().isEmpty()) {
				System.out.println("Bitte Passwort ausf\u00fcllen");
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
							System.out.println("Sie haben sich erfolgreich angemeldet");
							login = true;
						} else {
							System.out.println("Username und/oder Passwort stimmen nicht");
						}
					} else {
						System.out.println("Username und/oder Passwort stimmen nicht");
					}
				}
			}
		}
		return login;
	}

}
