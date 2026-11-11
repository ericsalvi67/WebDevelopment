<!-- <%-- Document : Update Created on : 6 de set. de 2026, 17:13:39 Author : Salvi
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="Entities.Options"%> <% Options options = (Options)
request.getAttribute("option"); %>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css" />
    <script src="<%= request.getContextPath() %>/Options/Scripts.js"></script>
    <title>Registro de Opções</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form
      class="container"
      action="../OptionsServlet"
      method="post"
      onsubmit="return validateForm();"
    >
      <h2>Cadastro de Opções</h2>
      <br />

      <input type="hidden" name="id" value="<%= options.id %>" />
      <div>
        <label for="name">Nome:</label><br />
        <input
          id="name"
          type="text"
          name="name"
          maxlength="30"
          value="<%= options.name %>"
          readonly
        />
        <br /><br />
      </div>

      <div>
        <label for="description">Descrição:</label><br />
        <input
          id="description"
          type="text"
          name="description"
          maxlength="255"
          value="<%= options.description %>"
        />
        <br /><br />
      </div>

      <div class="form-actions">
        <input
          type="button"
          class="botao"
          value="Voltar"
          onclick="
            window.location.href =
              '<%= request.getContextPath() %>/OptionsServlet?a=getAll'
          "
        />
        <input class="botao3" type="submit" value="Salvar" />
      </div>
    </form>
  </body>
</html> -->
