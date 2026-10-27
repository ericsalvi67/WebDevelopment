<%-- 
    Document   : select_Categories
    Created on : 8 de set. de 2026
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Categories"%>

<!DOCTYPE html>

<html>
    <head>

        <meta charset="UTF-8">
        <title>Categorias</title>
        <link rel="stylesheet" href="styles.css">
        <script src="Categories/Categories_scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Categorias</h2>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Descrição</th>
                        <th>Excluir</th>

                    </tr>

                </thead>

                <tbody>
                    <%
                        List<Categories> categoriesList =
                                (List<Categories>) request.getAttribute("categoriesList");

                        if (categoriesList != null && !categoriesList.isEmpty()) {

                            for (Categories category : categoriesList) {
                    %>
                    <tr>
                        <td>
                            <%= category.id %>
                        </td>
                        <td>
                            <%= category.description %>
                        </td>
                        <td>
                            <input
                                style="color: #ff5050;"
                                type="button"
                                value="Excluir"
                                onclick="deleteCategory('<%= request.getContextPath() %>', <%= category.id %>)"
                            >
                        </td>
                    </tr>
                    <%
                            }

                        } else {
                    %>
                    <tr>
                        <td colspan="7">
                            Nenhuma categoria encontrada.
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
                onclick="window.location.href = 'Categories/Categories.jsp'"
            >
        </div>
    </body>
</html>