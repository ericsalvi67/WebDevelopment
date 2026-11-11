<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Projects"%>
<%@page import="Entities.Requirements"%>
<%@page import="Entities.Users"%>
<%
    Projects project = (Projects) request.getAttribute("project");
    List<Requirements> requirementsList = (List<Requirements>) request.getAttribute("requirementsList");
    Users user = (Users) session.getAttribute("user");
    boolean isAdmin = user != null && user.role == 1;
    String activeTab = request.getParameter("tab");
    boolean startOnReqTab = "req".equals(activeTab);
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

        <div class="container">
            <h2 class="h2">Projeto: <%= project.name %></h2>

            <!-- Sistema de 2 Abas -->
            <div class="tab-header">
                <button
                    id="btn-tab-projeto"
                    type="button"
                    class="tab-btn <%= !startOnReqTab ? "active" : "" %>"
                    onclick="switchTab('tab-projeto')"
                >
                    1. Dados do Projeto
                </button>
                <button
                    id="btn-tab-requisitos"
                    type="button"
                    class="tab-btn <%= startOnReqTab ? "active" : "" %>"
                    onclick="switchTab('tab-requisitos')"
                >
                    2. Requisitos
                </button>
            </div>

            <!-- Aba 1: Dados do Projeto -->
            <div id="tab-projeto" class="tab-content" style="<%= !startOnReqTab ? "display:block;" : "display:none;" %>">
                <form action="<%= request.getContextPath() %>/ProjectsServlet" method="post" onsubmit="return verifyProjectForm()">
                    <input type="hidden" name="id" value="<%= project.id %>">

                    <div>
                        <label for="name">Nome do projeto:</label><br>
                        <input
                            id="name"
                            type="text"
                            name="name"
                            maxlength="60"
                            value="<%= project.name %>"
                            <%= isAdmin ? "required" : "readonly" %>
                        >
                        <br><br>
                    </div>

                    <div>
                        <label for="created_by">Criado por:</label><br>
                        <input id="created_by" type="text" value="<%= project.created_by %>" readonly>
                        <br><br>
                    </div>

                    <div>
                        <label for="created_at">Data de criação:</label><br>
                        <input id="created_at" type="text" value="<%= project.created_at == null ? "" : project.created_at %>" readonly>
                        <br><br>
                    </div>

                    <div class="form-actions">
                        <input
                            class="botao"
                            type="button"
                            value="Voltar"
                            onclick="window.location.href = '<%= request.getContextPath() %>/ProjectsServlet?a=getAll'"
                        >
                        <% if (isAdmin) { %>
                        <input class="botao3" type="submit" value="Salvar Alterações">
                        <% } %>
                    </div>
                </form>
            </div>

            <!-- Aba 2: Requisitos do Projeto -->
            <div id="tab-requisitos" class="tab-content" style="<%= startOnReqTab ? "display:block;" : "display:none;" %>">

                <% if (isAdmin) { %>
                <!-- Formulário para adicionar requisito neste projeto -->
                <fieldset class="maintenance-options">
                    <legend>Adicionar Novo Requisito / Nova Versão</legend>

                    <form action="<%= request.getContextPath() %>/RequirementsServlet" method="post" onsubmit="return verifyRequirementForm()">
                        <input type="hidden" name="project_id" value="<%= project.id %>">
                        <input type="hidden" name="id" value="0">

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

                            <div style="display: flex; align-items: flex-end;">
                                <input class="botao2" type="submit" value="Adicionar Requisito" style="width: 100%;">
                            </div>
                        </div>
                    </form>
                </fieldset>
                <br>
                <% } %>

                <!-- Lista de Requisitos Cadastrados -->
                <h3 style="margin-bottom: 8px;">Requisitos Cadastrados</h3>

                <!-- Filtro de Requisitos (RF9) -->
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
                            <% if (isAdmin) { %><th>Excluir</th><% } %>
                        </tr>
                    </thead>
                    <tbody id="requirements-table-body">
                        <%
                            if (requirementsList != null && !requirementsList.isEmpty()) {
                                for (Requirements req : requirementsList) {
                        %>
                        <tr data-priority="<%= req.priority %>" data-complexity="<%= req.complexity %>">
                            <td><strong><%= req.code %></strong></td>
                            <td><%= req.type %></td>
                            <td>v<%= req.version %></td>
                            <td><%= req.requirement %></td>
                            <td><%= req.description == null ? "" : req.description %></td>
                            <td><%= req.priority %></td>
                            <td><%= req.complexity %></td>
                            <% if (isAdmin) { %>
                            <td>
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
                            <td colspan="<%= isAdmin ? 8 : 7 %>">
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

                    <!-- Botão de Impressão / Gerar PDF (RF10) -->
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
