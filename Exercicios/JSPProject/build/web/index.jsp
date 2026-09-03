<%-- 
    Document   : index
    Created on : 29 de ago. de 2026, 21:44:32
    Author     : Salvi
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            int idade = 25;
        %>
        
        <%!
            String getDate(){
                return LocalDateTime.now().toString();
            }
        %>
        
        <p>Idade do macaco: <%= idade %> <p/>
        <p>Data atual: <%= getDate() %><p/>
        
        <%
            for(int i = 0; i < 5; i++){
        %>
        <p>DATA <%= i+1 %> ATUAL: <%= getDate() %> <p/>
        <%
            }
        %>
        
        
        <footer>
            <input
              type="button"
              value="Teste"
              onclick="window.location.href = 'teste.jsp'"
            />
        </footer>
    </body>
</html>
