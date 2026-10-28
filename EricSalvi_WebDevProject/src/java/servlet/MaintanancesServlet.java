/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entities.Cars;
import Entities.Maintanances;
import Entities.Options;
import Entities.People;
import Handlers.PeopleHandler;
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
@WebServlet("/MaintanancesServlet")
public class MaintanancesServlet extends HttpServlet {

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
            out.println("<title>Maintanances Servlet</title>");
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
        
        ArrayList<Maintanances> maintanances = new ArrayList<>();

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
                maintanances.addAll(new MaintananceHandler().GetAll());
                break;
            case "getByValue":
                maintanances.addAll(new MaintananceHandler().GetByValue(criteria, value));
                break;
            case "getById":
                Maintanances maintanance= new MaintananceHandler().GetById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("maintanance", maintanance);
                Action.RouterPage("Maintanances/Update.jsp", request, response);
                return;
            default:
                System.out.println("Ação não reconhecida");
        }
        
        request.setAttribute("maintanancesList", maintanances);
        Action.RouterPage("Maintanances/Maintanances.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no POST.");
        String name = request.getParameter("name");
        String car_plate = request.getParameter("car_plate");
        String done_by = request.getParameter("done_by");
        Date done_at = Date.valueOf(request.getParameter("done_at"));
        String observations = request.getParameter("observations");

        Maintanances maintanance = new Maintanances(0, name, car_plate, done_by, done_at, observations);
        
        System.out.println("------------MAINTANANCES------------");
        System.out.println(maintanance.name);
        System.out.println(maintanance.car_plate);
        System.out.println(maintanance.done_by);
        System.out.println(maintanance.done_at);
        System.out.println(maintanance.observations);
        System.out.println("------------MAINTANANCES------------");

        if (new MaintanancesHandler().Insert(maintanance)){
            response.setStatus(HttpServletResponse.SC_OK);
            Action.RouterPage("Maintanances/Maintanances.jsp", request, response);
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

        if (new MaintanancesHandler().Delete(id)){
            System.out.println("Manutenção excluída com sucesso.");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Erro ao excluir manutenção.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
