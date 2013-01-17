/*This class will generate a salt file if there is no salt file already
 * 
 * 
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 *
 * @author Koteshwar
 */
public final class masterkey {
    
    private String masterPassword;
    private String bucketName;
    private byte[] salt = new byte[8];
    public masterkey(String masterPassword, String bucketName)
    {
        this.masterPassword = masterPassword;
        this.bucketName = bucketName;
        readSalt();
    }
    
    public void readSalt()
    {
        /*
         * Read Salt from S3
         * if Salt is not found, prompt user and generate a new salt file
         */
        try
        {
            // Try reading the salt file from S3        
            S3Object obj = master_password.s3.getObject( new GetObjectRequest(bucketName, "salt") );
            
            //JOptionPane.showMessageDialog(null, "Salt file found");
            
            //Extracting salt
            BufferedReader br = new BufferedReader( new InputStreamReader(new BufferedInputStream(obj.getObjectContent()) ) );
            
            String line;
            int ctr =0;
            
            while ( (line = br.readLine()) != null)
            {
                salt[ctr] = Byte.parseByte(line);
                ctr++;
            }
            
            br.close();
        }
        catch (AmazonServiceException e)
        {
            JOptionPane.showMessageDialog(null, "Salt for Generating MasterKey not found"+"Generating new salt file ","warning", JOptionPane.WARNING_MESSAGE);             
            
            //If salt not found in S3
            generateSalt();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Salt: Other Exception. Message: " + e.getMessage(),
                    "Salt Get", JOptionPane.WARNING_MESSAGE);             
        }
        
    }
    
    private void generateSalt()
    {
        SecureRandom sr = null;
        byte[] bytes = new byte[8];
        PrintWriter write = null;
        
        try
        {
            write = new PrintWriter("newsalt");
            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(bytes);
            
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }

        this.salt = bytes;
                  
        for (int i=0; i<8; i++)
            write.println(bytes[i]);
        
        write.close();
        
        //Upload new salt and delete local file
        master_password.s3.putObject(new PutObjectRequest(bucketName, "salt", new File("newsalt")));
        
        //JOptionPane.showMessageDialog(null, "New salt file created and uploaded."); 
        
        new File("newsalt").delete();
        
        return;        
    }
    
    public SecretKey generateKey()
    {        
        SecretKey MASTERKey = null;
        try
        {        
            PBEKeySpec password = new PBEKeySpec(masterPassword.toCharArray(),salt, 1000, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKey key2 = (PBEKey) factory.generateSecret(password);
            MASTERKey = new SecretKeySpec(key2.getEncoded(), "AES");
            //System.out.println(MASTERKey);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
        return MASTERKey;
    }
}
