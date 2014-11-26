public class User{
  String firstName;
  String lastName;
  String userName;
  String passwordHash;
  
  public User(String f, String l, String u, String p){
    this.firstName = f;
    this.lastName = l;
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
  
}
