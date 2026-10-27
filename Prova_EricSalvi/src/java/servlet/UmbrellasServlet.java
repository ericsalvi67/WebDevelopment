/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entity.Categories;
import Entity.Umbrellas;
import Handler.CategoriesHandler;
import Handler.UmbrellasHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author pretto
 */
@WebServlet("/UmbrellasServlet")
public class UmbrellasServlet extends HttpServlet {

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
            out.println("<title>Umbrellas Servlet</title>");
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
        
        ArrayList<Umbrellas> umbrellas = new ArrayList<>();

        String action = request.getParameter("a");
        String criteria = request.getParameter("c");
        String value = request.getParameter("v");

        System.out.println("Action: " + action);
        System.out.println("Criteria: " + criteria);
        System.out.println("Value: " + value);
        
        System.out.println("Action recebida: [" + action + "]");
        // -------------------------------------------------------------------
        switch (action) {
            case "register":
                ArrayList<Categories> categories = new CategoriesHandler().GetAll();
                request.setAttribute("categoriesList", categories);
                Action.RouterPage("Umbrellas/register_Umbrellas.jsp", request, response);
                return;
            case "getAll":
                umbrellas.addAll(new UmbrellasHandler().GetAll());
                break;
            case "getByValue":
                umbrellas.addAll(new UmbrellasHandler().GetByValue(criteria, value));
                break;
            case "getById":
                Umbrellas umbrella = new UmbrellasHandler().GetById(Integer.parseInt(request.getParameter("id")));
                ArrayList<Categories> categoriesById = new CategoriesHandler().GetAll();
                request.setAttribute("umbrella", umbrella);
                request.setAttribute("categoriesList", categoriesById);
                Action.RouterPage("Umbrellas/update_Umbrellas.jsp", request, response);
                return;
            default:
                System.out.println("Ação não reconhecida");
        }
        
        request.setAttribute("umbrellasList", umbrellas);
        Action.RouterPage("Umbrellas/select_Umbrellas.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no POST.");

        String description = request.getParameter("description");
        Float price = Float.parseFloat(request.getParameter("price"));
        Date service_date = Date.valueOf(LocalDate.now());
        int categories_id = Integer.parseInt(request.getParameter("categories_id"));

        Umbrellas umbrellas = new Umbrellas(0, description, price, service_date, categories_id);
        
        System.out.println("------------UMBRELLAS------------");
        System.out.println(umbrellas.description);
        System.out.println(umbrellas.price);
        System.out.println(umbrellas.service_date);
        System.out.println(umbrellas.categories_id);
        System.out.println("------------UMBRELLAS------------");

        if (new UmbrellasHandler().Insert(umbrellas)){
            response.setStatus(HttpServletResponse.SC_OK);
            Action.RouterPage("Umbrellas/Umbrellas.jsp", request, response);
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

        if (new UmbrellasHandler().Delete(id)){
            System.out.println("OS excluída com sucesso.");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Erro ao excluir OS.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
