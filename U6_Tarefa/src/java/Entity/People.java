package Entity;

import java.util.Date;

public class People {

    public int id;
    public String name;
    public String email;
    public String phone;
    public Date birth;
    
    public People(int id, String name, String email, String phone, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
    }
}
