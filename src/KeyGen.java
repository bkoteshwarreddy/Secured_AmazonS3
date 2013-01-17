// KeyRead Class to to generate secret key from user given password.

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class KeyGen{
	public SecretKey keyGen(String keypwd) {
        SecretKey decKey2 = null;
        try {

            byte[] salt1 = { (byte) 0xc7, (byte) 0x73, (byte) 0x21,
                    (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee,
                    (byte) 0x99 };

          //  System.out.println(keypwd);
            PBEKeySpec password = new PBEKeySpec(keypwd.toCharArray(),salt1, 1000, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKey key2 = (PBEKey) factory.generateSecret(password);
            decKey2 = new SecretKeySpec(key2.getEncoded(), "AES");
        } catch (Exception e) {
            System.err.println("Error reading file: " + e);
        }
        return decKey2;
    }

}
