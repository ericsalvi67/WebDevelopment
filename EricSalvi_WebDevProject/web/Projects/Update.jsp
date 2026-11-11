<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Projects"%>
<%@page import="Entities.Requirements"%>
<%@page import="Entities.Users"%>
<%
    Projects project = (Projects) request.getAttribute("project");
    List<Requirements> requirementsList =
            (List<Requirements>) request.getAttribute("requirementsList");
    Users user = (Users) session.getAttribute("user");
    boolean isAdmin = user != null && user.role == 1;
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Projeto - <%= project.name %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
    <script src="<%= request.getContextPath() %>/Projects/Scripts.js"></script>
  </head>
  <body class="body">
    <main class="container project-detail">
      <div class="detail-header">
        <h2 class="h2">Projeto</h2>
        <% if (isAdmin) { %>
        <button id="edit-project" class="botao" type="button"
                onclick="toggleProjectEdit()">Editar projeto</button>
        <% } %>
      </div>

      <form action="<%= request.getContextPath() %>/ProjectsServlet" method="post">
        <input type="hidden" name="id" value="<%= project.id %>">
        <label for="project-name">Nome</label>
        <input id="project-name" type="text" name="name" value="<%= project.name %>"
               maxlength="60" <%= isAdmin ? "disabled" : "readonly" %> required>
        <label for="creator-email">Criado por</label>
        <input id="creator-email" type="email" value="<%= project.created_by %>" readonly>
        <label for="created-at">Data de criação</label>
        <input id="created-at" type="text"
               value="<%= project.created_at == null ? "" : project.created_at %>" readonly>
        <% if (isAdmin) { %>
        <div id="project-actions" class="form-actions" hidden>
          <button class="botao" type="button"
                  onclick="window.location.href='<%= request.getContextPath() %>/ProjectsServlet?a=getAll'">
            Cancelar
          </button>
          <button class="botao2" type="submit">Salvar projeto</button>
        </div>
        <% } %>
      </form>

      <section class="requirements-section">
        <h2 class="h2">Requisitos</h2>
        <form action="<%= request.getContextPath() %>/RequirementsServlet"
              method="post" onsubmit="prepareRequirementCode()">
          <input type="hidden" name="project_id" value="<%= project.id %>">
          <label for="type">Tipo</label>
          <select id="type" name="type" onchange="updateCodePrefix()" required>
            <option value="RF">Requisito funcional</option>
            <option value="RNF">Requisito não funcional</option>
          </select>
          <label for="code-number">Código</label>
          <div class="code-field">
            <span id="code-prefix">RF</span>
            <input id="code-number" type="number" min="1" step="1" required
                   oninput="updateCodePreview()">
            <input id="code" type="hidden" name="code">
          </div>
          <small id="code-preview">RF</small>

          <label for="requirement">Requirement</label>
          <input id="requirement" type="text" name="requirement" maxlength="100" required>
          <label for="description">Description (opcional)</label>
          <textarea id="description" name="description" maxlength="255"></textarea>

          <div class="range-grid">
            <label for="priority">Priority: <output id="priority-value">1</output></label>
            <input id="priority" type="range" name="priority" min="1" max="5" value="1"
                   oninput="document.getElementById('priority-value').value = value">
            <label for="complexity">Complexity: <output id="complexity-value">1</output></label>
            <input id="complexity" type="range" name="complexity" min="1" max="5" value="1"
                   oninput="document.getElementById('complexity-value').value = value">
          </div>
          <label for="version">Version</label>
          <input id="version" type="number" name="version" min="1" step="1" value="1" required>
          <button class="botao3" type="submit">Adicionar requisito</button>
        </form>

        <table class="tabela">
          <thead>
            <tr>
              <th>Código</th><th>Tipo</th><th>Requirement</th><th>Description</th>
              <th>Priority</th><th>Complexity</th><th>Version</th>
            </tr>
          </thead>
          <tbody>
          <% if (requirementsList != null) {
               for (Requirements requirement : requirementsList) { %>
            <tr>
              <td><%= requirement.code %></td>
              <td><%= requirement.type %></td>
              <td><%= requirement.requirement %></td>
              <td><%= requirement.description == null ? "" : requirement.description %></td>
              <td><%= requirement.priority %></td>
              <td><%= requirement.complexity %></td>
              <td><%= requirement.version %></td>
            </tr>
          <%   }
             } %>
          </tbody>
        </table>
      </section>

      <input class="botao" type="button" value="Voltar"
             onclick="window.location.href='<%= request.getContextPath() %>/ProjectsServlet?a=getAll'">
    </main>
  </body>
</html>
