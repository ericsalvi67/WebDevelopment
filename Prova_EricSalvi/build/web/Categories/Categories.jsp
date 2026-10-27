<%-- Document : Categories Created on : 6 de set. de 2026, 17:12:05 Author : Salvi
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../styles.css" />
    <title>Menu</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <div class="container menu">
      <h2 class="h2">Menu Categorias</h2>
      <br />

      <input
        class="botao"
        type="button"
        value="Visualizar Categorias"
        onclick="window.location.href = '../CategoriesServlet?a=getAll'"
      />
      <input
        class="botao"
        type="button"
        value="Registrar Categorias"
        onclick="window.location.href = 'register_Categories.jsp'"
      />

      <footer>
        <input
          type="button"
          value="Voltar"
          onclick="window.location.href = '../index.jsp'"
        />
      </footer>
    </div>
  </body>
</html>
