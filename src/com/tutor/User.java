package com.tutor;
import java.security.MessageDigest;
import java.io.*;

public class User{

    private String userName;
    private String passwordHash;
    private String type;
    
    public User(String u, String p, String t){
    	this.userName = u;
    	this.passwordHash = hash(p);    
    	this.type = t;
    }
    
    public String hash(String password){
    	String generatedPassword = null;
    	try{
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		md.update(password.getBytes());
    		byte[] bytes = md.digest();
    		StringBuilder sb = new StringBuilder();
    		for(int i=0; i< bytes.length ;i++){
    			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    		}
    		generatedPassword = sb.toString();
    		return generatedPassword;
	    }
	   catch(Exception e){
		   System.out.println("Something broke.");
		   return null;
	   }
	}

    public boolean userTest(String password){
	  	if (hash(password).equals(this.passwordHash)){
	  		return true;
	  	}
	  	return false;
    }
    
	public void add(String username, String password, String type){
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(Main.location + "/Database.txt", true));
			w.write("\n" + username + "#" + hash(password) + "#" + type);
			w.close();
			Main.passwords.put(username, hash(password)+ '#' + type);
		}
		catch(Exception e){
			System.out.println("Error in add user");
		}

	}
	
	public void remove(String username){
		try{
			File database = new File(Main.location + "/Database.txt");
			File tempdatabase = new File(Main.location + "/TempDatabase.txt");
			BufferedReader r = new BufferedReader(new FileReader(database));
			BufferedWriter w = new BufferedWriter(new FileWriter(tempdatabase));
			
			String currentLine;
			while((currentLine=r.readLine())!= null){
				if (currentLine.contains(username)){
					continue;
				}
				else{
					w.write(currentLine + "\n");
				}
			}
			
			w.close();
			r.close();
			tempdatabase.renameTo(database);
			}
			
		catch(Exception e){
			System.out.println("Error in remove user");
		}
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getUserName() {
		return this.userName;
	}
	
	public String getType(){
		return this.type;
	}
}
