<%-- 
    Document   : register_people
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entity.People"%>

<%
    People people = (People) request.getAttribute("person");
%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Registro de Pessoas</title>
    <script src="people_scripts.js"></script>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../PeopleServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Pessoas</h2>
        <br>

        <input
            type="hidden"
            name="id"
            value="<%= people.id %>"
        >
        <div>
          <label for="name">Nome:</label><br>
          <input id="name" type="text" name="name" value="<%= people.name %>" required/>
          <br><br>
        </div>
        <div>
          <label for="email">Email:</label><br>
          <input id="email" type="email" name="email" value="<%= people.email %>" readonly></input>
          <br><br>
        </div>
        <div>
          <label for="phone">Telefone:</label><br>
          <input id="phone" type="text" name="phone" value="<%= people.phone %>" required></input>
          <br><br>
        </div>
        <div>
          <label for="date_of_birth">Data de Nascimento:</label><br>
          <input id="date_of_birth" type="date" name="date_of_birth" value="<%= people.birth %>" required></input>
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
                '<%= request.getContextPath() %>/PeopleServlet?a=getAll'"
        />
        </footer>
    </form>     
  </body>
</html>


