package Handlers;

import Entities.Requirements;
import Support.ConexaoBD;
import Support.IDAOT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class RequirementsHandler implements IDAOT<Requirements> {

    public static final String _select =
            "select id, code, type, requirement, description, priority, complexity, version, project_id "
            + "from requirements ";

    public static final String _insert =
            "insert into requirements "
            + "(code, type, requirement, description, priority, complexity, version, project_id) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String _update =
            "update requirements set "
            + "code = ?, " 
            + "type = ?, "
            + "requirement = ?, "
            + "description = ?, "
            + "priority = ?, "
            + "complexity = ?, "
            + "version = ?, "
            + "project_id = ?, "
            + "updated_at = ? "
            + "where id = ?";

    public static final String _delete =
            "delete from requirements where id = ?";

    public static final String _upsert =
            _insert
            + " on conflict (project_id, code, version) do update set "
            + "type = excluded.type, "
            + "requirement = excluded.requirement, "
            + "description = excluded.description, "
            + "priority = excluded.priority, "
            + "complexity = excluded.complexity, "
            + "updated_at = NOW()";

    @Override
    public boolean Insert(Requirements o) {
        try {

            PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                    .prepareStatement(_upsert);

            bind(pst, o);

            pst.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir REQUIREMENTS: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(Requirements o) {
        try {

            PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                    .prepareStatement(_update);

            bind(pst, o);
            pst.setTimestamp(9, Timestamp.from(Instant.now()));
            pst.setInt(10, o.id);

            pst.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar REQUIREMENTS: " + e);
            return false;
        }
    }

    @Override
    public boolean Delete(int id) {
        try {

            PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                    .prepareStatement(_delete);

            pst.setInt(1, id);

            pst.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir REQUIREMENTS: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Requirements> GetAll() {
        return query(_select + "order by project_id, code, version", null);
    }

    @Override
    public ArrayList<Requirements> GetByValue(String criterio, String valor) {
        String sql = _select + "where code ilike ? "
                + "or type ilike ? or requirement ilike ? or description ilike ? "
                + " order by project_id, code, version";

        String pattern = "%" + valor + "%";

        return query(sql, new String[]{pattern, pattern, pattern, pattern});
    }

    @Override
    public Requirements GetById(int id) {
        try {

            PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                    .prepareStatement(_select + "where id = ?");

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            return rs.next() ? toRequirement(rs) : null;
        } catch (Exception e) {
            System.out.println("Erro ao consultar REQUIREMENT por id: " + e);
            return null;
        }
    }
        
    public ArrayList<Requirements> GetByProjectId(int projectId) {
        ArrayList<Requirements> result = new ArrayList<>();
        try {
            
            String sql = _select + "where project_id = ? order by code, version";
            
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
             pst.setInt(1, projectId);

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                result.add(toRequirement(rs));
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar REQUIREMENTS: " + e);
        }
        return result;
    }
 

    private void bind(PreparedStatement pst, Requirements o) throws Exception {
        pst.setString(1, o.code);
        pst.setString(2, o.type);
        pst.setString(3, o.requirement);
        pst.setString(4, o.description);
        pst.setInt(5, o.priority);
        pst.setInt(6, o.complexity);
        pst.setInt(7, o.version);
        pst.setInt(8, o.project_id);
    }

    private ArrayList<Requirements> query(String sql, String[] values) {
        ArrayList<Requirements> result = new ArrayList<>();
        try {

            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(sql);

            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    pst.setString(i + 1, values[i]);
                }
            }

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                result.add(toRequirement(rs));
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar REQUIREMENTS: " + e);
        }
        return result;
    }

    private Requirements toRequirement(ResultSet rs) throws Exception {
        return new 
            Requirements(
                    rs.getInt("id"), 
                    rs.getString("code"),
                    rs.getString("type"), 
                    rs.getString("requirement"),
                    rs.getString("description"), 
                    rs.getInt("priority"),
                    rs.getInt("complexity"), 
                    rs.getInt("version"),
                    rs.getInt("project_id")
            );
                }
}
