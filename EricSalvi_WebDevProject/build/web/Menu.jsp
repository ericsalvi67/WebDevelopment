<%-- Document : Menu Created on : 3 de set. de 2026, 21:47:20 Author : Salvi
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="styles.css" />
    <title>Menu</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <div class="container menu">
      <h2 class="h2">Menu</h2>
      <br />

      <input
        class="botao"
        type="button"
        value="Carros"
        onclick="window.location.href = 'Cars/Cars.jsp'"
      />
      
      <input
        class="botao"
        type="button"
        value="Manutenções"
        onclick="window.location.href = 'Maintanance/Maintanance.jsp'"
      />
      
      <input
        class="botao"
        type="button"
        value="Categorias de Manutenção"
        onclick="window.location.href = 'Categories/Categories.jsp'"
      />
    </div>
  </body>
</html>
