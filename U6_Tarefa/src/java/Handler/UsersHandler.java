package Handler;

import Support.ConexaoBD;
import Support.IDAOT;
import Support.MD5;
import Entity.User;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UsersHandler implements IDAOT<User> {
    
    public static final String _select = 
                "select user, password "
                + "from users "
                + "where user = ? "
                + "and password = ? ";

    @Override
    public boolean Insert(User o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Update(User o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> GetByValue(String criterio, String valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean Auth (User u) {
        
        try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_select);

            pst.setString(1, u.user);
            pst.setString(2, MD5.getMd5(u.password));

            ResultSet result = pst.executeQuery();
            System.out.println("SQL executado!");

            if (result.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Erro ao autenticar USERS: " + e);
            return false;
        }
    }

}
