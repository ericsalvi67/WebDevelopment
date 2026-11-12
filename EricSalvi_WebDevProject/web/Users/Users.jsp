<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Users"%>
<%
    Users sessionUser = (Users) session.getAttribute("user");
    if (sessionUser == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    List<Users> usersList = (List<Users>) request.getAttribute("usersList");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Usuários</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
        <script src="<%= request.getContextPath() %>/Users/Scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Usuários</h2>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (usersList != null && !usersList.isEmpty()) {
                            for (Users u : usersList) {
                    %>
                    <tr>
                        <td>
                            <%= u.id %>
                        </td>
                        <td>
                            <%= u.email %>
                        </td>
                        <td>
                            <%= u.role == 1 ? "Admin" : "Usuário" %>
                        </td>
                        <td>
                            <a href="<%= request.getContextPath() %>/UsersServlet?a=getById&id=<%= u.id %>">
                                Editar
                            </a>
                        </td>
                        <td>
                            <input
                                style="color: #ff5050; cursor: pointer;"
                                type="button"
                                value="Excluir"
                                onclick="deleteUser('<%= request.getContextPath() %>', <%= u.id %>)"
                            >
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5">
                            Nenhum usuário encontrado.
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
            </div>
        </div>
    </body>
</html>
