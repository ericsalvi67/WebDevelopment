<%-- 
    Document   : people
    Created on : 6 de set. de 2026, 17:12:05
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Menu</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    
    <div class="container menu">
        <h2 class="h2" > Menu Pessoas</h2>
        <br>

        <input class="botao" type="button" value="Visualizar Pessoa" onclick="window.location.href = 'select_people.jsp'" />
        <input class="botao" type="button" value="Registrar Pessoa" onclick="window.location.href = 'register_people.jsp'" />

        <footer>
            <input
                type="button"
                value="Voltar"
                onclick="window.location.href = '../index.html'"
                />
        </footer>
    </div>
  </body>
</html>

