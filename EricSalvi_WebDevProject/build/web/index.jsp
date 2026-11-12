<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Support.TablesInsertionDB"%>
<%--
    TablesInsertionDB.CreateTables(true);
--%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
    <script src="<%= request.getContextPath() %>/Users/Scripts.js"></script>
    <title>Login</title>
    <meta charset="UTF-8">
  </head> 
  <body class="body">
    <form
      class="container"
      action="<%= request.getContextPath() %>/UsersServlet"
      method="post"
      autocomplete="off"
      onsubmit="return verifyLoginForm()"
      style="max-width: 440px;"
    >
      <h2 class="h2">LOGIN</h2>

      <div>
        <label for="email">Email:</label><br>
        <input
          id="email"
          type="text"
          name="email"
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
          autocomplete="new-password"
          required
        >
        <br><br>
      </div>

      <div class="form-actions">
        <input
          class="botao"
          type="button"
          value="Registrar-se"
          onclick="window.location.href = '<%= request.getContextPath() %>/Users/Register.jsp'"
        >
        <input class="botao3" type="submit" value="Entrar">
      </div>
    </form>
  </body>
</html>
