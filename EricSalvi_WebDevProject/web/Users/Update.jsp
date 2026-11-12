<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Users"%>
<%
    Users sessionUser = (Users) session.getAttribute("user");
    if (sessionUser == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    Users userEdit = (Users) request.getAttribute("userEdit");
    if (userEdit == null) {
        response.sendRedirect(request.getContextPath() + "/UsersServlet?a=getAll");
        return;
    }

    boolean isAdmin = sessionUser.role == 1;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Usuário</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
        <script src="<%= request.getContextPath() %>/Users/Scripts.js"></script>
    </head>
    <body class="body">

        <form
            class="container"
            action="<%= request.getContextPath() %>/UsersServlet"
            method="post"
            autocomplete="off"
            onsubmit="return verifyUserForm()"
            style="max-width: 480px;"
        >
            <h2 class="h2">Editar Usuário</h2>

            <input type="hidden" name="id" value="<%= userEdit.id %>">

            <div>
                <label for="email">Email:</label><br>
                <input
                    id="email"
                    type="text"
                    name="email"
                    maxlength="60"
                    value="<%= userEdit.email %>"
                    autocomplete="off"
                    required
                >
                <br><br>
            </div>

            <div>
                <label for="password">Senha:</label><br>
                <input
                    id="password"
                    type="password"
                    name="password"
                    maxlength="60"
                    autocomplete="new-password"
                    required
                >
                <br><br>
            </div>

            <div>
                <label for="confirm_password">Confirmar Senha:</label><br>
                <input
                    id="confirm_password"
                    type="password"
                    name="confirm_password"
                    maxlength="60"
                    autocomplete="new-password"
                    required
                >
                <br><br>
            </div>

            <div>
                <label for="role">Role:</label><br>
                <% if (isAdmin) { %>
                <select id="role" name="role" required>
                    <option value="1" <%= userEdit.role == 1 ? "selected" : "" %>>Admin</option>
                    <option value="2" <%= userEdit.role == 2 ? "selected" : "" %>>Usuário</option>
                </select>
                <% } else { %>
                <select id="role-readonly" disabled style="background-color: #f0f0f0; cursor: not-allowed;">
                    <option value="1" <%= userEdit.role == 1 ? "selected" : "" %>>Admin</option>
                    <option value="2" <%= userEdit.role == 2 ? "selected" : "" %>>Usuário</option>
                </select>
                <input type="hidden" id="role" name="role" value="<%= userEdit.role %>">
                <% } %>
                <br><br>
            </div>

            <div class="form-actions">
                <input
                    class="botao"
                    type="button"
                    value="Voltar"
                    onclick="window.location.href = '<%= request.getContextPath() %>/UsersServlet?a=getAll'"
                >
                <input class="botao3" type="submit" value="Salvar Alterações">
            </div>
        </form>
    </body>
</html>
