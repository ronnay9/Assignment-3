

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		for (int i=0; i<plainText.length(); i++)
		{
			char c = plainText.charAt(i);
			if (c < LOWER_BOUND || c > UPPER_BOUND)
			{
				return false;
			}
		}
		return true;
	}
	
	// Adding an integer to a character, the addition cycles between the LOWER_BOUND and the UPPER_BOUND
	// This method is used for both Caesar and Bellaso cipher
	public static char add(char c, int key)
	{
		int n = c - (int) LOWER_BOUND;
		n += key;
		n = n % 64;
		if (n < 0)
		{
			n += 64;
		}
		n += LOWER_BOUND;
		char encoded = (char) (n);
		return encoded;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encrypted = "";
		for (int i=0; i<plainText.length(); i++)
		{
			char c = plainText.charAt(i);
			char encoded = add(c, key);
			encrypted += encoded;
		}
		return encrypted;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String encrypted = "";
		int l = bellasoStr.length();
		int bellasoPointer = 0;
		for (int i=0; i<plainText.length(); i++)
		{
			char c = plainText.charAt(i);
			char encoded = add(c, (int) bellasoStr.charAt(bellasoPointer));
			encrypted += encoded;
			bellasoPointer++;
			if (bellasoPointer == l)
			{
				bellasoPointer = 0;
			}
		}
		return encrypted;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		return encryptCaesar(encryptedText, -key);
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String decrypted = "";
		int l = bellasoStr.length();
		int bellasoPointer = 0;
		for (int i=0; i<encryptedText.length(); i++)
		{
			char c = encryptedText.charAt(i);
			char encoded = add(c, - (int) bellasoStr.charAt(bellasoPointer));
			decrypted += encoded;
			bellasoPointer++;
			if (bellasoPointer == l)
			{
				bellasoPointer = 0;
			}
		}
		return decrypted;
	}
}