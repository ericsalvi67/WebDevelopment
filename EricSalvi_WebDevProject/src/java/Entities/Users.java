package Entities;

public class Users {

    public int id;
    public String email;
    public String password;
    public int role;

    public Users(int id, String email, String password) {
        this(id, email, password, 0);
    }

    public Users(int id, String email, String password, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users(String email, String password, int role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public Users (){
    }
}
