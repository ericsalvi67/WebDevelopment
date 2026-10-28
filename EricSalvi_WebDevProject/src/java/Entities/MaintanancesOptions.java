package Entities;

public class MaintanancesOptions {

    public int id;
    public int maintanance_id;
    public int option_id;
    public double unit_value;

    public MaintanancesOptions(int id, int maintanance_id, int option_id, double unit_value) {
        this.id = id;
        this.maintanance_id = maintanance_id;
        this.option_id = option_id;
        this.unit_value = unit_value;
    }
    
    public MaintanancesOptions(int maintanance_id, int option_id, double unit_value) {
        this.maintanance_id = maintanance_id;
        this.option_id = option_id;
        this.unit_value = unit_value;
    }
    
    public MaintanancesOptions (){
    }
}
