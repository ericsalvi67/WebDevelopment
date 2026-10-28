package Handlers;

import Support.ConexaoBD;
import Support.IDAOT;
import Entities.Cars;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class CarsHandler implements IDAOT<Cars> {

    public static final String _select = 
            "select id, "
            + "name, "
            + "number_plate, "
            + "color, "
            + "users_email "
            + "from cars ";

    public static final String _insert = 
            "insert into cars "
            + "(name, number_plate, color, user_email) "
            + "values "
            + "(?, ?, ?, ?)";

    public static final String _update = 
            "update set "
            + "name = ?, "
            + "number_plate = ?, "
            + "color = ?, "
            + "users_email = ?, "
            + "updated_at = ? ";

    public static final String _delete = 
            "delete from cars "
            + "where id = ?";
    
    public static final String _upsert = 
            _insert
            + " ON CONFLICT (number_plate) DO " 
            + _update; 

    @Override
    public boolean Insert(Cars o) {
         try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_upsert);
            
            pst.setString(1, o.name);
            pst.setString(2, o.number_plate);
            pst.setString(3, o.color);
            pst.setString(4, o.users_email);
            pst.setString(5, o.name);
            pst.setString(6, o.number_plate);
            pst.setString(7, o.color);
            pst.setString(8, o.users_email);
            pst.setTimestamp(9, Timestamp.from(Instant.now()));
            pst.execute();
            System.out.println("SQL executado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao executar upsert em Cars: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(Cars o) {
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
            System.out.println("Erro ao excluir cars: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Cars> GetAll() {
        ArrayList<Cars> Cars = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " order by number_plate");
            System.out.println("SQL executado!");

            while (rs.next()) {
                Cars.add(new Cars(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("number_plate"),
                        rs.getString("color"),
                        rs.getString("users_email")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar cars: " + e);
        }

        return Cars;
    }

    @Override
    public ArrayList<Cars> GetByValue(String criteria, String value) {
        ArrayList<Cars> Cars = new ArrayList<>();
        String whereSql = 
            " where id = '?' "
            + "or name ilike '?' "
            + "or number_plate ilike '?' "
            + "or color ilike '?' "
            + "or users_email ilike '?' ";

        try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_select + whereSql);
            
            pst.setString(1, value);
            pst.setString(2, value);
            pst.setString(3, value);
            pst.setString(4, value);
            pst.setString(5, value);
            
            ResultSet rs = pst.executeQuery();
            System.out.println("SQL executado!");

            while (rs.next()) {
                Cars.add(new Cars(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("number_plate"),
                    rs.getString("color"),
                    rs.getString("users_email")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar cars: " + e);
        }

        return Cars;
    }

    @Override
    public Cars GetById(int id) {
        Cars Cars = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " where id = " + id);
            System.out.println("SQL executado!");

            while (rs.next()) {
                Cars = new Cars(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("number_plate"),
                        rs.getString("color"),
                        rs.getString("users_email"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar cars: " + e);
        }

        return Cars;
    }
}