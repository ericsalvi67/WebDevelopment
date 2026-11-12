package Handlers;

import Entities.Projects;
import Support.ConexaoBD;
import Support.IDAOT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class ProjectsHandler implements IDAOT<Projects> {

    public static final String _select =
            "select id, name, created_by, created_at from projects ";

    public static final String _insert =
            "insert into projects (name, created_by, created_at) values (?, ?, ?)";

    public static final String _update =
            "update projects set name = ?, updated_at = ? where id = ?";

    public static final String _delete =
            "delete from projects where id = ?";

    public static final String _upsert =
            _insert
            + " on conflict (name) do update set "
            + "updated_at = NOW()";

    @Override
    public boolean Insert(Projects o) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_upsert)) {

            pst.setString(1, o.name);
            pst.setString(2, o.created_by);
            pst.setTimestamp(3, o.created_at != null ? o.created_at : Timestamp.from(Instant.now()));

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao inserir PROJECTS: " + e);
            return false;
        }
    }

    @Override
    public boolean Update(Projects o) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_update)) {

            pst.setString(1, o.name);
            pst.setTimestamp(2, Timestamp.from(Instant.now()));
            pst.setInt(3, o.id);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar PROJECTS: " + e);
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
            System.out.println("Erro ao excluir PROJECTS: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Projects> GetAll() {
        return query(_select + "order by name", null);
    }

    @Override
    public ArrayList<Projects> GetByValue(String criterio, String valor) {
        String sql = _select + "where name ilike ? order by name";
        String pattern = "%" + valor + "%";
        return query(sql, new String[]{pattern});
    }

    @Override
    public Projects GetById(int id) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_select + "where id = ?")) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? toProject(rs) : null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar PROJECT por id: " + e);
            return null;
        }
    }

    public Projects GetByName(String name) {
        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection()
                .prepareStatement(_select + "where name = ?")) {

            pst.setString(1, name);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? toProject(rs) : null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar PROJECT por nome: " + e);
            return null;
        }
    }

    private ArrayList<Projects> query(String sql, String[] values) {
        ArrayList<Projects> result = new ArrayList<>();

        try (PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    pst.setString(i + 1, values[i]);
                }
            }

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    result.add(toProject(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar PROJECTS: " + e);
        }

        return result;
    }

    private Projects toProject(ResultSet rs) throws Exception {
        return new Projects(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("created_by"),
                rs.getTimestamp("created_at")
        );
    }
}
