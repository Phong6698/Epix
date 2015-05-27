package ch.bbcag.epix.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Encoder & Decoder
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class CryptUtils {
	private static final String DEFAULT_CODING = "UTF-8";
	private static BASE64Encoder enc = new BASE64Encoder();
	private static BASE64Decoder dec = new BASE64Decoder();

	
	/**
	 * Hier wird das Passwort des Users verschluesselt
	 * @param password {@link String} unverschlüsselt
	 * @return verschluesseltes Passwort
	 */
	public static String base64encode(String password) {
		try {
			// Passwort wird codiert
			return enc.encode(password.getBytes(DEFAULT_CODING));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}


	/**
	 * Hier wird das Passwort des Users zurueck verschluesselt
	 * @param password {@link String} verschlüsselt
	 * @return zurueck verschluesseltes Passwort
	 */
	public static String base64decode(String password) {
		try {
			// Passwort wird decodiert
			return new String(dec.decodeBuffer(password), DEFAULT_CODING);
		} catch (IOException e) {
			return null;
		}
	}
}
