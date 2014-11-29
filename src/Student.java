public class Student extends User{
    private String firstName;
    private String lastName;
    private String userName;
    private String passwordHash;
	
    public Student(String f, String l, String u, String p) {
	  super(f, l, u, p);
    }
  
}
