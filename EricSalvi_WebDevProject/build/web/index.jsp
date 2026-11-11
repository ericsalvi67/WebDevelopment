<%-- Document : index Created on : 3 de set. de 2026, 21:50:35 Author : Salvi
--%> <%@page import="Support.TablesInsertionDB"%> <%@page
contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css" />
    <title>Login</title>
    <meta charset="UTF-8" />
  </head>
  <body class="body">
    <%-- USE FOR INSERT TABLES AND ADD A TEST USER --%> <%=
    TablesInsertionDB.CreateTables(true) %>

    <form
      class="container"
      action="UsersServlet"
      method="post"
      autocomplete="off"
    >
      <h2 class="h2">Login</h2>
      <br />

      <label for="email">Email:</label><br />
      <input
        id="email"
        type="text"
        name="email"
        value=""
        autocomplete="new-password"
        required
      />
      <br /><br />

      <label for="Senha">Senha:</label><br />
      <input
        id="password"
        type="password"
        name="password"
        value=""
        autocomplete="new-password"
        required
      />
      <br /><br /><br /><br />

      <input class="botao-menu" type="submit" value="Enviar" />
    </form>
  </body>
</html>
