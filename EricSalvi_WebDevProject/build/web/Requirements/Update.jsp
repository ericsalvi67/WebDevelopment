<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Projects"%>
<%@page import="Entities.Requirements"%>
<%@page import="Entities.Users"%>
<%!
    private String escapeAttr(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
%>
<%
    Users user = (Users) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    Projects project = (Projects) request.getAttribute("project");
    if (project == null) {
        response.sendRedirect(request.getContextPath() + "/ProjectsServlet?a=getAll");
        return;
    }

    List<Requirements> requirementsList = (List<Requirements>) request.getAttribute("requirementsList");
    boolean isAdmin = user.role == 1;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Requisitos - <%= project.name %></title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
        <script src="<%= request.getContextPath() %>/Requirements/Scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Projeto: <%= project.name %></h2>

            <!-- Sistema de 2 Abas (Projetos e Requisitos separados) -->
            <div class="tab-header">
                <button
                    id="btn-tab-projeto"
                    type="button"
                    class="tab-btn"
                    onclick="window.location.href = '<%= request.getContextPath() %>/ProjectsServlet?a=getById&id=<%= project.id %>'"
                >
                    1. Dados do Projeto
                </button>
                <button
                    id="btn-tab-requisitos"
                    type="button"
                    class="tab-btn active"
                >
                    2. Requisitos
                </button>
            </div>

            <!-- Aba 2: Requisitos do Projeto -->
            <div id="tab-requisitos" class="tab-content">

                <% if (isAdmin) { %>
                <fieldset id="req-fieldset" class="maintenance-options">
                    <legend id="req-form-legend">Adicionar Novo Requisito / Nova Versão</legend>

                    <form id="req-form" action="<%= request.getContextPath() %>/RequirementsServlet" method="post" onsubmit="return verifyRequirementForm()">
                        <input type="hidden" name="project_id" value="<%= project.id %>">
                        <input type="hidden" id="req-id" name="id" value="0">

                        <div class="req-grid-form">
                            <div>
                                <label for="type">Tipo:</label><br>
                                <select id="type" name="type" onchange="updateCodePrefix()" required>
                                    <option value="RF" selected>RF (Funcional)</option>
                                    <option value="RNF">RNF (Não Funcional)</option>
                                </select>
                            </div>

                            <div>
                                <label for="code-number">Código:</label><br>
                                <div class="code-field">
                                    <span id="code-prefix">RF</span>
                                    <input
                                        id="code-number"
                                        type="number"
                                        min="1"
                                        step="1"
                                        placeholder="Ex: 1"
                                        required
                                        oninput="updateCodePreview()"
                                    >
                                    <input id="code" type="hidden" name="code" value="RF001">
                                </div>
                                <small id="code-preview" style="color:#0b4f1c; font-weight:bold;">RF001</small>
                            </div>

                            <div>
                                <label for="version">Versão:</label><br>
                                <input id="version" type="number" name="version" min="1" step="1" value="1" required>
                            </div>

                            <div style="grid-column: span 3;">
                                <label for="requirement">Título do Requisito:</label><br>
                                <input id="requirement" type="text" name="requirement" maxlength="100" required placeholder="Ex: Autenticação de Usuário">
                            </div>

                            <div style="grid-column: span 3;">
                                <label for="description">Descrição detalhada:</label><br>
                                <textarea id="description" name="description" maxlength="255" rows="2" placeholder="Detalhes do requisito..."></textarea>
                            </div>

                            <div>
                                <label for="priority">Prioridade (1 a 5): <output id="priority-val">1</output></label><br>
                                <input
                                    id="priority"
                                    type="range"
                                    name="priority"
                                    min="1"
                                    max="5"
                                    value="1"
                                    oninput="document.getElementById('priority-val').value = value"
                                >
                            </div>

                            <div>
                                <label for="complexity">Complexidade (1 a 5): <output id="complexity-val">1</output></label><br>
                                <input
                                    id="complexity"
                                    type="range"
                                    name="complexity"
                                    min="1"
                                    max="5"
                                    value="1"
                                    oninput="document.getElementById('complexity-val').value = value"
                                >
                            </div>

                            <div style="display: flex; align-items: flex-end; gap: 8px;">
                                <input id="btn-submit-req" class="botao2" type="submit" value="Adicionar Requisito" style="width: 100%;">
                                <input id="btn-cancel-req" class="botao" type="button" value="Cancelar" style="display: none;" onclick="resetRequirementForm()">
                            </div>
                        </div>
                    </form>
                </fieldset>
                <br>
                <% } %>

                <h3 style="margin-bottom: 8px;">Requisitos Cadastrados</h3>

                <div class="filter-bar" style="margin-bottom: 12px; display: flex; gap: 12px; align-items: center;">
                    <label><strong>Filtrar:</strong></label>
                    <select id="filter-priority" onchange="filterRequirements()">
                        <option value="">Todas as Prioridades</option>
                        <option value="1">Prioridade 1</option>
                        <option value="2">Prioridade 2</option>
                        <option value="3">Prioridade 3</option>
                        <option value="4">Prioridade 4</option>
                        <option value="5">Prioridade 5</option>
                    </select>
                    <select id="filter-complexity" onchange="filterRequirements()">
                        <option value="">Todas as Complexidades</option>
                        <option value="1">Complexidade 1</option>
                        <option value="2">Complexidade 2</option>
                        <option value="3">Complexidade 3</option>
                        <option value="4">Complexidade 4</option>
                        <option value="5">Complexidade 5</option>
                    </select>
                </div>

                <table class="tabela">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Tipo</th>
                            <th>Versão</th>
                            <th>Requisito</th>
                            <th>Descrição</th>
                            <th>Prioridade</th>
                            <th>Complexidade</th>
                            <% if (isAdmin) { %>
                            <th class="no-print">Editar</th>
                            <th class="no-print">Excluir</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody id="requirements-table-body">
                        <%
                            if (requirementsList != null && !requirementsList.isEmpty()) {
                                for (Requirements req : requirementsList) {
                        %>
                        <tr
                            data-id="<%= req.id %>"
                            data-code="<%= escapeAttr(req.code) %>"
                            data-type="<%= escapeAttr(req.type) %>"
                            data-version="<%= req.version %>"
                            data-requirement="<%= escapeAttr(req.requirement) %>"
                            data-description="<%= escapeAttr(req.description) %>"
                            data-priority="<%= req.priority %>"
                            data-complexity="<%= req.complexity %>"
                        >
                            <td><strong><%= escapeAttr(req.code) %></strong></td>
                            <td><%= escapeAttr(req.type) %></td>
                            <td>v<%= req.version %></td>
                            <td><%= escapeAttr(req.requirement) %></td>
                            <td><%= escapeAttr(req.description) %></td>
                            <td><%= req.priority %></td>
                            <td><%= req.complexity %></td>
                            <% if (isAdmin) { %>
                            <td class="no-print">
                                <input
                                    style="color: #0b4f1c; font-weight: bold; cursor: pointer;"
                                    type="button"
                                    value="Editar"
                                    onclick="editRequirement(this)"
                                >
                            </td>
                            <td class="no-print">
                                <input
                                    style="color: #ff5050; cursor: pointer;"
                                    type="button"
                                    value="Excluir"
                                    onclick="deleteRequirement('<%= request.getContextPath() %>', <%= req.id %>, <%= project.id %>)"
                                >
                            </td>
                            <% } %>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="<%= isAdmin ? 9 : 7 %>">
                                Nenhum requisito cadastrado para este projeto.
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>

                <br>
                <div class="form-actions">
                    <input
                        class="botao"
                        type="button"
                        value="Voltar"
                        onclick="window.location.href = '<%= request.getContextPath() %>/ProjectsServlet?a=getAll'"
                    >

                    <input
                        class="botao"
                        type="button"
                        value="Imprimir / Salvar PDF"
                        onclick="window.print()"
                    >
                </div>
            </div>

        </div>
    </body>
</html>
