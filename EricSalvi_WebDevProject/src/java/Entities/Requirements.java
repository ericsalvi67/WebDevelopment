package Entities;

public class Requirements {

    public int id;
    public String code;
    public String type;
    public String requirement;
    public String description;
    public int priority;
    public int complexity;
    public int version;
    public int project_id;

    public Requirements(int id, String code, String type, String requirement,String description, int priority, int complexity, int version, int project_id) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.requirement = requirement;
        this.description = description;
        this.priority = priority;
        this.complexity = complexity;
        this.version = version;
        this.project_id = project_id;
    }

    public Requirements(String code, String type, String requirement,String description, int priority, int complexity, int version, int project_id) {
        this(
            0, 
            code, 
            type, 
            requirement, 
            description, 
            priority, 
            complexity, 
            version, 
            project_id);
    }

    public Requirements() {
    }
}
