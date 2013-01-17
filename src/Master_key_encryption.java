/*
 * This class encrypts the user given key
 * 
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Master_key_encryption {
    
    private  final  String ALGO = "AES";
     SecretKey master_enc_key,master_dec_key;
    
    @SuppressWarnings("restriction")
    public String encrypt(String Data, SecretKey sk) throws Exception {
    	this.master_enc_key=sk;
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, master_enc_key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    @SuppressWarnings("restriction")
	public String decrypt(String encryptedData,SecretKey sk) throws Exception {
        this.master_dec_key=sk;
    	Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, master_dec_key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
}
