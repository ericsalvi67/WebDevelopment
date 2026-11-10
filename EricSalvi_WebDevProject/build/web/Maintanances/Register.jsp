<%-- 
    Document   : Register
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page import="Entities.Users"%>
<%@page import="Entities.Cars"%>
<%@page import="Handlers.CarsHandler"%>
<%@page import="Entities.Options"%>
<%@page import="Handlers.OptionsHandler"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String usersEmail = (String) session.getAttribute("emailuser");
    ArrayList<Cars> cars = new CarsHandler().GetByUserEmail(usersEmail);
    ArrayList<Options> options = new OptionsHandler().GetAll();
%>

<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
    <title>Registro de Manutenções</title>
    <script src="<%= request.getContextPath() %>/Maintanances/Scripts.js"></script>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../MaintanancesServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Manutenções</h2>
        <br>

        <div>
          <label for="name">Nome:</label><br>
          <input id="name" type="text" name="name" maxlength="30" required/>
          <br><br>
        </div>
        <div>
          <label for="car_plate">Placa:</label><br>
          <select id="car_plate" name="car_plate" required>
            <option value="" disabled selected>Selecione uma placa</option>
          <%
              for (Cars car : cars) {
          %>
            <option value="<%= car.number_plate %>"><%= car.number_plate %></option>
          <%
              }
          %>
          </select>
          <br><br>
        </div>
        <div>
          <label for="done_by">Realizado por:</label><br>
          <input id="done_by" type="text" name="done_by" maxlength="100"></input>
          <br><br>
        </div>
        <div>
          <label for="done_at">Data:</label><br>
          <input id="done_at" type="date" name="done_at"></input>
          <br><br>
        </div>
        <div>
          <label for="observations">Observação:</label><br>
          <input id="observations" type="text" name="observations" maxlength="500"></input>
        </div>
        <br><br>
        <fieldset class="maintenance-options">
          <legend>Opções da manutenção</legend>
          <label for="option_select">Opção:</label><br>
          <select id="option_select">
            <option value="" selected>Selecione uma opção</option>
            <%
                for (Options option : options) {
            %>
            <option value="<%= option.id %>" data-name="<%= option.name %>" data-description="<%= option.description %>">
              <%= option.name %>
            </option>
            <%
                }
            %>
          </select>
          <input id="option_value" type="number" step="0.01" min="0" placeholder="Valor unitário">
          <button type="button" onclick="addMaintenanceOption()">Adicionar</button>
          <table class="tabela">
            <thead>
              <tr>
                <th>Opção</th>
                <th>Descrição</th>
                <th>Valor unitário</th>
                <th>Ação</th>
              </tr>
            </thead>
            <tbody id="selected_options"></tbody>
          </table>
          <br><br>
        </fieldset>
        <div class="form-actions">
            <input
                type="button"
                class="botao"
                value="Voltar"
                onclick="window.location.href = 
                        '<%= request.getContextPath() %>/MaintanancesServlet?a=getAll'"
                />
            <input class="botao3" type="submit" value="Registrar" />
        </div>
    </form>     
  </body>
</html>
