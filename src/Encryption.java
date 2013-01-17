
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;

import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public class Encryption
{
	Cipher ecipher;
	Cipher dcipher;

public Encryption(SecretKey key)
{
	// Create an 8-byte initialization vector
	byte[] iv = new byte[] {	0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f	};

AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
try
{
ecipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
dcipher = Cipher.getInstance("AES/CTR/PKCS5Padding");

// CTR requires an initialization vector
ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
}
catch (Exception e)
{
e.printStackTrace();
}
}

// Buffer used to transport the bytes from one stream to another
byte[] buf = new byte[1024];

public void encrypt(InputStream in, OutputStream out)
{
try
{
// Bytes written to out will be encrypted
out = new CipherOutputStream(out, ecipher);

// Read in the cleartext bytes and write to out to encrypt
int numRead = 0;
while ((numRead = in.read(buf)) >= 0)
{
out.write(buf, 0, numRead);
}
out.close();
}
catch (java.io.IOException e)
{
}
}

public void decrypt(InputStream in, OutputStream out)
{
try
{
// Bytes read from in will be decrypted
in = new CipherInputStream(in, dcipher);

// Read in the decrypted bytes and write the cleartext to out
int numRead = 0;
while ((numRead = in.read(buf)) >= 0)
{
out.write(buf, 0, numRead);
}
out.close();
}
catch (java.io.IOException e)
{
}
}

public static void main(String args[])
{
try
{

SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");  
byte[] salt = new byte[16];  
rand.nextBytes(salt);  
PBEKeySpec password = new PBEKeySpec("password".toCharArray(), salt, 1000, 128);  
SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  
PBEKey key1 = (PBEKey) factory.generateSecret(password);  
SecretKey encKey = new SecretKeySpec(key1.getEncoded(), "AES");  

// Create encrypter/decrypter class
Encryption encrypter = new Encryption(encKey);

// Encrypt
encrypter.encrypt(new FileInputStream("01.mp3"),new FileOutputStream("01.mp3"));
// Decrypt
encrypter.decrypt(new FileInputStream("01.mp3"),new FileOutputStream("01.mp3"));
}
catch (Exception e)
{
e.printStackTrace();
}
}
}
