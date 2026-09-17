package Handler;

import Support.ConexaoBD;
import Support.IDAOT;
import Entity.People;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;

public class PeopleHandler implements IDAOT<People> {

    public static final String _select = 
            "select id, "
            + "name, "
            + "email, "
            + "phone, "
            + "birth "
            + "from people ";

    public static final String _insert = 
            "insert into people "
            + "(name, email, phone, birth) "
            + "values "
            + "(?, ?, ?, ?)";

    public static final String _update = 
            "update set "
            + "name = ?, "
            + "email = ?, "
            + "phone = ?, "
            + "birth = ? ";

    public static final String _delete = 
            "delete from people "
            + "where id = ?";
    
    public static final String _upsert = 
            _insert
            + " ON CONFLICT (email) DO " 
            + _update; 

    @Override
    public boolean Insert(People o) {
         try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_upsert);
            
            pst.setString(1, o.name);
            pst.setString(2, o.email);
            pst.setString(3, o.phone);
            pst.setDate(4, o.birth);
            pst.setString(5, o.name);
            pst.setString(6, o.email);
            pst.setString(7, o.phone);
            pst.setDate(8, o.birth);

            pst.execute();
            System.out.println("SQL executado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao executar upsert em PEOPLE: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(People o) {
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
            System.out.println("Erro ao excluir PEOPLE: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<People> GetAll() {
        ArrayList<People> people = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " order by name");
            System.out.println("SQL executado!");

            while (rs.next()) {
                people.add(new People(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("birth")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar PEOPLE: " + e);
        }

        return people;
    }

    @Override
    public ArrayList<People> GetByValue(String criteria, String value) {
        ArrayList<People> people = new ArrayList<>();
        String whereSql = 
            " where id = '?' "
            + "or name ilike '?' "
            + "or email ilike '?' "
            + "or phone ilike '?' "
            + "or birth ilike '?' ";

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
                people.add(new People(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getDate("birth")));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar PEOPLE: " + e);
        }

        return people;
    }

    @Override
    public People GetById(int id) {
        People people = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(_select + " where id = " + id);
            System.out.println("SQL executado!");

            while (rs.next()) {
                people = new People(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("birth"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar PEOPLE: " + e);
        }

        return people;
    }
}