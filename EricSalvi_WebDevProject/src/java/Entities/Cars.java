package Entities;

public class Cars {

    public int id;
    public String name;
    public String number_plate;
    public String color;
    public String users_email;

    public Cars(int id, String name, String number_plate, String color, String users_email) {
        this.id = id;
        this.name = name;
        this.number_plate = number_plate;
        this.color = color;
        this.users_email = users_email;
    }
    
    public Cars(String name, String number_plate, String color, String users_email) {
        this.name = name;
        this.number_plate = number_plate;
        this.color = color;
        this.users_email = users_email;
    }
    
    public Cars (){
    }
}
