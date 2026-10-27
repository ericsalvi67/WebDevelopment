<%-- 
    Document   : register_Umbrellas
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page import="java.util.List"%>
<%@page import="Entity.Categories"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entity.Umbrellas"%>

<%
    Umbrellas umbrella = (Umbrellas) request.getAttribute("umbrella");
    List<Categories> categories = (List<Categories>) request.getAttribute("categoriesList");

%>
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
        <h2> Cadastro de OS para Guarda Chuvas</h2>
        <br>

        <input
            type="hidden"
            name="id"
            value="<%= umbrella.id %>"
        >
        <div>
          <label for="description">Descrição:</label><br>
          <input id="description" type="text" name="description" value="<%= umbrella.description %>" required/>
          <br><br>
        </div>
        <div>
          <label for="price">Valor:</label><br>
          <input id="price" type="number" name="price" value="<%= umbrella.price %>" required></input>
          <br><br>
        </div>
        <div>
          <label for="service_date">Data :</label><br>
          <input id="service_date" type="date" name="service_date" value="<%= umbrella.service_date %>" readonly></input>
          <br><br>
        </div>
        <div>
            <label for="categories_id">Categoria:</label><br>

            <select id="categories_id" name="categories_id" required>

                <%
                    for (Categories category : categories) {
                %>

                    <option
                        value="<%= category.id %>"
                        <%= category.id == umbrella.categories_id ? "selected" : "" %>
                    >
                        <%= category.description %>
                    </option>

                <%
                    }
                %>

            </select>

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
                '<%= request.getContextPath() %>/UmbrellasServlet?a=getAll'"
        />
        </footer>
    </form>     
  </body>
</html>


