<!-- <%-- 
    Document   : Update
    Created on : 6 de set. de 2026, 17:13:39
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Maintanances"%>
<%@page import="Entities.Cars"%>
<%@page import="Handlers.CarsHandler"%>
<%@page import="Entities.Options"%>
<%@page import="Handlers.OptionsHandler"%>
<%@page import="Entities.MaintanancesOptions"%>
<%@page import="Handlers.MaintanancesOptionsHandler"%>
<%@page import="java.util.ArrayList"%>

<%
    Maintanances maintanances = (Maintanances) request.getAttribute("maintanance");
    String usersEmail = (String) session.getAttribute("emailuser");
    ArrayList<Cars> cars = new CarsHandler().GetByUserEmail(usersEmail);
    ArrayList<Options> options = new OptionsHandler().GetAll();
    ArrayList<MaintanancesOptions> selectedOptions =
            new MaintanancesOptionsHandler().GetByMaintenanceId(maintanances.id);
%>

<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
    <script src="<%= request.getContextPath() %>/Maintanances/Scripts.js"></script>
    <title>Registro de Carros</title>
    <meta charset="UTF-8" />
  </head>

  <body class="body">
    <form class="container" action="../MaintanancesServlet" method="post" onsubmit="return validateForm()">
        <h2> Cadastro de Manutenções</h2>
        <br>

        <input
            type="hidden"
            name="id"
            value="<%= maintanances.id %>"
        >
        <div>
          <label for="name">Nome:</label><br>
          <input id="name" type="text" name="name" maxlength="30" value="<%= maintanances.name %>" required/>
          <br><br>
        </div>
        <div>
          <label for="car_plate">Placa:</label><br>
          <select id="car_plate" name="car_plate" required>
            <option value="<%= maintanances.car_plate %>" selected><%= maintanances.car_plate %></option>
          <%
              for (Cars car : cars) {
                  if (!car.number_plate.equals(maintanances.car_plate)) {
          %>
            <option value="<%= car.number_plate %>"><%= car.number_plate %></option>
          <%
                  }
              }
          %>
          </select>
          <br><br>
        </div>
        <div>
          <label for="done_by">Realizado por:</label><br>
          <input id="done_by" type="text" name="done_by" maxlength="100" value="<%= maintanances.done_by %>"></input>
          <br><br>
        </div>
        <div>
          <label for="done_at">Data:</label><br>
          <input id="done_at" type="date" name="done_at" value="<%= maintanances.done_at %>"></input>
          <br><br>
        </div>
        <div>
          <label for="observations">Observação:</label><br>
          <input id="observations" type="text" name="observations" maxlength="500" value="<%= maintanances.observations %>"></input>
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
            <tbody id="selected_options">
              <%
                  for (MaintanancesOptions selectedOption : selectedOptions) {
                      Options option = new OptionsHandler().GetById(selectedOption.option_id);
                      if (option != null) {
              %>
              <tr data-option-id="<%= option.id %>">
                <td><%= option.name %></td>
                <td><%= option.description %></td>
                <td><%= selectedOption.unit_value %></td>
                <td><button type="button" onclick="removeMaintenanceOption(this)">Remover</button>
                <input type="hidden" name="option_ids" value="<%= option.id %>">
                <input type="hidden" name="unit_values" value="<%= selectedOption.unit_value %>"></td>
              </tr>
              <script>
                document.querySelector('#option_select option[value="<%= option.id %>"]').disabled = true;
              </script>
              <%
                      }
                  }
              %>
            </tbody>
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
            <input class="botao3" type="submit" value="Salvar" />
        </div>
    </form>     
  </body>
</html> -->
