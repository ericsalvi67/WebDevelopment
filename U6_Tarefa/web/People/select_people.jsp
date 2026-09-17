<%-- 
    Document   : select_people
    Created on : 8 de set. de 2026
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entity.People"%>

<!DOCTYPE html>

<html>
    <head>

        <meta charset="UTF-8">
        <title>People</title>
        <link rel="stylesheet" href="styles.css">
        <script src="People/people_scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Pessoas</h2>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Telefone</th>
                        <th>Data Nasc.</th>
                        <th>Editar</th>
                        <th>Excluir</th>

                    </tr>

                </thead>

                <tbody>
                    <%
                        List<People> peopleList =
                                (List<People>) request.getAttribute("peopleList");

                        if (peopleList != null && !peopleList.isEmpty()) {

                            for (People people : peopleList) {
                    %>
                    <tr>
                        <td>
                            <%= people.id %>
                        </td>
                        <td>
                            <%= people.name %>
                        </td>
                        <td>
                            <%= people.email %>
                        </td>
                        <td>
                            <%= people.phone %>
                        </td>
                        <td>
                            <%= people.birth %>
                        </td>
                        <td>
                            <a
                                href="PeopleServlet?a=getById&id=<%= people.id %>">
                                Editar
                            </a>
                        </td>
                        <td>
                            <input
                                style="color: #ff5050;"
                                type="button"
                                value="Excluir"
                                onclick="deletePerson('<%= request.getContextPath() %>', <%= people.id %>)"
                            >
                        </td>
                    </tr>
                    <%
                            }

                        } else {
                    %>
                    <tr>
                        <td colspan="7">
                            Nenhuma pessoa encontrada.
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
                onclick="window.location.href = 'People/People.jsp'"
            >
        </div>
    </body>
</html>