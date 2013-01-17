import java.io.File;
import java.util.*;

import javax.crypto.SecretKey;

public class HashMapGen {
	
	public String Hash_key,Hash_secret_key_extract;
	public String Hash_file;
	
	@SuppressWarnings("rawtypes")
	HashMap key_file = new HashMap();
	File hashkey_file = new File("key_file.txt");
	
	@SuppressWarnings("unchecked")
	public HashMapGen(String e_key, String file){
		this.Hash_key=e_key;
		this.Hash_file=file;
		key_file.put(Hash_key, Hash_file);
	}
	
	public String getKey_from_file(String sk){
		this.Hash_secret_key_extract=sk;
		Hash_file=(String) key_file.get(Hash_secret_key_extract);
		return Hash_file;
		
	}

}
