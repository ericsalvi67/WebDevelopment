package Reports;

import Support.ConexaoBD;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperRunManager;


public class Reports {
    public byte[] GetReports(int projectId) {
        try {
            java.sql.Connection conn = ConexaoBD.getInstance().getConnection();

            File reportFile = new File(getClass().getClassLoader().getResource("Reports/projetos.jasper").getPath());
            Map params = new HashMap();

            params.put("project_id", projectId);

            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, conn);

            return bytes;
            } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro ao gerar relatorio: " + e);

            return null;
        }
    }
}

