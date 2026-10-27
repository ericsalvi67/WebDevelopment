package Handler;

import Support.ConexaoBD;
import Support.IDAOT;
import Entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriesHandler implements IDAOT<Categories> {

    public static final String _select = 
            "select id, "
            + "description "
            + "from categories ";

    public static final String _insert = 
            "insert into categories "
            + "(description) "
            + "values "
            + "(?)";

    public static final String _update = 
            "update set "
            + "description = ? ";

    public static final String _delete = 
            "delete from categories "
            + "where id = ?";
    
    public static final String _upsert = 
            _insert
            + " ON CONFLICT (id) DO " 
            + _update; 

    @Override
    public boolean Insert(Categories o) {
         try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_upsert);
            
            pst.setString(1, o.description);
            pst.setString(2, o.description);

            pst.execute();
            System.out.println("SQL executado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao executar upsert em CATEGORIES: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(Categories o) {
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
            System.out.println("Erro ao excluir CATEGORIES: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Categories> GetAll() {
        ArrayList<Categories> categories = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " order by last_modification desc");
            System.out.println("SQL executado!");

            while (rs.next()) {
                categories.add(new Categories(
                        rs.getInt("id"),
                        rs.getString("description")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar CATEGORIES: " + e);
        }

        return categories;
    }

    @Override
    public ArrayList<Categories> GetByValue(String criterio, String valor){ return new ArrayList(); }
    
    
    @Override
    public Categories GetById(int id) {
        Categories categories = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " where id = " + id);
            System.out.println("SQL executado!");

            while (rs.next()) {
                categories = new Categories(
                        rs.getInt("id"),
                        rs.getString("description"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar CATEGORIES: " + e);
        }

        return categories;
    }
}