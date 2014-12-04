package com.tutor;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		String location = "/Users/venkateshrameshkumar/Dropbox/workspace/Database.txt";
		// Data is stored as Username#PasswordHash#UserType
		// No hashtags or spaces are allowed in Username and Password
		Scanner s = new Scanner(System.in);
		if(location == null){
			System.out.println("Please intialise password database");
			location = s.nextLine().replaceAll("//s","");
			}
		try{
			BufferedReader read = new BufferedReader(new FileReader(location));
			HashMap<String, String> passwords = new HashMap<String, String>();
			while (true){
				String a = read.readLine();
				if (a == null){
					break;
				}
				int seperator = 0;
				for(; seperator<a.length(); seperator++){
					if (a.charAt(seperator) == '#'){
						passwords.put((a.substring(0,seperator).replaceAll("//s", "")),
						(a.substring(seperator+1).replaceAll("//s", "")));
						break;
					}
				}
			}
			System.out.println("Database initialized");
			login(passwords);
			read.close();
		}
		catch (Exception e){
			System.out.println("File not found");
			s.close();
			return;
		}
		s.close();
		
	}
	public static User login(HashMap<String, String> passwords){
		String username = "venki";//TODO:Add GUI to get username
		String password = "awesome";//TODO:Add GUI to get password
		String result = passwords.get(username);
		if (result == null){
			return null;
		}
		for (int i = 0; i < result.length(); i++){
			if (result.charAt(i)=='#'){
				if (result.charAt(i+1)=='a'){
					System.out.println("Admin "+ username + " logged in!");
					return new Admin(username, password);
				}
				if (result.charAt(i+1)=='t'){
					System.out.println("Teacher " + username + " logged in!");
					return new Teacher(username, password);
				}
				if (result.charAt(i+1)== 's'){
					System.out.println("Student " + username + " logged in!");
					return new Student(username, password);
				}
			}
		}
		return null;
	}

}
