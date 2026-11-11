<%-- Document : Menu Created on : 3 de set. de 2026, 21:47:20 Author : Salvi
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css" />
    <title>Menu</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <div class="container menu">
      <h2 class="h2">Menu</h2>

      <input
        class="botao-menu"
        type="button"
        value="Projetos"
        onclick="
          window.location.href =
            '<%= request.getContextPath() %>/ProjectsServlet?a=getAll'
        "
      />

      <input
        class="botao-menu"
        type="button"
        value="Relatórios"
        onclick="alert('A tela de relatórios ainda não foi implementada.')"
      />
    </div>
  </body>
</html>
