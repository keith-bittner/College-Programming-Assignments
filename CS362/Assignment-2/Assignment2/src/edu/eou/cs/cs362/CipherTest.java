/**
 * <h1>CipherTest.java</h1>
 *
 * These are the test cases for the implementation of Cipher.java.
 *
 * @author Keith Bittner
 * @version 1.0
 * @since 4/26/2020
 */
package edu.eou.cs.cs362;
import org.junit.Test;
import static org.junit.Assert.*;

public class CipherTest {

    //check to see if encryption works
    @Test
    public void testEncrypt1() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.encrypt("cs380 is awesome", "bagofbones");
        assertEquals("ds380 og fxsfsef", result);
    }

    //check to see if decryption works
    @Test
    public void testDecrypt1() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.decrypt("lbcd uiqvh njohy oygncvh vg", "Puck");
        assertEquals("what fools these mortals be", result);
    }

    //check to see if uppercase is maintained on encryption
    @Test
    public void testEncrypt2() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.encrypt("Cool4Cryptography", "abCD");
        assertEquals("Cpqo4Csastpiuaqjb", result);
    }

    //check to see if uppercase is maintained on decryption
    @Test
    public void testDecrypt2() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.decrypt("Cpqo4Csastpiuaqjb", "abCD");
        assertEquals("Cool4Cryptography", result);
    }

    //check to see if exception thrown on numerical key phrases when encrypting
    @Test (expected = IllegalArgumentException.class)
    public void testEncrypt3() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.encrypt("Star Wars is awesome", "C3PO&r2d2"); }

    //check to see if exception thrown on numerical key phrases when decrypting
    @Test (expected = IllegalArgumentException.class)
    public void testDecrypt3() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.decrypt("The Dark Side sucks", "D@rthV4d3r"); }

    //check to see if exception thrown if non-alphanumerical in message during encryption
    @Test (expected = IllegalArgumentException.class)
    public void testEncrypt4() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.encrypt("Star W4rs is awesome!", "ceethreepeeoh"); }

    //check to see if exception thrown if non-alphanumerical in message during decryption
    @Test (expected = IllegalArgumentException.class)
    public void testDecrypt4() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.decrypt("The Dark Side sucks!", "kyloren");}

    //check to see if exception thrown if key is null encryption
    @Test (expected = StringIndexOutOfBoundsException.class)
    public void testEncrypt5() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.encrypt("Key is null", "");}

    //check to see if exception thrown if key is null on decryption
    @Test (expected = StringIndexOutOfBoundsException.class)
    public void testDecrypt5() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.decrypt("Key is null", "");}

    //check to see if exception thrown if message is null encryption
    @Test
    public void testEncrypt6() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.encrypt("", "myKey");}

    //check to see if exception thrown if message is null on decryption
    @Test
    public void testDecrypt6() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.decrypt("", "myKey");}
}
