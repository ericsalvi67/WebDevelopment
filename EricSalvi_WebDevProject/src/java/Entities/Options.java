package Entities;

public class Options {

    public int id;
    public String name;
    public String description;

    public Options(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public Options(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public Options (){
    }
}
