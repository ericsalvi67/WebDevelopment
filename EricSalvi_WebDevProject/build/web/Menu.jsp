<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Users"%>
<%
    Users user = (Users) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    String userEmail = user.email;
    String roleName = (user.role == 1) ? "Administrador" : "Usuário";
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
        Conectado como: <strong><%= userEmail %></strong> (<%= roleName %>)
      </p>

      <div class="menu">
        <input
          class="botao-menu"
          type="button"
          value="Gerenciar Usuários"
          onclick="window.location.href = '<%= request.getContextPath() %>/UsersServlet?a=getAll'"
        >

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
          onclick="window.location.href = '<%= request.getContextPath() %>/UsersServlet?a=logout'"
        >
      </div>
    </div>
  </body>
</html>
