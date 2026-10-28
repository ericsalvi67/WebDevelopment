<%-- Document : index Created on : 3 de set. de 2026, 21:50:35 Author : Salvi
--%> <%@page import="Support.TablesInsertionDB"%> <%@page
contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Login</title>
    <meta charset="UTF-8" />
  </head>
  <body class="body">
    <%-- USE FOR INSERT TABLES AND ADD A TEST USER --%> <%=
    TablesInsertionDB.CreateTables(false, false) %>

    <form
      class="container"
      action="UsersServlet"
      method="post"
      autocomplete="off"
    >
      <h2 class="h2">Login</h2>
      <br />

      <label for="email">Email:</label>
      <input
        id="email"
        type="text"
        name="email"
        value=""
        autocomplete="new-password"
        required
      />
      <br /><br />

      <label for="Senha">Senha:</label>
      <input
        id="password"
        type="password"
        name="password"
        value=""
        autocomplete="new-password"
        required
      />
      <br /><br />

      <input class="botao" type="submit" value="Enviar" />
    </form>
  </body>
</html>
