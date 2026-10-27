<%-- 
    Document   : register_Categories
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entity.Categories"%>

<%
    Categories category = (Categories) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Registro de Pessoas</title>
    <script src="Categories_scripts.js"></script>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../CategoriesServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Pessoas</h2>
        <br>

        <input
            type="hidden"
            name="id"
            value="<%= category.id %>"
        >
        <div>
          <label for="description">Descrição:</label><br>
          <input id="description" type="text" name="description" value="<%= category.description %>" required/>
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
                '<%= request.getContextPath() %>/CategoriesServlet?a=getAll'"
        />
        </footer>
    </form>     
  </body>
</html>


