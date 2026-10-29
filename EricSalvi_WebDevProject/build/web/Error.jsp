<%-- Document : Error Created on : 3 de set. de 2026, 22:25:41 Author : Salvi
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Error</title>
    <link rel="stylesheet" href="../styles.css" />
  </head>

  (HttpServletResponse) result = (HttpServletResponse)
  request.getAttribute("response");

  <body class="error">
    <h1>Erro ao executar</h1>
    <div class="error-message">
      <p><strong>Erro:</strong> <%= result.getStatus() %></p>
      <p><strong>Mensagem:</strong> <%= result.getErrorMessage() %></p>
    </div>

    <footer>
      <input
        class="botao"
        type="button"
        value="Voltar"
        onclick="window.location.href = '../'"
      />
    </footer>
  </body>
</html>
