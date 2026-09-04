/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entity.People;
import Handler.PeopleHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

/**
 *
 * @author pretto
 */
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

        String action = request.getParameter("a");
        String criteria = request.getParameter("c");
        String value = request.getParameter("v");
        int id = Integer.parseInt(request.getParameter("id"));

        System.out.println("Action: " + action);
        System.out.println("Criteria: " + criteria);
        System.out.println("Value: " + value);
        System.out.println("ID: " + id);

        // -------------------------------------------------------------------
        switch (action) {
            case "getAll":
                new PeopleHandler().GetAll();
                break;
            case "getByValue":
                new PeopleHandler().GetByValue(criteria, value);
                break;
            case "getById":
                new PeopleHandler().GetById(id);
                break;
            default:
                System.out.println("Ação não reconhecida");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no POST.");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Date birth = Date.valueOf(request.getParameter("birth"));

        People people = new People(0, name, email, phone, birth);

        if (new PeopleHandler().Insert(people)){
            Action.RouterPage("Menu.jsp", request, response);
        } else {
            Action.RouterPage("erro.jsp", request, response);

        }
    }

    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no PATCH.");
        
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Date birth = Date.valueOf(request.getParameter("birth"));

        People people = new People(id, name, email, phone, birth);

        if (new PeopleHandler().Update(people)){
            Action.RouterPage("Menu.jsp", request, response);
        } else {
            Action.RouterPage("erro.jsp", request, response);

        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no DELETE.");
        
        int id = Integer.parseInt(request.getParameter("id"));

        if (new PeopleHandler().Delete(id)){
            Action.RouterPage("Menu.jsp", request, response);
        } else {
            Action.RouterPage("erro.jsp", request, response);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
