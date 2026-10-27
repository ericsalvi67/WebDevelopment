<%-- Document : Umbrellas Created on : 6 de set. de 2026, 17:12:05 Author : Salvi
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
      <h2 class="h2">Menu OS Guarda Chuvas</h2>
      <br />

      <input
        class="botao"
        type="button"
        value="Visualizar OS"
        onclick="window.location.href = '../UmbrellasServlet?a=getAll'"
      />
      <input
        class="botao"
        type="button"
        value="Registrar OS"
        onclick="window.location.href = '<%= request.getContextPath() %>/UmbrellasServlet?a=register'"
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
