<%-- 
    Document   : select_Umbrellas
    Created on : 8 de set. de 2026
    Author     : Salvi
--%>

<%@page import="Entity.Categories"%>
<%@page import="Handler.CategoriesHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Umbrellas"%>

<!DOCTYPE html>

<html>
    <head>

        <meta charset="UTF-8">
        <title>OS Guarda Chuva</title>
        <link rel="stylesheet" href="styles.css">
        <script src="Umbrellas/Umbrellas_scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Ordem de Serviço</h2>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Descrição</th>
                        <th>Valor</th>
                        <th>Data da OS</th>
                        <th>Categoria</th>
                        <th>Editar</th>
                        <th>Excluir</th>

                    </tr>

                </thead>

                <tbody>
                    <%
                        List<Umbrellas> umbrellasList =
                                (List<Umbrellas>) request.getAttribute("umbrellasList");

                        if (umbrellasList != null && !umbrellasList.isEmpty()) {

                            for (Umbrellas umbrella : umbrellasList) {
                    %>
                    <tr>
                        <td>
                            <%= umbrella.id %>
                        </td>
                        <td>
                            <%= umbrella.description %>
                        </td>
                        <td>
                            <%= umbrella.price %>
                        </td>
                        <td>
                            <%= umbrella.service_date %>
                        </td>
                        <td>
                            <%
                                Categories category = new CategoriesHandler()
                                    .GetById(umbrella.categories_id);
                            %>
                            <%= category != null ? category.description : "Sem categoria" %>
                        </td>
                        <td>
                            <a
                                href="UmbrellasServlet?a=getById&id=<%= umbrella.id %>">
                                Editar
                            </a>
                        </td>
                        <td>
                            <input
                                style="color: #ff5050;"
                                type="button"
                                value="Excluir"
                                onclick="deleteUmbrella('<%= request.getContextPath() %>', <%= umbrella.id %>)"
                            >
                        </td>
                    </tr>
                    <%
                            }

                        } else {
                    %>
                    <tr>
                        <td colspan="7">
                            Nenhuma OS encontrada.
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
                onclick="window.location.href = 'Umbrellas/Umbrellas.jsp'"
            >
        </div>
    </body>
</html>