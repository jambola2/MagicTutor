package com.tutor;
import java.io.*;
import java.util.ArrayList;

public class Quiz {
	public ArrayList<String[]> groups;
	public Quiz(){
		try{
			BufferedReader r = new BufferedReader(new FileReader(Main.location + "/Groups.txt"));
			String line = r.readLine();
			while(line != null){
				ArrayList<String[]> groups = new ArrayList<String[]>();
				groups.add(line.split("\\s+"));
			}
			r.close();
		}
		catch (Exception e){
			System.out.println("Groups not found");
		}
	}
}
