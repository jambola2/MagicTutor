package com.tutor;


public class Student extends User{
    private String userName;
    private String passwordHash;
	
    public Student(String u, String p) {
	  super(u, p);
    }
  
}
