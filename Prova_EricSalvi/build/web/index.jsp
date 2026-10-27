<%-- Document : index Created on : 3 de set. de 2026, 21:50:35 Author : Salvi
--%> <%@page import="Support.TablesInsertionDB"%> <%@page
contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="styles.css" />
    <title>Menu</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <%-- USE FOR INSERT TABLES AND ADD A TEST USER --%> 
    <%= TablesInsertionDB.CreateTables(true, false) %>
        
    <div class="container menu">
      <h2 class="h2">Menu</h2>
      <br />

      <input
        class="botao"
        type="button"
        value="Guarda Chuvas"
        onclick="window.location.href = 'Umbrellas/Umbrellas.jsp'"
      />
      <input
        class="botao"
        type="button"
        value="Categorias"
        onclick="window.location.href = 'Categories/Categories.jsp'"
      />
    </div>
  </body>
</html>
