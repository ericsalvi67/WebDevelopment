<%-- 
    Document   : register_Categories
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Registro de Categorias</title>
    <script src="Categories_scripts.js"></script>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../CategoriesServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Categorias</h2>
        <br>

        <div>
          <label for="description">Descrição:</label><br>
          <input id="description" type="text" name="description" required/>
          <br><br>
        </div>
        
        <div>
          <input class="botao" type="submit" value="Registrar" />
        </div>

        <footer>
            <input
                type="button"
                value="Voltar"
                onclick="window.location.href = 'Categories.jsp'"
                />
        </footer>
    </form>     
  </body>
</html>


