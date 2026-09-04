<%-- 
    Document   : index
    Created on : 3 de set. de 2026, 21:50:35
    Author     : Salvi
--%>

<%@page import="Support.TablesInsertionDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Login</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    
    <form class="container" action="/UserServlet" method="post";">
        <h2> Login</h2>
        <br>
        <%= 
            TablesInsertionDB.CreateTables()
        %>

        <label for="Login">Login:</label>
        <input id="username" type="text" name="username" required/>
        <br><br>

        <label for="Senha">Senha:</label>
        <input id="password" type="password" name="password" required></input>
        <br><br>
    
        <input class="botao" type="submit" value="Enviar" />
    </form>
  </body>
</html>

