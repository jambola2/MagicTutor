package com.tutor;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Groups {
	public ArrayList<String[]> groups;
	public Groups(){
		try{
			BufferedReader r = new BufferedReader(new FileReader(Main.location + "/Groups.txt"));
			String line = r.readLine();
			while(line != null){
				ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
				groups.add(new ArrayList<String>(Arrays.asList(line.split("\\s+"))));
			}
			r.close();
		}
		catch (Exception e){
			System.out.println("Groups not found");
		}
	}
	
	public void addToGroup(){
		
	}
}
