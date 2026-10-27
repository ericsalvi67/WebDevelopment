package Support;

import java.sql.Connection;
import java.sql.Statement;

public class TablesInsertionDB {

    static Connection conexao = null;
    
    private static final String _createUmbrellas = 
            "CREATE TABLE IF NOT EXISTS umbrellas ("
            + "	id serial4 NOT NULL,"
            + "	description varchar(150) NOT NULL,"
            + "	price numeric(6,2) NOT NULL,"
            + " service_date timestamp NOT NULL DEFAULT NOW(),"
            + " categories_id int4 NOT NULL,"
            + "	last_modification timestamp NOT NULL DEFAULT NOW(),"
            + "	CONSTRAINT umbrellas_pk PRIMARY KEY (id),"
            + " CONSTRAINT umbrellas_un UNIQUE (id));";
    
    private static final String _createCategories = 
            "CREATE TABLE IF NOT EXISTS categories ("
            + "	id serial4 NOT NULL,"
            + "	description varchar(150) NOT NULL,"
            + "	last_modification timestamp NOT NULL DEFAULT NOW(),"
            + "	CONSTRAINT categories_pk PRIMARY KEY (id),"
            + " CONSTRAINT categories_un UNIQUE (id));";
 


    public static boolean CreateTables(boolean createTable, boolean addData) throws Exception {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            if(createTable){
                st.executeUpdate(_createUmbrellas);
                st.executeUpdate(_createCategories);
            }
            
            return true;

        } catch (Exception e) {
            System.err.println("Erro ao tentar criar tabelas: " + e);
            throw e;
        }
    }
}
