<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Projects"%>
<%@page import="Entities.Users"%>
<%
    List<Projects> projectsList = (List<Projects>) request.getAttribute("projectsList");
    Users user = (Users) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Projetos</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
  </head>

  <body class="body">
    <main class="container">
      <h2 class="h2">Projetos</h2>

      <table class="tabela">
        <thead>
          <tr>
            <th>Projeto</th>
            <th>Criado por</th>
            <th>Data de criação</th>
          </tr>
        </thead>
        <tbody>
        <% if (projectsList != null && !projectsList.isEmpty()) {
             for (Projects project : projectsList) { %>
          <tr>
            <td>
              <a href="<%= request.getContextPath() %>/ProjectsServlet?a=getById&id=<%= project.id %>">
                <%= project.name %>
              </a>
            </td>
            <td><%= project.created_by %> </td>
            <td><%= project.created_at == null ? "" : project.created_at %></td>
          </tr>
        <%   }
           } else { %>
          <tr><td colspan="3">Nenhum projeto cadastrado.</td></tr>
        <% } %>
        </tbody>
      </table>

      <input class="botao-dir" type="button" value="Novo Projeto"
             onclick="window.location.href='<%= request.getContextPath() %>/Projects/Register.jsp'">

      <input class="botao" type="button" value="Voltar"
             onclick="window.location.href='<%= request.getContextPath() %>/Menu.jsp'">
    </main>
  </body>
</html>
