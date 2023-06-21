/**
 * <h1>CipherImpl.java</h1>
 *
 * This is the implementation of the Cipher class.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 */
package edu.eou.cs.cs362;

public class CipherImpl implements Cipher {
    /**
     * This method takes a string message and simulates a Vegenere Cipher encryption
     * based on a string key.
     *
     * Will not accept messages that contain non-alphanumerical characters. Will not
     * accept keys that contain numerical characters.
     *
     * @param stringToEncrypt The string message to encrypt.
     * @param key The string key to encrypt with.
     * @return String The encrypted message.
     * @exception IllegalArgumentException On input not being valid.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html">
     * IllegalArgumentException</a>
     */
    public String encrypt(String stringToEncrypt, String key) {

        String encrypted = ""; //encrypted message

        for (int i = 0, j = 0; i < stringToEncrypt.length(); i++) { //begin encryption loop
            if (Character.isLetter(key.charAt(j))) { //make sure no character in the key is numerical
                if (stringToEncrypt.charAt(i) >= 'a' && stringToEncrypt.charAt(i) <= 'z') { //check if characters are between a and z
                    //add the converted character to the encrypted string
                    encrypted = encrypted + (char) (((stringToEncrypt.charAt(i)) + Character.toLowerCase(key.charAt(j)) - 2 * 'a') % 26 + 'a');
                    j = ++j % key.length(); //we are at the end of the key, start back at the beginning
                } else if (stringToEncrypt.charAt(i) >= 'A' && stringToEncrypt.charAt(i) <= 'Z') { //check if characters are between A and Z
                    //add the converted character to the encrypted string
                    encrypted = encrypted + (char) (((stringToEncrypt.charAt(i)) + Character.toUpperCase(key.charAt(j)) - 2 * 'A') % 26 + 'A');
                    j = ++j % key.length(); //we are at the end of the key, start back at the beginning
                } else if (stringToEncrypt.charAt(i) == ' ' || Character.isDigit(stringToEncrypt.charAt(i))) { //skip over spaces and digits
                    //add the space or digit to the encrypted string
                    encrypted = encrypted + (stringToEncrypt.charAt(i));
                } else {
                    //invalid character
                    throw new IllegalArgumentException("Invalid character @ " + stringToEncrypt.charAt(i));
                }
            } else {
                //invalid character
                throw new IllegalArgumentException("Invalid character @ " + stringToEncrypt.charAt(i));
            }
        } //end encryption loop
        return encrypted; //fully encrypted message
    }

    /**
     * This method takes a string message and simulates a decryption of a Vegenere Cipher
     * based on a string key.
     *
     * Will not accept messages that contain non-alphanumerical characters. Will not
     * accept keys that contain numerical characters.
     *
     * @param stringToDecrypt The encrypted message to decrypt.
     * @param key The string key to decrypt with.
     * @return String The decrypted message.
     * @exception IllegalArgumentException On input not being valid.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html">
     * IllegalArgumentException</a>
     */
    public String decrypt(String stringToDecrypt, String key) {

        String decrypted = ""; //message to decrypt

        for (int i = 0, j = 0; i < stringToDecrypt.length(); i++) { //begin decryption loop
            if (Character.isLetter(key.charAt(j))) { //make sure no character in the key is numerical
                if (stringToDecrypt.charAt(i) >= 'a' && stringToDecrypt.charAt(i) <= 'z') { //check if characters are between a and z
                    //add the converted character to the decrypted string
                    decrypted = decrypted + (char) (((stringToDecrypt.charAt(i) - Character.toLowerCase(key.charAt(j)) + 26) % 26) + 'a');
                    j = ++j % key.length(); //we are at the end of the key, start back at the beginning
                } else if (stringToDecrypt.charAt(i) >= 'A' && stringToDecrypt.charAt(i) <= 'Z') { //check if characters are between A and Z
                    //add the converted character to the decrypted string
                    decrypted = decrypted + (char) (((stringToDecrypt.charAt(i) - Character.toUpperCase(key.charAt(j)) + 26) % 26) + 'A');
                    j = ++j % key.length(); //we are at the end of the key, start back at the beginning
                } else if (stringToDecrypt.charAt(i) == ' ' || Character.isDigit(stringToDecrypt.charAt(i))) { //skip over spaces and digits
                    decrypted = decrypted + (stringToDecrypt.charAt(i));
                } else {
                    //invalid character
                    throw new IllegalArgumentException("Invalid character @ " + stringToDecrypt.charAt(i));
                }
            } else {
                //invalid character
                throw new IllegalArgumentException("Invalid character @ " + stringToDecrypt.charAt(i));
            }
        } //end decryption loop
        return decrypted; //fully decrypted message
    }
}