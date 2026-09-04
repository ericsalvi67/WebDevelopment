<%-- 
    Document   : User
    Created on : 3 de set. de 2026, 21:46:04
    Author     : Salvi
--%>

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
    
    <form class="container" action="sucesso.html" method="post" onsubmit="return validateForm();">
        <h2> Login</h2>
        <br>

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

