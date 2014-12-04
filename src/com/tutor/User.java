package com.tutor;
import java.security.MessageDigest;
import java.io.*;

public class User{

    private String userName;
    private String passwordHash;
  
    public User(String u, String p){

    	this.userName = u;
    	this.passwordHash = hash(p);
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

}
