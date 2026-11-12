<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Projects"%>
<%@page import="Entities.Users"%>
<%
    Users user = (Users) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    Projects project = (Projects) request.getAttribute("project");
    if (project == null) {
        response.sendRedirect(request.getContextPath() + "/ProjectsServlet?a=getAll");
        return;
    }

    boolean isAdmin = user.role == 1;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Projeto - <%= project.name %></title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
        <script src="<%= request.getContextPath() %>/Projects/Scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Projeto: <%= project.name %></h2>

            <!-- Sistema de 2 Abas (Projetos e Requisitos separados) -->
            <div class="tab-header">
                <button
                    id="btn-tab-projeto"
                    type="button"
                    class="tab-btn active"
                >
                    1. Dados do Projeto
                </button>
                <button
                    id="btn-tab-requisitos"
                    type="button"
                    class="tab-btn"
                    onclick="window.location.href = '<%= request.getContextPath() %>/RequirementsServlet?project_id=<%= project.id %>'"
                >
                    2. Requisitos
                </button>
            </div>

            <!-- Aba 1: Dados do Projeto -->
            <div id="tab-projeto" class="tab-content">
                <form action="<%= request.getContextPath() %>/ProjectsServlet" method="post" onsubmit="return verifyProjectForm()">
                    <input type="hidden" name="id" value="<%= project.id %>">

                    <div>
                        <label for="name">Nome do projeto:</label><br>
                        <input
                            id="name"
                            type="text"
                            name="name"
                            maxlength="60"
                            value="<%= project.name %>"
                            <%= isAdmin ? "required" : "readonly" %>
                        >
                        <br><br>
                    </div>

                    <div>
                        <label for="created_by">Criado por:</label><br>
                        <input id="created_by" type="text" value="<%= project.created_by %>" readonly>
                        <br><br>
                    </div>

                    <div>
                        <label for="created_at">Data de criação:</label><br>
                        <input id="created_at" type="text" value="<%= project.created_at == null ? "" : project.created_at %>" readonly>
                        <br><br>
                    </div>

                    <div class="form-actions">
                        <input
                            class="botao"
                            type="button"
                            value="Voltar"
                            onclick="window.location.href = '<%= request.getContextPath() %>/ProjectsServlet?a=getAll'"
                        >
                        <% if (isAdmin) { %>
                        <input class="botao3" type="submit" value="Salvar Alterações">
                        <% } %>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
