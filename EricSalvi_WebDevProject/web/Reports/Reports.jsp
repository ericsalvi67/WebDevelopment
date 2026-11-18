<%-- 
    Document   : Reports
    Created on : 17 de nov. de 2026, 21:47:06
    Author     : Salvi
--%>
<%@page import="Reports.Reports"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reports</title>
    </head>
    <body>
        <%
            String where = request.getParameter("project_id");
            int projectId = Integer.parseInt(request.getParameter("project_id"));
            
            byte[] bytes = new Reports().GetReports(projectId);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
        </body>
</html>
