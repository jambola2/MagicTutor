package com.tutor;

import java.io.*;
import java.util.*;

public class Main {
	// Mac-location Uncomment next line
	//public static String location = "/Users/venkateshrameshkumar/Dropbox/workspace/Database.txt";
	// Windows-location Uncomment next line
	public static String location = "C:\\Users\\venkatesh\\Dropbox\\workspace\\Database.txt";
	
	public static HashMap<String, String> passwords;
	static BufferedReader read;
		public static void main(String[] args) {
			// Data is stored as Username#PasswordHash#UserType
			// No hashtags or spaces are allowed in the Username
			Scanner s = new Scanner(System.in);
			if(location == null){
				System.out.println("Please intialise password database");
				location = s.nextLine().replaceAll("//s","");
				}
			try{
				read = new BufferedReader(new FileReader(location));
				passwords = new HashMap<String, String>();
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
				read.close();
				new Login();
				
			}
			catch (Exception e){
				System.out.println("File not found");
				s.close();
				return;
			}
			s.close();
			
		}
	public static User login(HashMap<String, String> passwords, String username, String password){
		String result = passwords.get(username);
		String rpass = null;
		if (result == null){
			System.out.println("Username not found");
			return null;
		}
		for (int n = 0; n < result.length(); n++){
			if (result.charAt(n)=='#'){
				rpass = result.substring(0, n);
			}
		}

		for (int i = 0; i < result.length(); i++){
			if (result.charAt(i)=='#'){
				if (result.charAt(i+1)=='a'){
					Admin a = new Admin(username, password);
					if (!a.getPasswordHash().equals(rpass)){ break; }
					System.out.println("Admin "+ username + " logged in!");
					new MainMenu(username, "a");
					return a;
				}
				if (result.charAt(i+1)=='t'){
					Teacher t = new Teacher(username, password);
					if (!t.getPasswordHash().equals(rpass)){ break; }
					System.out.println("Teacher " + username + " logged in!");
					new MainMenu(username, "t");
					return t;
				}
				if (result.charAt(i+1)== 's'){
					Student s = new Student(username, password);
					if (!s.getPasswordHash().equals(rpass)){ break; }
					System.out.println("Student " + username + " logged in!");
					new MainMenu(username, "s");
					return s;
				}
			}
		}
		System.out.println("Usertype unrecognised");
		return null;
	}
	
}
