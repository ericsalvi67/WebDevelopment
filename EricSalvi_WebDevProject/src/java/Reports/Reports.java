package Reports;

import Support.ConexaoBD;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperRunManager;


public class Reports {
    public byte[] GetReports(String type,int id) {
        Map<String, Object> params = new HashMap<>();

        try {
            String path = null;
            switch (type){
                case "user":
                    path = "Reports/users.jasper";
                    break;
                case "projects":
                    params.put("project_id", id);
                    path = "Reports/projetos.jasper";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }
            File reportFile = new File(getClass().getClassLoader().getResource(path).toURI().getPath());
            

            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, ConexaoBD.getInstance().getConnection());

            return bytes;
            } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro ao gerar relatorio: " + e);

            return null;
        }
    }
}

