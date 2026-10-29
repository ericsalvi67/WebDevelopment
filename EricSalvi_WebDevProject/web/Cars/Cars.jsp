<%-- 
    Document   : Cars
    Created on : 8 de set. de 2026
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Cars"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Carros</title>
        <link rel="stylesheet" href="../styles.css">
        <script src="Cars/Scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Carros</h2>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Placa</th>
                        <th>Cor</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Cars> carsList =
                                (List<Cars>) request.getAttribute("carsList");

                        if (carsList != null && !carsList.isEmpty()) {

                            for (Cars cars : carsList) {
                    %>
                    <tr>
                        <td>
                            <%= cars.id %>
                        </td>
                        <td>
                            <%= cars.name %>
                        </td>
                        <td>
                            <%= cars.number_plate %>
                        </td>
                        <td>
                            <%= cars.color %>
                        </td>
                        <td>
                            <a
                                href="CarsServlet?a=getById&id=<%= cars.id %>">
                                Editar
                            </a>
                        </td>
                        <td>
                            <input
                                style="color: #ff5050;"
                                type="button"
                                value="Excluir"
                                onclick="deletePerson('<%= request.getContextPath() %>', <%= cars.id %>)"
                            >
                        </td>
                    </tr>
                    <%
                            }

                        } else {
                    %>
                    <tr>
                        <td colspan="7">
                            Nenhum carro encontrado.
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
                onclick="window.location.href = 'Cars/Register.jsp'"
            >
        </div>
    </body>
</html>