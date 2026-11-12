<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Projects"%>
<%@page import="Entities.Users"%>
<%
    List<Projects> projectsList = (List<Projects>) request.getAttribute("projectsList");
    Users user = (Users) session.getAttribute("user");
    boolean isAdmin = user != null && user.role == 1;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Projetos</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
        <script src="<%= request.getContextPath() %>/Projects/Scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Projetos</h2>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Projeto</th>
                        <th>Criado por</th>
                        <th>Data de criação</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (projectsList != null && !projectsList.isEmpty()) {
                            for (Projects project : projectsList) {
                    %>
                    <tr>
                        <td>
                            <%= project.id %>
                        </td>
                        <td>
                            <%= project.name %>
                        </td>
                        <td>
                            <%= project.created_by %>
                        </td>
                        <td>
                            <%= project.created_at == null ? "" : project.created_at %>
                        </td>
                        <td>
                            <a href="ProjectsServlet?a=getById&id=<%= project.id %>">
                                Editar
                            </a>
                        </td>
                        <td>
                            <% if (isAdmin) { %>
                            <input
                                style="color: #ff5050; cursor: pointer;"
                                type="button"
                                value="Excluir"
                                onclick="deleteProject('<%= request.getContextPath() %>', <%= project.id %>)"
                            >
                            <% } else { %>
                            <span style="color: #999;">-</span>
                            <% } %>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6">
                            Nenhum projeto encontrado.
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
                
            <br>
            
            <div class="form-actions">
                <input
                    class="botao"
                    type="button"
                    value="Voltar"
                    onclick="window.location.href = '<%= request.getContextPath() %>/Menu.jsp'"
                >
                
                <% if (isAdmin) { %>
                <input
                    class="botao2"
                    type="button"
                    value="Adicionar"
                    onclick="window.location.href = '<%= request.getContextPath() %>/Projects/Update.jsp'"
                >
                <% } %>
            </div>
        </div>
    </body>
</html>
