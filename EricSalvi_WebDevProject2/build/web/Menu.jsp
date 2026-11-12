<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Users"%>
<%
    Users user = (Users) session.getAttribute("user");
    String userEmail = user != null ? user.email : "Usuário X";
    String roleName = (user != null && user.role == 1) ? "Administrador" : "Usuário";
%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
    <title>Menu Principal</title>
    <meta charset="UTF-8">
  </head>

  <body class="body">
    <div class="container" style="max-width: 480px; text-align: center;">
      <h2 class="h2">Menu Principal</h2>
      
      <p style="margin-bottom: 24px; color: #444;">
        Conectado como: <strong><%= userEmail %></strong><t /> (<%= roleName %>)
      </p>

      <div class="menu">
        <input
          class="botao-menu"
          type="button"
          value="Gerenciar Projetos"
          onclick="window.location.href = '<%= request.getContextPath() %>/ProjectsServlet?a=getAll'"
        >

        <input
          class="botao-menu"
          type="button"
          value="Sair (Logout)"
          style="color: #aa2222;"
          onclick="window.location.href = '<%= request.getContextPath() %>/index.jsp'"
        >
      </div>
    </div>
  </body>
</html>
