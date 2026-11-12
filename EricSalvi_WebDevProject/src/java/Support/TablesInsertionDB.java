
package Support;

import java.sql.Connection;
import java.sql.Statement;

public class TablesInsertionDB {

    static Connection conexao = null;
    
    private static final String _createUsers = 
            "CREATE TABLE IF NOT EXISTS users (" +
            "	id serial4 NOT NULL ," +
            "	email varchar(60) NOT NULL," +
            "	password varchar(255) NOT NULL," +
            "	role int4 NOT NULL," +
            "	created_at timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT users_pk PRIMARY KEY (id), " +
            "   CONSTRAINT users_un UNIQUE (email));";

    private static final String _createProjects = 
            "CREATE TABLE IF NOT EXISTS projects (" +
            "	id serial4 NOT NULL ," +
            "	name varchar(60) NOT NULL," +
            "	created_by varchar(60) NOT NULL," +
            "	created_at timestamp NOT NULL DEFAULT NOW()," +
            "	updated_at timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT projects_pk PRIMARY KEY (id), " +
            "   CONSTRAINT projects_un UNIQUE (name), " +
            "	CONSTRAINT projects_fk FOREIGN KEY (created_by) REFERENCES users(email) ON UPDATE CASCADE ON DELETE CASCADE);";

    private static final String _createRequirements = 
            "CREATE TABLE IF NOT EXISTS requirements (" +
            "	id serial4 NOT NULL ," +
            "	code varchar(10) NOT NULL," +
            "	type varchar(3) NOT NULL," +
            "	requirement varchar(100) NOT NULL," +
            "	description varchar(255) NULL," +
            "	priority int4 NOT NULL," +
            "	complexity int4 NOT NULL," +
            "	version int4 NOT NULL," +
            "	project_id int4 NOT NULL," +
            "	created_at timestamp NOT NULL DEFAULT NOW()," +
            "	updated_at timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT requirements_pk PRIMARY KEY (id), " +
            "   CONSTRAINT requirements_un UNIQUE (project_id, code, version), " +
            "	CONSTRAINT requirements_fk FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE);";

    private static final String _insertData = 
            "INSERT INTO users (email, password, role) "
            + "VALUES "
            + "('admin', '" + MD5.getMd5("admin") + "', 1),"
            + "('juca', '" + MD5.getMd5("bala") + "', 2) "
            + " ON CONFLICT (email) DO NOTHING;";


    public static boolean CreateTables(boolean createTable) throws Exception {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            if(createTable){
                st.executeUpdate(_createUsers);
                st.executeUpdate(_createProjects);
                st.executeUpdate(_createRequirements);
            
                st.executeUpdate(_insertData);
            }
            
            return true;

        } catch (Exception e) {
            System.err.println("Erro ao tentar criar tabelas: " + e);
            throw e;
        }
    }
}
