/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entity.People;
import Handler.PeopleHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author pretto
 */
@WebServlet("/PeopleServlet")
public class PeopleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>People Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet action at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no GET.");
        
        ArrayList<People> people = new ArrayList<>();

        String action = request.getParameter("a");
        String criteria = request.getParameter("c");
        String value = request.getParameter("v");

        System.out.println("Action: " + action);
        System.out.println("Criteria: " + criteria);
        System.out.println("Value: " + value);
        
        System.out.println("Action recebida: [" + action + "]");
        // -------------------------------------------------------------------
        switch (action) {
            case "getAll":
                people.addAll(new PeopleHandler().GetAll());
                break;
            case "getByValue":
                people.addAll(new PeopleHandler().GetByValue(criteria, value));
                break;
            case "getById":
                People person = new PeopleHandler().GetById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("person", person);
                Action.RouterPage("People/update_people.jsp", request, response);
                return;
            default:
                System.out.println("Ação não reconhecida");
        }
        
        request.setAttribute("peopleList", people);
        Action.RouterPage("People/select_people.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no POST.");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Date birth = Date.valueOf(request.getParameter("date_of_birth"));

        People people = new People(0, name, email, phone, birth);
        
        System.out.println("------------PEOPLE------------");
        System.out.println(people.name);
        System.out.println(people.email);
        System.out.println(people.phone);
        System.out.println(people.birth);
        System.out.println("------------PEOPLE------------");

        if (new PeopleHandler().Insert(people)){
            response.setStatus(HttpServletResponse.SC_OK);
            Action.RouterPage("People/People.jsp", request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Action.RouterPage("Error.jsp", request, response);

        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no DELETE.");
        
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("ID recebido no DELETE: " + id);

        if (new PeopleHandler().Delete(id)){
            System.out.println("Pessoa excluída com sucesso.");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Erro ao excluir pessoa.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
