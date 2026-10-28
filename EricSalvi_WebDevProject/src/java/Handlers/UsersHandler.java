package Handlers;

import Support.ConexaoBD;
import Support.IDAOT;
import Entities.Users;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UsersHandler implements IDAOT<Users> {
    
    public static final String _select = 
                "select 1 "
                + "from users "
                + "where email = ? "
                + "and password = ? ";

    @Override
    public boolean Insert(Users o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Update(Users o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Users> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Users> GetByValue(String criterio, String valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Users GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean Auth (Users u) {
        
        try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_select);

            pst.setString(1, u.email);
            pst.setString(2, u.password);

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
