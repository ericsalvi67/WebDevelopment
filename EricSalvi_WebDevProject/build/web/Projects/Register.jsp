<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="Entities.Users"%> <% Users user = (Users) session.getAttribute("user");
String createdBy = user == null ? "" : user.email; %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Novo Projeto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css" />
    <script src="<%= request.getContextPath() %>/Projects/Scripts.js"></script>
  </head>
  <body class="body">
    <main class="container">
      <h2 class="h2">Novo Projeto</h2>
      <form
        action="<%= request.getContextPath() %>/ProjectsServlet"
        method="post"
        onsubmit="return prepareProjectRequirements();"
      >
        <section class="project-fields">
          <div>
            <label for="name">Nome do projeto</label>
            <input id="name" type="text" name="name" maxlength="60" required />
          </div>
          <div>
            <label for="created_by">Criado por</label>
            <input
              id="created_by"
              type="email"
              value="<%= createdBy %>"
              readonly
            />
          </div>
          <div>
            <label for="created_at">Data de criação</label>
            <input
              id="created_at"
              type="text"
              value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>"
              readonly
            />
          </div>
        </section>

        <section class="requirement-entry">
          <div class="requirement-type">
            <label for="type">Tipo de requisito</label>
            <select id="type" onchange="updateCodePrefix()">
              <option value="RF">Requisito funcional</option>
              <option value="RNF">Requisito não funcional</option>
            </select>
          </div>
          <div class="requirement-code">
            <label for="code-number">Código (numérico)</label>
            <div class="code-field">
              <span id="code-prefix">RF</span>
              <input
                id="code-number"
                type="number"
                min="0001"
                step="1"
                oninput="updateCodePreview()"
              />
            </div>
          </div>
          <div class="requirement-version">
            <label for="version">Versão</label>
            <input id="version" type="number" min="1" step="1" value="1" />
          </div>
          <div class="requirement-text">
            <label for="requirement">Requisito</label>
            <input id="requirement" type="text" maxlength="100" />
          </div>
          <div class="requirement-description">
            <label for="description">Descrição</label>
            <textarea id="description" maxlength="255"></textarea>
          </div>
          <div class="requirement-slider">
            <label for="priority"
              >Prioridade <output id="priority-value">1</output></label
            >
            <input
              id="priority"
              type="range"
              min="1"
              max="5"
              value="1"
              oninput="document.getElementById('priority-value').value = value"
            />
            <label for="complexity"
              >Complexidade <output id="complexity-value">1</output></label
            >
            <input
              id="complexity"
              type="range"
              min="1"
              max="5"
              value="1"
              oninput="
                document.getElementById('complexity-value').value = value
              "
            />
          </div>
          <button
            class="botao add-requirement-button"
            type="button"
            onclick="addProjectRequirement()"
          >
            Adicionar
          </button>
        </section>

        <table class="tabela">
          <thead>
            <tr>
              <th>Código</th>
              <th>Versão</th>
              <th>Requisito</th>
              <th>Descrição</th>
              <th>Prioridade</th>
              <th>Complexidade</th>
              <th></th>
            </tr>
          </thead>
          <tbody id="project-requirements"></tbody>
        </table>
        <div id="requirement-fields"></div>
        <div class="project-form-actions">
          <input
            class="botao"
            type="button"
            value="Voltar"
            onclick="
              window.location.href =
                '<%= request.getContextPath() %>/ProjectsServlet?a=getAll'
            "
          />
          <input
            class="botao-dir"
            type="submit"
            value="Registrar"
          />
        </div>
      </form>
    </main>
  </body>
</html>
