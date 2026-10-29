<%-- 
    Document   : Update
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Cars"%>
<%
    Cars cars = (Cars) request.getAttribute("cars");
%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <script src="Cars/Scripts.js"></script>
    <title>Registro de Carros</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../CarsServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Carros</h2>
        <br>

        <input
            type="hidden"
            name="id"
            value="<%= cars.id %>"
        >
        <div>
          <label for="name">Nome:</label><br>
          <input id="name" type="text" name="name" maxlength="30" value="<%= cars.name %>" required/>
          <br><br>
        </div>
        <div>
          <label for="number_plate">Placa:</label><br>
          <input id="number_plate" type="text" name="number_plate" maxlength="15" value="<%= cars.number_plate %>" required></input>
          <br><br>
        </div>
        <div>
          <label for="color">Cor:</label><br>
          <input id="color" type="text" name="color" maxlength="20" value="<%= cars.color %>" required></input>
          <br><br>
        </div>
        
        <div>
          <label for="users_email">Email proprietário:</label><br>
          <input id="users_email" type="text" name="users_email" type="" value="<%= cars.users_email %>" readonly></input>
          <br><br>
        </div>
          
        <div>
          <input class="botao" type="submit" value="Salvar" />
        </div>

        <footer>
            <input
            type="button"
            value="Voltar"
            onclick="window.location.href =
                '<%= request.getContextPath() %>/CarsServlet?a=getAll'"
        />
        </footer>
    </form>     
  </body>
</html>


