<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Usuário</title>
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
            <h2 class="h2">Cadastro de Usuário</h2>

            <input type="hidden" name="id" value="0">
            <input type="hidden" id="role" name="role" value="2">

            <div>
                <label for="email">Email:</label><br>
                <input
                    id="email"
                    type="text"
                    name="email"
                    maxlength="60"
                    placeholder="Ex: usuario@email.com"
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

            <div class="form-actions">
                <input
                    class="botao"
                    type="button"
                    value="Voltar"
                    onclick="window.location.href = '<%= request.getContextPath() %>/index.jsp'"
                >
                <input class="botao3" type="submit" value="Registrar">
            </div>
        </form>
    </body>
</html>
