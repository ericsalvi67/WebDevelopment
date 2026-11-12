package Entities;

import java.sql.Timestamp;

public class Projects {

    public int id;
    public String name;
    public String created_by;
    public Timestamp created_at;

    public Projects(int id, String name, String created_by) {
        this(
            id,
            name, 
            created_by, 
            Timestamp.from(java.time.Instant.now())
        );
    }

    public Projects(int id, String name, String created_by, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.created_by = created_by;
        this.created_at = created_at;
    }

    public Projects(String name, String created_by) {
        this.name = name;
        this.created_by = created_by;
    }

    public Projects() {
    }
}
