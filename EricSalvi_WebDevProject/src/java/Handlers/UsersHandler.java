package Handlers;

import Entities.Users;
import Support.ConexaoBD;
import Support.IDAOT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersHandler implements IDAOT<Users> {

    public static final String _select =
            "select id, email, password, role from users ";

    @Override
    public boolean Insert(Users o) {
        return true;
    }

    @Override
    public boolean Update(Users o) {
        return true;
    }

    @Override
    public boolean Delete(int id) {
        return true;
    }

    @Override
    public ArrayList<Users> GetAll() {
        return null;
    }

    @Override
    public ArrayList<Users> GetByValue(String criterio, String valor) {
        return null;
    }

    @Override
    public Users GetById(int id) {
        return null;
    }

    public Users Auth(Users u) {
        try {

            PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                    .prepareStatement(_select + "where email = ? and password = ?");

            pst.setString(1, u.email);
            pst.setString(2, u.password);
            ResultSet result = pst.executeQuery();

            System.out.println("SQL executado!");
            return result.next() ? toUser(result) : null;
            
        } catch (Exception e) {
            System.out.println("Erro ao autenticar USERS: " + e);
            return null;
        }
    }

    private Users toUser(ResultSet result) {
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
