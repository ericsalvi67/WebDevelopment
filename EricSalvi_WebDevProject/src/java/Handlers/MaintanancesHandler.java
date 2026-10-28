package Handlers;

import Support.ConexaoBD;
import Support.IDAOT;
import Entities.Maintanances;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class MaintanancesHandler implements IDAOT<Maintanances> {

    public static final String _select = 
            "select id, "
            + "name, "
            + "car_plate, "
            + "done_by, "
            + "done_at, "
            + "observations, "
            + "from maintanances ";

    public static final String _insert = 
            "insert into maintanances "
            + "(name, car_plate, done_by, done_at, observations) "
            + "values "
            + "(?, ?, ?, ?, ?)";

    public static final String _update = 
            "update set "
            + "name = ?, "
            + "car_plate = ?, "
            + "done_by = ?, "
            + "done_at = ?, "
            + "observations = ?, "
            + "updated_at = ? ";

    public static final String _delete = 
            "delete from maintanances "
            + "where id = ?";
    
    public static final String _upsert = 
            _insert
            + " ON CONFLICT (name) DO " 
            + _update; 

    @Override
    public boolean Insert(Maintanances o) {
         try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_upsert);
            
            pst.setString(1, o.name);
            pst.setString(2, o.car_plate);
            pst.setString(3, o.done_by);
            pst.setDate(4, (java.sql.Date) o.done_at);
            pst.setString(5, o.observations);
            pst.setString(6, o.name);
            pst.setString(7, o.car_plate);
            pst.setString(8, o.done_by);
            pst.setDate(9, (java.sql.Date) o.done_at);
            pst.setTimestamp(10, Timestamp.from(Instant.now()));

            pst.execute();
            System.out.println("SQL executado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao executar upsert em Maintanances: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(Maintanances o) {
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
            System.out.println("Erro ao excluir Maintanances: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Maintanances> GetAll() {
        ArrayList<Maintanances> Maintanances = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " order by name");
            System.out.println("SQL executado!");

            while (rs.next()) {
                Maintanances.add(new Maintanances(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("car_plate"),
                        rs.getString("done_by"),
                        rs.getDate("done_at"),
                        rs.getString("observations")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Maintanances: " + e);
        }

        return Maintanances;
    }

    @Override
    public ArrayList<Maintanances> GetByValue(String criteria, String value) {
        ArrayList<Maintanances> Maintanances = new ArrayList<>();
        String whereSql = 
            " where id = '?' "
            + "or name ilike '?' "
            + "or car_plate ilike '?' "
            + "or done_by ilike '?' "
            + "or done_at ilike '?' "
            + "or observations ilike '?' ";

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
                Maintanances.add(new Maintanances(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("car_plate"),
                    rs.getString("done_by"),
                    rs.getDate("done_at"),
                    rs.getString("observations")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Maintanances: " + e);
        }

        return Maintanances;
    }

    @Override
    public Maintanances GetById(int id) {
        Maintanances Maintanances = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " where id = " + id);
            System.out.println("SQL executado!");

            while (rs.next()) {
                Maintanances = new Maintanances(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("car_plate"),
                        rs.getString("done_by"),
                        rs.getDate("done_at"),
                        rs.getString("observations"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Maintanances: " + e);
        }

        return Maintanances;
    }
}