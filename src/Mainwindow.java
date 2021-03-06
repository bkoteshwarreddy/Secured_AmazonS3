/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Mainwindow.java
 *
 * Created on Nov 29, 2011, 5:16:22 PM
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class Mainwindow extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Creates new form Mainwindow */
	AmazonS3 s3;
	Bucket selected;
    List<Bucket> bucket;
    Object bucket_select_name=null;
    String mainwin_master_password,filename=null,key_file,key_file2;
    SecretKey main_secret_key,encryption_secret_key,encrypted_encryption_key,decrypted_encryption_key;
    String e1_key,e_key,d_key,hmac,encrypt_keywords,file_match,user_decrypt_key;
    File FiletoUpload,File_intermediate,filecheck,key,file;
	String[] temp;
    
	public Mainwindow() {
        initComponents();
    }

    public Mainwindow(AmazonS3 s3, Object bucket_object,String mp,SecretKey sk){
    	initComponents();
    	this.s3=s3;
    	this.bucket_select_name =bucket_object;
    	this.mainwin_master_password=mp;
    	this.main_secret_key=sk;
    	    	
    	encrypt_selected_bucket_text.setText(bucket_object.toString());
    	decrypt_selected_bucket_text.setText(bucket_object.toString());
    	search_keyword_text.setToolTipText("Enter 'list-all' to list all objects in bucket:"+bucket_select_name);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        tabbedpane = new javax.swing.JTabbedPane();
        encrypttab = new javax.swing.JPanel();
        encrypt_file_label = new javax.swing.JLabel();
        encrypt_file_text = new javax.swing.JTextField();
        encrypt_file_browse = new javax.swing.JButton();
        encrypt_key_label = new javax.swing.JLabel();
        encrypt_key_text = new javax.swing.JTextField();
        encrypt_and_upload_button = new javax.swing.JButton();
        encrypt_cancel_button = new javax.swing.JButton();
        encrypt_keywords_label = new javax.swing.JLabel();
        encrypt_keywords_text = new javax.swing.JTextField();
        encrypt_back_button = new javax.swing.JButton();
        encrypt_selected_bucket = new javax.swing.JLabel();
        encrypt_selected_bucket_text = new javax.swing.JTextField();
        searchtab = new javax.swing.JPanel();
        search_keyword_label = new javax.swing.JLabel();
        search_keyword_text = new javax.swing.JTextField();
        search_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        search_results_list = new javax.swing.JList();
        search_cancel = new javax.swing.JButton();
        search_back_button = new javax.swing.JButton();
        decrypt_key_label = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        search_results_label = new javax.swing.JLabel();
        decrypt_key_text = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        decrypt_selected_bucket_label = new javax.swing.JLabel();
        decrypt_selected_bucket_text = new javax.swing.JTextField();
        decrypt_and_download_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedpane.setMinimumSize(new java.awt.Dimension(50, 60));

        encrypt_file_label.setText("File :");

        encrypt_file_browse.setText("Browse");
        encrypt_file_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encrypt_file_browseActionPerformed(evt);
            }
        });

        encrypt_key_label.setText("Key :");

        encrypt_key_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encrypt_key_textActionPerformed(evt);
            }
        });

        encrypt_and_upload_button.setText("Encrypt and Upload");
        encrypt_and_upload_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encrypt_and_upload_buttonActionPerformed(evt);
            }
        });

        encrypt_cancel_button.setText("Cancel");
        encrypt_cancel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encrypt_cancel_buttonActionPerformed(evt);
            }
        });

        encrypt_keywords_label.setText("Keywords :");

        encrypt_back_button.setText("Back");
        encrypt_back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encrypt_back_buttonActionPerformed(evt);
            }
        });

        encrypt_selected_bucket.setText("Selected Bucket :");

        javax.swing.GroupLayout encrypttabLayout = new javax.swing.GroupLayout(encrypttab);
        encrypttab.setLayout(encrypttabLayout);
        encrypttabLayout.setHorizontalGroup(
            encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encrypttabLayout.createSequentialGroup()
                .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(encrypttabLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(encrypt_and_upload_button, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, encrypttabLayout.createSequentialGroup()
                        .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(encrypttabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(encrypt_selected_bucket))
                            .addGroup(encrypttabLayout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(encrypt_file_label, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                            .addGroup(encrypttabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(encrypt_key_label))
                            .addGroup(encrypttabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(encrypt_keywords_label)))
                        .addGap(13, 13, 13)))
                .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(encrypttabLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(encrypt_selected_bucket_text, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(encrypt_keywords_text, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(encrypt_file_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(encrypt_key_text, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encrypt_file_browse))
                    .addGroup(encrypttabLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(encrypt_back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(encrypt_cancel_button, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1512, 1512, 1512))
        );
        encrypttabLayout.setVerticalGroup(
            encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encrypttabLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encrypt_selected_bucket_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encrypt_selected_bucket, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encrypt_file_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encrypt_file_browse)
                    .addComponent(encrypt_file_label, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encrypt_key_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encrypt_key_label, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encrypt_keywords_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encrypt_keywords_label))
                .addGap(30, 30, 30)
                .addGroup(encrypttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encrypt_and_upload_button, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encrypt_back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encrypt_cancel_button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        tabbedpane.addTab("Encrypt", encrypttab);

        search_keyword_label.setText("Keyword :");

        search_button.setText("Search");
        search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_buttonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(search_results_list);

        search_cancel.setText("Cancel");
        search_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_cancelActionPerformed(evt);
            }
        });

        search_back_button.setText("Back");
        search_back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_back_buttonActionPerformed(evt);
            }
        });

        decrypt_key_label.setText("Key :");

        search_results_label.setText("Search Results:");

        decrypt_selected_bucket_label.setText("Selected Bucket:");

        decrypt_and_download_button.setText("Decrypt and Download");
        decrypt_and_download_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decrypt_and_download_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchtabLayout = new javax.swing.GroupLayout(searchtab);
        searchtab.setLayout(searchtabLayout);
        searchtabLayout.setHorizontalGroup(
            searchtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchtabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchtabLayout.createSequentialGroup()
                        .addGroup(searchtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchtabLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(search_keyword_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(search_keyword_text, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchtabLayout.createSequentialGroup()
                                .addComponent(decrypt_selected_bucket_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(decrypt_selected_bucket_text, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchtabLayout.createSequentialGroup()
                                .addComponent(decrypt_and_download_button, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search_back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(search_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchtabLayout.createSequentialGroup()
                                .addComponent(decrypt_key_label)
                                .addGap(30, 30, 30)
                                .addComponent(decrypt_key_text, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                        .addGap(162, 162, 162))
                    .addGroup(searchtabLayout.createSequentialGroup()
                        .addComponent(search_results_label)
                        .addContainerGap(483, Short.MAX_VALUE))))
        );
        searchtabLayout.setVerticalGroup(
            searchtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchtabLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(searchtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decrypt_selected_bucket_label)
                    .addComponent(decrypt_selected_bucket_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_button)
                    .addComponent(search_keyword_label)
                    .addComponent(search_keyword_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(search_results_label)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(decrypt_key_label)
                    .addComponent(decrypt_key_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(searchtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decrypt_and_download_button)
                    .addComponent(search_back_button)
                    .addComponent(search_cancel))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tabbedpane.addTab("Search and Decrypt", searchtab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedpane, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

private void decrypt_and_download_buttonActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
	try {
	Master_key_encryption mkd = new Master_key_encryption();
	d_key=mkd.decrypt(e_key, main_secret_key);
		
	user_decrypt_key=decrypt_key_text.getText();
	
	if(user_decrypt_key.contentEquals(d_key)){
	
	//HashMapGen keymap=new HashMapGen(e_key,filename);
	//file_match=keymap.getKey_from_file(e_key);
	
	S3Object obj = s3.getObject(new GetObjectRequest(bucket_select_name.toString(), (String) search_results_list.getSelectedValue()));
	
	int size = Integer.MIN_VALUE;
    File newfilename=new File("new"+filename);    

	FileOutputStream fos = new FileOutputStream(newfilename);
    byte [] buf = new byte[512];
    
    while ( (size=obj.getObjectContent().read(buf)) > 0)
    {
        fos.write(buf,0,size);
    }
	
    KeyGen kr = new KeyGen();
    decrypted_encryption_key=kr.keyGen(d_key);
    Encryption enc =new Encryption(decrypted_encryption_key);
    enc.decrypt(new FileInputStream(newfilename), new FileOutputStream(filename));
    fos.close();
    
    JOptionPane.showMessageDialog(this, "File saved in project folder.");
    
    decrypt_key_text.setText("");
	search_results_list.removeAll();
	search_keyword_text.setText("");
    }
	else{
        JOptionPane.showMessageDialog(null, "Incorrect decryption key","Error",JOptionPane.ERROR_MESSAGE);
	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

private void search_back_buttonActionPerformed(java.awt.event.ActionEvent evt) {
	new buckets(s3,mainwin_master_password).setVisible(true);
  	this.setVisible(false);
}

private void search_cancelActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
	System.exit(0);
}

private void search_buttonActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
	 try
     {
	String keywords = search_keyword_text.getText();
	ObjectListing objects_list=null;
	if ( keywords.equalsIgnoreCase("list-all"))
    {
       
            objects_list = s3.listObjects(bucket_select_name.toString());
            List<S3ObjectSummary> object_summary = objects_list.getObjectSummaries();
            Iterator<S3ObjectSummary> it = object_summary.iterator();
            DefaultListModel listModel = new DefaultListModel();
            search_results_list.setModel(listModel);
            
            while (it.hasNext())
            {
                listModel.addElement(it.next().getKey());
            }
            
            search_results_list.setModel(listModel);
            System.out.println("List All Called : "+listModel);

    }
    else
    {	
    	//mohans
    	File filecheck =new File("keywords.ser");
       //Check if File already exists
       if(!filecheck.exists())
       {
    	   
       }
       else{
    	   indexsearch is =new indexsearch();
    	   System.out.println("Search IT : "+keywords);
    	   ArrayList<String> alist = (ArrayList) is.Search(keywords);
           DefaultListModel listModel1 = null;
    	   for (String s : alist)
    	   {
    	    listModel1 = new DefaultListModel();
			objects_list = s3.listObjects(new ListObjectsRequest().withBucketName(bucket_select_name.toString()).withPrefix(s));
            List<S3ObjectSummary> object_summary = objects_list.getObjectSummaries();
            Iterator<S3ObjectSummary> it = object_summary.iterator();
            //search_results_list.setModel(listModel1);
            System.out.println("Entered 111"+s+"--"+alist);
            while (it.hasNext())
            {
                listModel1.addElement(it.next().getKey());
            }
    	   }
           search_results_list.setModel(listModel1);

         }
       }
     }
     catch (Exception e)
     {
         JOptionPane.showMessageDialog(this, "Error listing objects." + "\n\nAmazon says: " + e.getMessage(),
                 "Search Error", JOptionPane.WARNING_MESSAGE); 
     }
     
}

private void encrypt_back_buttonActionPerformed(java.awt.event.ActionEvent evt) {
	new buckets(s3,mainwin_master_password).setVisible(true);
  	this.setVisible(false);
}

private void encrypt_cancel_buttonActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
	System.exit(0);
}

private void encrypt_and_upload_buttonActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
	try{
		if (encrypt_file_text.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Select a file to upload.","Error", JOptionPane.ERROR_MESSAGE); 
			}
		else if (encrypt_key_text.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Enter a key to upload.","Error", JOptionPane.ERROR_MESSAGE); 
			}
		else{
			if (encrypt_keywords_text.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "No user Keywords are created \nfor this File. Keywords are created \nby application and file is uploaded","Error", JOptionPane.WARNING_MESSAGE); 
				}
			File_intermediate = new File(encrypt_file_text.getText());
			e1_key=encrypt_key_text.getText();
			
			KeyGen kr = new KeyGen();
			String enskey = encrypt_key_text.getText();
			encryption_secret_key=kr.keyGen(enskey);
			Encryption enc =new Encryption(encryption_secret_key);
			
	        enc.encrypt(new FileInputStream(File_intermediate), new FileOutputStream("encryptedfile.txt"));
	        
	        FiletoUpload = new File("encryptedfile.txt");
	        
			Master_key_encryption mke = new Master_key_encryption();
			e_key=mke.encrypt(enskey, main_secret_key);
		        			
			s3.putObject(new PutObjectRequest(bucket_select_name.toString(), filename, FiletoUpload));
			FiletoUpload.delete();
           
			//check for keywords file , if exists append keywords to keywords file. otherwise create a new keywords file and append the keywords.
			filecheck =new File("keywords.txt");
			//mohan
			FileWriter fstream = new FileWriter(filecheck.getName(),true);
			BufferedWriter out = new BufferedWriter(fstream);
			indexsearch is = new indexsearch();
			encrypt_keywords=encrypt_keywords_text.getText();

			if(encrypt_file_text.getText().endsWith(".txt"))
			{
				out.write(encrypt_keywords);
				out.newLine();	
				Stemmer st = new Stemmer();
				List<String> arr = st.doStopStemmer(encrypt_file_text.getText());
			    String con_string = "";
			    for (String s: arr)
			       {	con_string+=s;
			    	   	out.write(s);
			    	   	out.newLine();
			       }
			    System.out.println("Stemmer Index : " +File_intermediate.getName() + con_string+".");
		    	is.Index(File_intermediate.getName(), con_string);
				out.close();
			 }
			
			else{
				System.out.println("Index IT: Filename:"+File_intermediate.getName()+" Keywords:"+encrypt_keywords);
				is.Index(File_intermediate.getName(),encrypt_keywords);
			}
        	}
		JOptionPane.showMessageDialog(this, "File uploaded.");
        
        encrypt_file_text.setText("");
        encrypt_key_text.setText("");
        encrypt_keywords_text.setText("");
        
             File_intermediate.delete();
		}
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error uploading file. Message: " + e.getMessage(),"Error", JOptionPane.WARNING_MESSAGE); 
        	}
	}

private void encrypt_key_textActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
}

private void encrypt_file_browseActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
     
	JFileChooser fc = new JFileChooser();
    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
        file = fc.getSelectedFile();        
        try
        {
        	encrypt_file_text.setText(file.getAbsolutePath());
                filename=file.getName();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Unable to open/read properties file. " + "Message: " + e.getMessage(),
                    "File Error", JOptionPane.WARNING_MESSAGE);                    
        }
    }
}

    
    // Variables declaration - do not modify
    private javax.swing.JButton decrypt_and_download_button;
    private javax.swing.JLabel decrypt_key_label;
    private javax.swing.JTextField decrypt_key_text;
    private javax.swing.JLabel decrypt_selected_bucket_label;
    private javax.swing.JTextField decrypt_selected_bucket_text;
    private javax.swing.JButton encrypt_and_upload_button;
    private javax.swing.JButton encrypt_back_button;
    private javax.swing.JButton encrypt_cancel_button;
    private javax.swing.JButton encrypt_file_browse;
    private javax.swing.JLabel encrypt_file_label;
    private javax.swing.JTextField encrypt_file_text;
    private javax.swing.JLabel encrypt_key_label;
    private javax.swing.JTextField encrypt_key_text;
    private javax.swing.JLabel encrypt_keywords_label;
    private javax.swing.JTextField encrypt_keywords_text;
    private javax.swing.JLabel encrypt_selected_bucket;
    private javax.swing.JTextField encrypt_selected_bucket_text;
    private javax.swing.JPanel encrypttab;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton search_back_button;
    private javax.swing.JButton search_button;
    private javax.swing.JButton search_cancel;
    private javax.swing.JLabel search_keyword_label;
    private javax.swing.JTextField search_keyword_text;
    private javax.swing.JLabel search_results_label;
    private javax.swing.JList search_results_list;
    private javax.swing.JPanel searchtab;
    private javax.swing.JTabbedPane tabbedpane;
    
    // End of variables declaration
}
