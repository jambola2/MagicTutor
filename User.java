public class User{
  String firstName;
  String lastName;
  String userName;
  String passwordHash;
  
  public User(String f, String l, String u, String p){
    this.firstName = f;
    this.lastName = l;
    this.userName = u;
    this.passwordHash = p;
  }
}
