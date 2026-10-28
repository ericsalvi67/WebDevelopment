package Entities;

import java.util.Date;

public class Maintanances {

    public int id;
    public String name;
    public String car_plate;
    public String done_by;
    public Date done_at;
    public String observations;

    public Maintanances(int id, String name, String car_plate, String done_by, Date done_at, String observations) {
        this.id = id;
        this.name = name;
        this.car_plate = car_plate;
        this.done_by = done_by;
        this.done_at = done_at;
        this.observations = observations;
    }
    
    public Maintanances(String name, String car_plate, String done_by, Date done_at, String observations) {
        this.name = name;
        this.car_plate = car_plate;
        this.done_by = done_by;
        this.done_at = done_at;
        this.observations = observations;
    }
    
    public Maintanances (){
    }
}
