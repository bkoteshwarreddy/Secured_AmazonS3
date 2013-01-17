/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author Ramji
 */
public class StopWords {
    
    public Hashtable getStopTable(String file1, String file2)
    {
         Hashtable stopWordsHash = new Hashtable();
        
        // Reading stop words file
	
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(file1));
            
            String[] stopWords = br.readLine().split(",");
            for (String s:stopWords)
            {
                stopWordsHash.put(s, s);
            }    
            
            br = new BufferedReader(new FileReader(file2));
            
            String line;
            while ( (line = br.readLine()) != null)
            {
                stopWordsHash.put(line, line);
            }
        
            //System.out.println(stopWordsHash.keySet().toString());
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return stopWordsHash;
    }
    
}
