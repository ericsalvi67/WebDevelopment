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
            "	created_at timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT users_pk PRIMARY KEY (id), " +
            "   CONSTRAINT users_un UNIQUE (email));";

    private static final String _createCars = 
            "CREATE TABLE IF NOT EXISTS cars (" +
            "	id serial4 NOT NULL ," +
            "	name varchar(30) NOT NULL," +
            "	number_plate varchar(15) NOT NULL," +
            "	color varchar(20) NULL," +
            "	users_email varchar(60) NOT NULL," +
            "	created_at timestamp NOT NULL DEFAULT NOW()," +
            "	updated_at timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT cars_pk PRIMARY KEY (id), " +
            "   CONSTRAINT cars_un UNIQUE (number_plate), " +
            "   CONSTRAINT cars_fk FOREIGN KEY (users_email) REFERENCES users(email) ON DELETE CASCADE);";

    private static final String _createOptions = 
            "CREATE TABLE IF NOT EXISTS options (" +
            "	id serial4 NOT NULL ," +
            "	name varchar(50) NOT NULL," +
            "	description varchar(255) NULL," +
            "	created_at timestamp NOT NULL DEFAULT NOW()," +
            "	updated_at timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT options_pk PRIMARY KEY (id), " +
            "   CONSTRAINT options_un UNIQUE (name));";
    
    private static final String _createMaintanances = 
            "CREATE TABLE IF NOT EXISTS maintanances (" +
            "	id serial4 NOT NULL ," +
            "	name varchar(30) NOT NULL," +
            "	car_plate varchar(15) NOT NULL," +
            "	done_by varchar(100) NULL," +
            "	done_at timestamp NULL," +
            "	observations varchar(500) NULL," +
            "	created_at timestamp NOT NULL DEFAULT NOW()," +
            "	updated_at timestamp NOT NULL DEFAULT NOW()," +
            "	CONSTRAINT maintanances_pk PRIMARY KEY (id), " +
            "	CONSTRAINT maintanances_un UNIQUE (name), " +
            "  CONSTRAINT maintanances_fk FOREIGN KEY (car_plate) REFERENCES cars(number_plate) ON DELETE CASCADE);";
   
    private static final String _createMaintanancesOptions = 
            "CREATE TABLE IF NOT EXISTS maintanances_options (" +
            "	maintanance_id int4 NOT NULL ," +
            "	option_id int4 NOT NULL," +
            "	unit_value decimal(9,2) NOT NULL," +
            "	CONSTRAINT maintanances_options_pk PRIMARY KEY (maintanance_id, option_id), " +
            "   CONSTRAINT maintanances_options_fk1 FOREIGN KEY (maintanance_id) REFERENCES maintanances(id) ON DELETE CASCADE," +
            "   CONSTRAINT maintanances_options_fk2 FOREIGN KEY (option_id) REFERENCES options(id) ON DELETE CASCADE);";

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
                st.executeUpdate(_createCars);
                st.executeUpdate(_createOptions);
                st.executeUpdate(_createMaintanances);
                st.executeUpdate(_createMaintanancesOptions);
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
