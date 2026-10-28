package Handlers;

import Support.ConexaoBD;
import Support.IDAOT;
import Entities.MaintanancesOptions;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class MaintanancesOptionsHandler implements IDAOT<MaintanancesOptions> {

    public static final String _select = 
            "select id, "
            + "maintanance_id, "
            + "option_id, "
            + "unit_value, "
            + "from maintanances_options ";

    public static final String _insert = 
            "insert into maintanances_options "
            + "(maintanance_id, options_id, unit_values) "
            + "values "
            + "(?, ?, ?)";

    public static final String _update = 
            "update set "
            + "maintanance_id = ?, "
            + "options_id = ?, "
            + "unit_values = ?, "
            + "updated_at = ? ";

    public static final String _delete = 
            "delete from maintanances_options "
            + "where maintanance_id = ?";
    
    public static final String _upsert = 
            _insert
            + " ON CONFLICT (name) DO " 
            + _update; 

    @Override
    public boolean Insert(MaintanancesOptions o) {
         try {
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_upsert);
            
            pst.setInt(1, o.maintanance_id);
            pst.setInt(2, o.option_id);
            pst.setDouble(3, o.unit_value);
            pst.execute();
            System.out.println("SQL executado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao executar upsert em maintanances_options: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(MaintanancesOptions o) {
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
            System.out.println("Erro ao excluir maintanances_options: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<MaintanancesOptions> GetAll() {
        return new ArrayList<MaintanancesOptions>();
    }

    @Override
    public ArrayList<MaintanancesOptions> GetByValue(String criteria, String value) {
        return new ArrayList<MaintanancesOptions>();
    }

    @Override
    public MaintanancesOptions GetById(int id) {
        return new MaintanancesOptions();
    }
}