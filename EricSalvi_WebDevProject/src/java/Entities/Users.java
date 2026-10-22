package Entities;

public class Users {

    public int id;
    public String email;
    public String password;
    
    public Users(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    
    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public Users (){
    }
}
