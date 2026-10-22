package Support;

import java.sql.Connection;
import java.sql.Statement;

public class TablesInsertionDB {

    static Connection conexao = null;
    
    private static final String _createUsers = 
            "CREATE TABLE IF NOT EXISTS users (" +
            "	id serial4 NOT NULL," +
            "	email varchar(60) NOT NULL," +
            "	password varchar(255) NOT NULL," +
            "	last_modification timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT users_pk PRIMARY KEY (id), " +
            "   CONSTRAINT users_un UNIQUE (email));";
   
    
    private static final String _insertData = 
            "INSERT INTO users (email, password)"
            + "VALUES "
            + "('admin', '" + MD5.getMd5("admin") + "'),"
            + "('juca', '" + MD5.getMd5("bala") + "');";


    public static boolean CreateTables(boolean createTable, boolean addData) throws Exception {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            if(createTable){
                st.executeUpdate(_createUsers);
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
