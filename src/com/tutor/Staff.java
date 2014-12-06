package com.tutor;

import java.util.HashMap;
import java.util.HashMap;

public class Staff extends User{

	private String userName;
	private String passwordHash;
	
	public Staff(String u, String p, String f){
		super(u, p, f);
	}
	public void createStudent(HashMap<String, String> passwords){
		while (true){
			String password = "awesome";//TODO Ask to confirm password with GUI
			if (hash(password).equals(this.passwordHash)){
				continue; //TODO: Say that password is incorrect and ask to try again
			}
			String username = "a";//TODO ask for student name with GUI
			String spassword = "b"; //TODO ask for student password with GUI
			if (passwords.get(username) == null){
				add(username, spassword, "s");
			}
		}
	}
}
