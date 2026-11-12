<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Support.TablesInsertionDB"%>

<%-- TablesInsertionDB.CreateTables(true) --%>

<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
    <title>Login</title>
    <meta charset="UTF-8">
  </head> 
  <body class="body">
    <form
      class="container"
      action="UsersServlet"
      method="post"
      autocomplete="off"
      style="max-width: 440px;"
    >
      <h2 class="h2">LOGIN</h2>

      <div>
        <label for="email">Email:</label><br>
        <input
          id="email"
          type="text"
          name="email"
          value="admin"
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
          value="admin"
          autocomplete="new-password"
          required
        >
        <br><br>
      </div>

      <div style="margin-top: 10px;">
        <input class="botao3" type="submit" value="Entrar">
      </div>
    </form>
  </body>
</html>
