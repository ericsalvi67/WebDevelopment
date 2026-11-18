package Handlers;

import Entities.Users;
import Support.ConexaoBD;
import Support.IDAOT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersHandler implements IDAOT<Users> {

    public static final String _select =
            "select id, "
            + "email, "
            + "password, "
            + "role "
            + "from users ";

    public static final String _insert =
            "insert into users (email, password, role) values (?, ?, ?)";

    public static final String _update =
            "update users set "
            + "email = ?, "
            + "password = ?, "
            + "role = ? "
            + "where id = ?";

    public static final String _delete =
            "delete from users where id = ?";

    @Override
    public boolean Insert(Users o) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_insert)) {

            pst.setString(1, o.email);
            pst.setString(2, o.password);
            pst.setInt(3, o.role);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao inserir USERS: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(Users o) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_update)) {

            pst.setString(1, o.email);
            pst.setString(2, o.password);
            pst.setInt(3, o.role);
            pst.setInt(4, o.id);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar USERS: " + e);
            return false;
        }
    }

    @Override
    public boolean Delete(int id) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_delete)) {

            pst.setInt(1, id);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao excluir USERS: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Users> GetAll() {
        String sql = _select + "order by id";
        
        ArrayList<Users> result = new ArrayList<>();
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    result.add(ToAggregate(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar USERS: " + e);
        }

        return result;
    }

    @Override
    public ArrayList<Users> GetByValue(String criterio, String valor) {
        String sql = _select + "where email ilike ? order by id";
        
        ArrayList<Users> result = new ArrayList<>();
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            
            pst.setString(1, valor);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    result.add(ToAggregate(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar USERS: " + e);
        }

        return result;
    }

    @Override
    public Users GetById(int id) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_select + "where id = ?")) {

            pst.setInt(1, id);

            try (ResultSet result = pst.executeQuery()) {
                return result.next() ? ToAggregate(result) : null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar USER por id: " + e);
            return null;
        }
    }

    public Users Auth(Users u) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_select + "where email = ? and password = ?")) {

            pst.setString(1, u.email);
            pst.setString(2, u.password);

            try (ResultSet result = pst.executeQuery()) {
                return result.next() ? ToAggregate(result) : null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar USERS: " + e);
            return null;
        }
    }

    private Users ToAggregate(ResultSet result) {
        try {
            return new Users(
                result.getInt("id"),
                result.getString("email"),
                result.getString("password"),
                result.getInt("role")
            );
        } catch (Exception e) {
            System.out.println("Erro ao converter ResultSet para Users: " + e);
            return null;
        }
    }
}
