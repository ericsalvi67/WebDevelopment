<%-- 
    Document   : Register
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page import="Entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
    <title>Registro de Opções</title>
    <script src="<%= request.getContextPath() %>/Options/Scripts.js"></script>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../OptionsServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Opções</h2>
        <br>

        <div>
          <label for="name">Nome:</label><br>
          <input id="name" type="text" name="name" maxlength="30" required/>
          <br><br>
        </div>
        <div>
          <label for="description">Descrição:</label><br>
          <input id="description" type="text" name="description" maxlength="300" required/>
          <br><br>
        </div>
        <div>
          <input class="botao3" type="submit" value="Registrar" />
        </div>

        <footer>
            <input
                type="button"
                class="botao"
                value="Voltar"
                onclick="window.location.href = 
                        '<%= request.getContextPath() %>/OptionsServlet?a=getAll'"
                />
        </footer>
    </form>     
  </body>
</html>


