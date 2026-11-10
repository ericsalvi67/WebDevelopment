/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entities.Maintanances;
import Entities.MaintanancesOptions;
import Handlers.MaintanancesHandler;
import Handlers.MaintanancesOptionsHandler;
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
 * @author Salvi
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
        if (action == null) {
            action = (String) request.getAttribute("a");
        }
        if (action == null) {
            action = "getAll";
        }
        
        String criteria = request.getParameter("c");
        String value = request.getParameter("v");

        System.out.println("Action: " + action);
        System.out.println("Criteria: " + criteria);
        System.out.println("Value: " + value);
        
        System.out.println("Action recebida: [" + action + "]");
        // -------------------------------------------------------------------
        switch (action) {
            case "getAll":
                maintanances.addAll(new MaintanancesHandler().GetAll());
                break;
            case "getByValue":
                maintanances.addAll(new MaintanancesHandler().GetByValue(criteria, value));
                break;
            case "getById":
                Maintanances maintanance= new MaintanancesHandler().GetById(Integer.parseInt(request.getParameter("id")));
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
        String doneAtValue = request.getParameter("done_at");
        Date done_at = doneAtValue == null || doneAtValue.isEmpty()
                ? null : Date.valueOf(doneAtValue);
        String observations = request.getParameter("observations");
        String idValue = request.getParameter("id");

        Maintanances maintanance = new Maintanances(
                idValue == null || idValue.isEmpty() ? 0 : Integer.parseInt(idValue),
                name, car_plate, done_by, done_at, observations);
        
        System.out.println("------------MAINTANANCES------------");
        System.out.println(maintanance.name);
        System.out.println(maintanance.car_plate);
        System.out.println(maintanance.done_by);
        System.out.println(maintanance.done_at);
        System.out.println(maintanance.observations);
        System.out.println("------------MAINTANANCES------------");

        MaintanancesHandler maintanancesHandler = new MaintanancesHandler();
        boolean saved = maintanance.id == 0
                ? maintanancesHandler.Insert(maintanance)
                : maintanancesHandler.Update(maintanance);

        if (saved) {
            if (maintanance.id == 0) {
                Maintanances savedMaintanance = maintanancesHandler.GetByName(name);
                if (savedMaintanance != null) {
                    maintanance.id = savedMaintanance.id;
                }
            }

            MaintanancesOptionsHandler optionsHandler = new MaintanancesOptionsHandler();
            optionsHandler.DeleteByMaintenanceId(maintanance.id);
            String[] optionIds = request.getParameterValues("option_ids");
            String[] unitValues = request.getParameterValues("unit_values");
            if (optionIds != null && unitValues != null && optionIds.length == unitValues.length) {
                for (int i = 0; i < optionIds.length; i++) {
                    optionsHandler.Insert(new MaintanancesOptions(
                            maintanance.id,
                            Integer.parseInt(optionIds[i]),
                            Double.parseDouble(unitValues[i])));
                }
            }

            response.setStatus(HttpServletResponse.SC_OK);
            
            request.setAttribute("a", "getAll");
            doGet(request, response);
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
