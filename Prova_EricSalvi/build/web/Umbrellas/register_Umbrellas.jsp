<%-- 
    Document   : register_Umbrellas
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page import="Entity.Categories"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Registro de OS</title>
    <script src="Umbrellas_scripts.js"></script>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../UmbrellasServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de OS</h2>
        <br>

        <div>
          <label for="description">Descrição:</label><br>
          <input id="description" type="text" name="description" required/>
          <br><br>
        </div>
        <div>
          <label for="price">Valor:</label><br>
          <input id="price" type="number" min="0" max="100" step="0.01" name="price" required></input>
          <br><br>
        </div>
        <div>
            <label for="categories_id">Categoria:</label><br>

            <select id="categories_id" name="categories_id" required>

                <option value="" disabled selected>
                    Selecione uma categoria
                </option>
                <%
                    List<Categories> categories =
                        (List<Categories>) request.getAttribute("categoriesList");
                    
                    out.println("Categorias: " + categories);
                    
                    if (categories != null) {
                        for (Categories category : categories) {
                %>
                    <option value="<%= category.id %>">
                        <%= category.description %>
                    </option>
                <%
                        }
                    }
                %>
            </select>
            <br><br>
        </div>
        
        <div>
          <input class="botao" type="submit" value="Registrar" />
        </div>

        <footer>
            <input
                type="button"
                value="Voltar"
                onclick="window.location.href = 'Umbrellas/Umbrellas.jsp'"
                />
        </footer>
    </form>     
  </body>
</html>


