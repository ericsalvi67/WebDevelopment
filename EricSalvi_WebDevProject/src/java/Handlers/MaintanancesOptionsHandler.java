//package Handlers;
//
//import Support.ConexaoBD;
//import Support.IDAOT;
//import Entities.MaintanancesOptions;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//public class MaintanancesOptionsHandler implements IDAOT<MaintanancesOptions> {
//
//    public static final String _select = 
//            "select maintanance_id, "
//            + "option_id, "
//            + "unit_value "
//            + "from maintanances_options ";
//
//    public static final String _insert = 
//            "insert into maintanances_options "
//            + "(maintanance_id, option_id, unit_value) "
//            + "values "
//            + "(?, ?, ?) "
//            + "on conflict (maintanance_id, option_id) do update set "
//            + "unit_value = excluded.unit_value";
//
//    public static final String _update = 
//            "update set "
//            + "maintanance_id = ?, "
//            + "options_id = ?, "
//            + "unit_values = ?, "
//            + "updated_at = ? ";
//
//    public static final String _delete = 
//            "delete from maintanances_options "
//            + "where maintanance_id = ?";
//    
//    public static final String _upsert = 
//            _insert
//            + " ON CONFLICT (name) DO " 
//            + _update; 
//
//    @Override
//    public boolean Insert(MaintanancesOptions o) {
//         try {
//            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_insert);
//            
//            pst.setInt(1, o.maintanance_id);
//            pst.setInt(2, o.option_id);
//            pst.setDouble(3, o.unit_value);
//            pst.execute();
//            System.out.println("SQL executado!");
//            return true;
//
//        } catch (Exception e) {
//            System.out.println("Erro ao executar upsert em maintanances_options: " + e);
//            return false;
//        }
//    }
//
//    @Override
//    public boolean Update(MaintanancesOptions o) {
//        return true;
//    }
//
//    @Override
//    public boolean Delete(int id) {
//        return DeleteByMaintenanceId(id);
//    }
//
//    public boolean DeleteByMaintenanceId(int maintananceId) {
//        try {
//            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(_delete);
//            
//            pst.setInt(1, maintananceId);
//
//            pst.execute();
//            System.out.println("SQL executado!");
//            return true;
//
//        } catch (Exception e) {
//            System.out.println("Erro ao excluir maintanances_options: " + e);
//            return false;
//        }
//    }
//
//    @Override
//    public ArrayList<MaintanancesOptions> GetAll() {
//        return new ArrayList<MaintanancesOptions>();
//    }
//
//    @Override
//    public ArrayList<MaintanancesOptions> GetByValue(String criteria, String value) {
//        return new ArrayList<MaintanancesOptions>();
//    }
//
//    @Override
//    public MaintanancesOptions GetById(int id) {
//        return new MaintanancesOptions();
//    }
//
//    public ArrayList<MaintanancesOptions> GetByMaintenanceId(int maintananceId) {
//        ArrayList<MaintanancesOptions> maintanancesOptions = new ArrayList<>();
//
//        try {
//            PreparedStatement pst = ConexaoBD.getInstance().getConnection()
//                    .prepareStatement(_select + " where maintanance_id = ?");
//            pst.setInt(1, maintananceId);
//
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                maintanancesOptions.add(new MaintanancesOptions(
//                        rs.getInt("maintanance_id"),
//                        rs.getInt("option_id"),
//                        rs.getDouble("unit_value")));
//            }
//        } catch (Exception e) {
//            System.out.println("Erro ao consultar opções da manutenção: " + e);
//        }
//
//        return maintanancesOptions;
//    }
//}