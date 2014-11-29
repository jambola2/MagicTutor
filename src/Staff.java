public class Staff extends User{
	private String title;
	private String firstName;
	private String lastName;
	private String userName;
	private String passwordHash;
	
	public Staff(String f, String l, String u, String p, String t){
		super(f, l, u, p)
		this.title = t;
	}
}