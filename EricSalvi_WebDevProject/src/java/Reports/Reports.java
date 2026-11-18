package Reports;

import Support.ConexaoBD;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperRunManager;


public class Reports {
    public byte[] GetReports(String origin,int projectId) {
        try {

            String path = null;
            switch (origin){
                case "user":
                    path = "Reports/usuarios.jasper";
                case "projects":
                    path = "Reports/projetos.jasper";
            }
            File reportFile = new File(getClass().getClassLoader().getResource(path).getPath());
            Map params = new HashMap();

            params.put("project_id", projectId);

            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, ConexaoBD.getInstance().getConnection());

            return bytes;
            } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro ao gerar relatorio: " + e);

            return null;
        }
    }
}

