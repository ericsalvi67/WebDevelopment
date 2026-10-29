package Handlers;

import Support.ConexaoBD;
import Support.IDAOT;
import Entities.Options;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class OptionsHandler implements IDAOT<Options> {

    public static final String _select = 
            "select id, "
            + "name, "
            + "description "
            + "from options ";

    public static final String _insert = 
            "insert into options "
            + "(name, description) "
            + "values "
            + "(?, ?)";

    public static final String _update = 
            "update set "
            + "name = ?, "
            + "description = ?, "
            + "updated_at = ? ";

    public static final String _delete = 
            "delete from options "
            + "where id = ?";
    
    public static final String _upsert = 
            _insert
            + " ON CONFLICT (name) DO " 
            + _update; 

    @Override
    public boolean Insert(Options o) {
         try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_upsert);
            
            pst.setString(1, o.name);
            pst.setString(2, o.description);
            pst.setString(3, o.name);
            pst.setString(4, o.description);
            pst.setTimestamp(3, Timestamp.from(Instant.now()));
            pst.execute();
            System.out.println("SQL executado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao executar upsert em Options: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(Options o) {
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
            System.out.println("Erro ao excluir Options: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Options> GetAll() {
        ArrayList<Options> Options = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " order by name");
            System.out.println("SQL executado!");

            while (rs.next()) {
                Options.add(new Options(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Options: " + e);
        }

        return Options;
    }

    @Override
    public ArrayList<Options> GetByValue(String criteria, String value) {
        ArrayList<Options> Options = new ArrayList<>();
        String whereSql = 
            " where id = '?' "
            + "or name ilike '?' "
            + "or description ilike '?' ";

        try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_select + whereSql);
            
            pst.setString(1, value);
            pst.setString(2, value);
            pst.setString(3, value);
            
            ResultSet rs = pst.executeQuery();
            System.out.println("SQL executado!");

            while (rs.next()) {
                Options.add(new Options(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Options: " + e);
        }

        return Options;
    }

    @Override
    public Options GetById(int id) {
        Options Options = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " where id = " + id);
            System.out.println("SQL executado!");

            while (rs.next()) {
                Options = new Options(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Options: " + e);
        }

        return Options;
    }
}