/**
 * <h1>Cipher.java</h1>
 *
 * This is the interface for the Cipher class.  It declares 2 methods, encrypt() and
 * decrypt().
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 */
package edu.eou.cs.cs362;

public interface Cipher {
    public String encrypt(String stringToEncrypt, String key);
    public String decrypt(String stringToDecrypt, String key);
}