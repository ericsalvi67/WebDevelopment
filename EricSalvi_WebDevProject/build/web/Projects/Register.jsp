<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Users"%>
<%
    Users user = (Users) session.getAttribute("user");
    String userEmail = (user != null) ? user.email : "";
    String today = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Projeto</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
        <script src="<%= request.getContextPath() %>/Projects/Scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Cadastro de Projetos</h2>

            <!-- Sistema de 2 Abas -->
            <div class="tab-header">
                <button type="button" class="tab-btn active" onclick="switchTab('tab-projeto')">
                    1. Dados do Projeto
                </button>
                <button type="button" class="tab-btn disabled" title="Disponível após registrar o projeto">
                    2. Requisitos (Bloqueado)
                </button>
            </div>

            <!-- Aba 1: Cadastro do Projeto -->
            <div id="tab-projeto" class="tab-content active">
                <form action="<%= request.getContextPath() %>/ProjectsServlet" method="post" onsubmit="return verifyProjectForm()">
                    <input type="hidden" name="id" value="0">

                    <div>
                        <label for="name">Nome do projeto:</label><br>
                        <input id="name" type="text" name="name" maxlength="60" required placeholder="Ex: Sistema de Vendas">
                        <br><br>
                    </div>

                    <div>
                        <label for="created_by">Criado por:</label><br>
                        <input id="created_by" type="text" value="<%= userEmail %>" readonly>
                        <br><br>
                    </div>

                    <div>
                        <label for="created_at">Data de criação:</label><br>
                        <input id="created_at" type="text" value="<%= today %>" readonly>
                        <br><br>
                    </div>

                    <div class="form-actions">
                        <input
                            class="botao"
                            type="button"
                            value="Voltar"
                            onclick="window.location.href = '<%= request.getContextPath() %>/ProjectsServlet?a=getAll'"
                        >
                        <input class="botao3" type="submit" value="Registrar Projeto">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
