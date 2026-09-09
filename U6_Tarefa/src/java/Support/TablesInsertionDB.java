package Support;

import java.sql.Connection;
import java.sql.Statement;

public class TablesInsertionDB {

    static Connection conexao = null;
    
    private static final String _createUsers = 
            "CREATE TABLE IF NOT EXISTS users (" +
            "	id serial4 NOT NULL," +
            "	name varchar(150) NOT NULL," +
            "	password varchar(200) NOT NULL," +
            "	last_modification timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT usuario_pk PRIMARY KEY (id), " +
            "   CONSTRAINT usuario_un UNIQUE (name));";
    
    private static final String _createPeople = 
            "CREATE TABLE IF NOT EXISTS people ("
            + "	id serial4 NOT NULL,"
            + "	name varchar(150) NOT NULL,"
            + " email varchar(50) NOT NULL,"
            + " phone varchar(15) NOT NULL,"
            + " birth date NOT NULL,"
            + "	last_modification timestamp NOT NULL DEFAULT NOW(),"
            + "	CONSTRAINT people_pk PRIMARY KEY (id),"
            + " CONSTRAINT people_un UNIQUE (email));";
    
    private static final String _insertData = 
            "INSERT INTO users (name, password)"
            + "VALUES "
            + "('admin', '" + MD5.getMd5("admin") + "'),"
            + "('juca', '" + MD5.getMd5("bala") + "');";


    public static boolean CreateTables(boolean createTable, boolean addData) throws Exception {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            if(createTable){
                st.executeUpdate(_createUsers);
                st.executeUpdate(_createPeople);
            }
            
            if (addData){
                st.executeUpdate(_insertData);
            }
            
            return true;

        } catch (Exception e) {
            System.err.println("Erro ao tentar criar tabelas: " + e);
            throw e;
        }
    }
}
