<%-- 
    Document   : register_cars
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page import="Entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Registro de Carros</title>
    <script src="Cars/Scripts.js"></script>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../CarsServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Carros</h2>
        <br>

        <div>
          <label for="name">Nome:</label><br>
          <input id="name" type="text" name="name" maxlength="30" required/>
          <br><br>
        </div>
        <div>
          <label for="number_plate">Placa:</label><br>
          <input id="number_plate" type="text" name="number_plate" maxlength="15" required></input>
          <br><br>
        </div>
        <div>
          <label for="color">Cor:</label><br>
          <input id="color" type="text" name="color" maxlength="20" required></input>
          <br><br>
        </div>
        
        <%
            String users_email = (String) session.getAttribute("emailuser");
        %>
        <div>
          <label for="users_email">Email proprietário:</label><br>
          <input id="users_email" type="text" name="users_email" type="" value="<%= users_email %>" readonly></input>
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
                        '<%= request.getContextPath() %>/CarsServlet?a=getAll'"
                />
        </footer>
    </form>     
  </body>
</html>


