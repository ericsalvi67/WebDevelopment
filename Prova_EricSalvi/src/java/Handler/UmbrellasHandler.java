package Handler;

import Support.ConexaoBD;
import Support.IDAOT;
import Entity.Umbrellas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UmbrellasHandler implements IDAOT<Umbrellas> {

    public static final String _select = 
            "select id, "
            + "description, "
            + "price, "
            + "service_date, "
            + "categories_id "
            + "from umbrellas ";

    public static final String _insert = 
            "insert into umbrellas "
            + "(description, price, service_date, categories_id) "
            + "values "
            + "(?, ?, ?, ?)";

    public static final String _update = 
            "update set "
            + "description = ?, "
            + "price = ?, "
            + "service_date = ?, "
            + "categories_id = ? ";

    public static final String _delete = 
            "delete from umbrellas "
            + "where id = ?";
    
    public static final String _upsert = 
            _insert
            + " ON CONFLICT (id) DO " 
            + _update; 

    @Override
    public boolean Insert(Umbrellas o) {
         try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_upsert);
            
            pst.setString(1, o.description);
            pst.setFloat(2, o.price);
            pst.setDate(3, o.service_date);
            pst.setInt(4, o.categories_id);
            pst.setString(5, o.description);
            pst.setFloat(6, o.price);
            pst.setDate(7, o.service_date);
            pst.setInt(8, o.categories_id);

            pst.execute();
            System.out.println("SQL executado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao executar upsert em UMBRELLAS: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(Umbrellas o) {
        return true;
    }

    @Override
    public boolean Delete(int id) {
        try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_delete);
            
            pst.setInt(1, id);

            pst.execute();
            System.out.println("SQL executado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao excluir UMBRELLAS: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Umbrellas> GetAll() {
        ArrayList<Umbrellas> umbrellas = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " order by last_modification desc");
            System.out.println("SQL executado!");

            while (rs.next()) {
                umbrellas.add(new Umbrellas(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getDate("service_date"),
                        rs.getInt("categories_id")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar UMBRELLAS: " + e);
        }

        return umbrellas;
    }

    @Override
    public ArrayList<Umbrellas> GetByValue(String criterio, String valor){ return new ArrayList(); }
    
    
    @Override
    public Umbrellas GetById(int id) {
        Umbrellas umbrella = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " where id = " + id);
            System.out.println("SQL executado!");

            while (rs.next()) {
                umbrella = new Umbrellas(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getDate("service_date"),
                        rs.getInt("categories_id"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar UMBRELLAS: " + e);
        }

        return umbrella;
    }
}