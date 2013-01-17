import java.io.*;
import java.util.*;

public class indexsearch {

            Map<String, String> map = new LinkedHashMap<String, String>();


            public void Index(String a,String b) throws Exception 
            {
      
              File filecheck =new File("keywords.ser");
              if(!filecheck.exists())
              {
                FileOutputStream fos = new FileOutputStream("keywords.ser");
                ObjectOutputStream out = new ObjectOutputStream(fos);
                map.put(a,b);
                out.writeObject(map);
                System.out.println("Index Successfully done"+map);
                out.close();
                
            }
                
            else
            {    

      			 FileInputStream fin = new FileInputStream("keywords.ser");
             	 ObjectInputStream in = new ObjectInputStream(fin);
             	 Map<String, String> map = (Map<String, String>) in.readObject();
             	 in.close();
                 File f = new File("keywords.ser");
                 boolean bool=f.delete();
                 
                 System.out.println("New File Created."+bool);
                map.put(a,b);
                FileOutputStream fos = new FileOutputStream("keywords.ser");
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(map);
                System.out.println("Index Successful : "+map);
                out.close();
            }

        }
        
       public ArrayList Search(String str) throws Exception {
    	   
          		 ArrayList ar = new ArrayList();
       			 FileInputStream fin = new FileInputStream("keywords.ser");
             	 ObjectInputStream in = new ObjectInputStream(fin);
             	 Map<String, String> mapresults = (Map<String, String>) in.readObject();
             	 Collection c = mapresults.values();
             	 System.out.println("Search Started : " + mapresults);
       			 Iterator itr = c.iterator();
       			 String addstr,stradd;
       			 int m,i=1;
             		 
       			 while(itr.hasNext())
       			 {
       			 
            		 	addstr=(String)itr.next();
            		 	m= addstr.indexOf(str);
             		 	if(m != -1)
             		 	{
 							System.out.println("strings1 : "+addstr);

             		 		for (String key : mapresults.keySet()) 
             		 		{
         						stradd = mapresults.get(key);
     							System.out.println("strings2 : "+stradd);

         						if(stradd.equals(addstr))
         						{
         							ar.add(key);
         							System.out.println("I Entered : "+key);
         						}
             		 		}				
             		 	 }
       			  }
				return ar;
              }
        
} 