<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Erro</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
  </head>
  <body class="body">
    <div class="error">
      <h2 style="color: #cc0000; text-align: center; margin-top: 0;">Erro na Operação</h2>
      <p style="text-align: center;">Ocorreu uma falha ao processar a solicitação ou as credenciais/permissões são inválidas.</p>
      <br>
      <div style="text-align: center;">
        <input
          class="botao"
          type="button"
          value="Voltar ao Início"
          onclick="window.location.href = '<%= request.getContextPath() %>/index.jsp'"
        >
      </div>
    </div>
  </body>
</html>
