package com.tutor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class Quiz {
	public HashMap<String, String> queslist = new HashMap<String, String>();
	public HashMap<String, String> marklist = new HashMap<String, String>();
	String name;
	String location;
	
	public Quiz(String name){
		this.name = name;
		this.location = name;
		try {
			BufferedReader r = new BufferedReader(new FileReader(Main.location + "/" + location + ".txt"));
			String a = r.readLine();
			outerloop:
			while (a != null){
				if(a.charAt(0) == '#'){
					for (int i = 1; i < a.length(); i++){
						if (a.charAt(i) == '#'){
							marklist.put(a.substring(1, i), a.substring(i+1, a.length()));
							continue outerloop;
						}
					}
				}
				for (int i = 0; i < a.length(); i ++){
					if (a.charAt(i) == '#'){
						queslist.put(a.substring(0, i), a.substring(i+1, a.length()));
					}
				}
				a = r.readLine();
			}
		} 
		catch (Exception e) {
		}
		
	}
	public void addQuestion(String question, String answer){
		queslist.put(question, answer);
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(Main.location + "/" + location + ".txt", true));
			w.write(question + "#" + answer);
			w.newLine();
			w.close();
		}
		catch(Exception e){
			System.out.println("Error in add user");
		}
	}
	
	public void addResults(User s, int score){
		marklist.put(s.getUserName(), String.valueOf(score));
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(Main.location + "/" + location + ".txt", true));
			w.write("#" + s.getUserName() + "#" + score);
			w.close();
		}
		catch(Exception e){
			System.out.println("Error in add user");
		}
	}
}
