<%-- 
    Document   : Options
    Created on : 8 de set. de 2026
    Author     : Salvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entities.Options"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Opções</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
        <script src="<%= request.getContextPath() %>/Options/Scripts.js"></script>
    </head>
    <body class="body">

        <div class="container">
            <h2 class="h2">Opções</h2>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Descrição</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Options> optionsList =
                                (List<Options>) request.getAttribute("optionsList");

                        if (optionsList != null && !optionsList.isEmpty()) {

                            for (Options options : optionsList) {
                    %>
                    <tr>
                        <td>
                            <%= Options.id %>
                        </td>
                        <td>
                            <%= Options.name %>
                        </td>
                        <td>
                            <%= Options.description %>
                        </td>
                        <td>
                            <a
                                href="OptionsServlet?a=getById&id=<%= options.id %>">
                                Editar
                            </a>
                        </td>
                        <td>
                            <input
                                style="color: #ff5050;"
                                type="button"
                                value="Excluir"
                                onclick="deletePerson('<%= request.getContextPath() %>', <%= options.id %>)"
                            >
                        </td>
                    </tr>
                    <%
                            }

                        } else {
                    %>
                    <tr>
                        <td colspan="7">
                            Nenhuma opção encontrada.
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
                onclick="window.location.href = 'Options/Register.jsp'"
            >
        </div>
    </body>
</html>