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
    <title>Registro de Manutenções</title>
    <script src="<%= request.getContextPath() %>/Maintanances/Scripts.js"></script>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../MaintanancesServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Carros</h2>
        <br>

        <div>
          <label for="name">Nome:</label><br>
          <input id="name" type="text" name="name" maxlength="30" required/>
          <br><br>
        </div>
        <div>
          <label for="car_plate">Placa:</label><br>
          <input id="car_plate" type="text" name="car_plate" maxlength="15" required></input>
          <br><br>
        </div>
        <div>
          <label for="done_by">Realizado por:</label><br>
          <input id="done_by" type="text" name="done_by" maxlength="100"></input>
          <br><br>
        </div>
        <div>
          <label for="done_at">Data:</label><br>
          <input id="done_at" type="date" name="done_at"></input>
          <br><br>
        </div>
        <div>
          <label for="observations">Observação:</label><br>
          <input id="observations" type="text" name="observations" maxlength="500"></input>
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
                        '<%= request.getContextPath() %>/MaintanancesServlet?a=getAll'"
                />
        </footer>
    </form>     
  </body>
</html>


