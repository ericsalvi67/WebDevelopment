package Entity;

public class User {

    public int id;
    public String user;
    public String password;
    
    public User(int id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }
    
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }
    
    public User (){
    }
}
