<%-- 
    Document   : Maintanances
    Created on : 8 de set. de 2026
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Maintanances"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Manutenções</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
        <script src="<%= request.getContextPath() %>/Maintanances/Scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Manutenções</h2>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Placa</th>
                        <th>Realizado por</th>
                        <th>Data</th>
                        <th>Observações</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Maintanances> maintanancesList =
                                (List<Maintanances>) request.getAttribute("maintanancesList");

                        if (maintanancesList != null && !maintanancesList.isEmpty()) {

                            for (Maintanances maintanances : maintanancesList) {
                    %>
                    <tr>
                        <td>
                            <%= maintanances.id %>
                        </td>
                        <td>
                            <%= maintanances.name %>
                        </td>
                        <td>
                            <%= maintanances.car_plate %>
                        </td>
                        <td>
                            <%= maintanances.done_by %>
                        </td>
                        <td>
                            <%= maintanances.done_at %>
                        </td>
                        <td>
                            <%= maintanances.observations %>
                        </td>
                        <td>
                            <a
                                href="MaintanancesServlet?a=getById&id=<%= maintanances.id %>">
                                Editar
                            </a>
                        </td>
                        <td>
                            <input
                                style="color: #ff5050;"
                                type="button"
                                value="Excluir"
                                onclick="deletePerson('<%= request.getContextPath() %>', <%= maintanances.id %>)"
                            >
                        </td>
                    </tr>
                    <%
                            }

                        } else {
                    %>
                    <tr>
                        <td colspan="7">
                            Nenhuma manutenção encontrada.
                        </td>

                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
                
            <br>
            
            <input
                class="botao"
                type="button"
                value="Voltar"
                onclick="window.location.href = '../Menu.jsp'"
            >
            
            <input
                class="botao2"
                type="button"
                value="Adicionar"
                onclick="window.location.href = 'Maintanances/Register.jsp'"
            >
        </div>
    </body>
</html>