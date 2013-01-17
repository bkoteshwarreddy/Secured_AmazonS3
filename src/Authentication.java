import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class Authentication {
	AmazonS3 s3;
	AWSCredentials credentials;
	public AmazonS3 Authentiication() throws IOException{
	credentials=new PropertiesCredentials(Authentication.class.getResourceAsStream("AwsCredentials.properties"));
	s3 = new AmazonS3Client(credentials);
	
	return s3;
}
}
