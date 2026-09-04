package Handler;

import Support.ConexaoBD;
import Support.IDAOT;
import Entity.People;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
            "update people set "
            + "name = ?, "
            + "email = ?, "
            + "phone = ?, "
            + "birth = ? "
            + "where id = ?";

    public static final String _delete = 
            "delete from endereco "
            + "where id = ?";

    @Override
    public boolean Insert(People o) {
         try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_insert);

            pst.setString(1, o.name);
            pst.setString(2, o.email);
            pst.setString(3, o.phone);
            pst.setString(4, o.birth.toString());

            pst.executeQuery();
            System.out.println("SQL executado!");

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao inserir PEOPLE: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(People o) {
        try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_update);

            pst.setString(1, o.name);
            pst.setString(2, o.email);
            pst.setString(3, o.phone);
            pst.setString(4, o.birth.toString());
            pst.setInt(6, o.id);

            pst.executeUpdate();
            System.out.println("SQL executado!");

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar PEOPLE: " + e);
            return false;
        }
    }

    @Override
    public boolean Delete(int id) {
        try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_delete);

            pst.setInt(1, id);

            pst.executeUpdate();
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

            ResultSet rs = st.executeQuery(_select + " order by rua");
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
    public ArrayList<People> GetByValue(String criterio, String valor) {
        ArrayList<People> people = new ArrayList<>();
        String sql = _select + " where " + criterio + " ilike '%" + valor + "%';";
        
        if(criterio == "ID"){
            sql = _select + " where " + criterio + " = " + valor + ";";
        }

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(sql);
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