package Entity;

import java.sql.Date;

public class Umbrellas {

    public int id;
    public String description;
    public Float price;
    public Date service_date;
    public int categories_id;
    
    public Umbrellas(int id, String description, Float price, Date service_date, int categories_id) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.service_date = service_date;
        this.categories_id = categories_id;
    }
}
