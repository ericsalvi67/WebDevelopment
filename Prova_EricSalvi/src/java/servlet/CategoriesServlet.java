/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entity.Categories;
import Handler.CategoriesHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author pretto
 */
@WebServlet("/CategoriesServlet")
public class CategoriesServlet extends HttpServlet {

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
            out.println("<title>Categories Servlet</title>");
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
        
        ArrayList<Categories> categories = new ArrayList<>();

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
                categories.addAll(new CategoriesHandler().GetAll());
                break;
            case "getByValue":
                categories.addAll(new CategoriesHandler().GetByValue(criteria, value));
                break;
            case "getById":
                Categories category = new CategoriesHandler().GetById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("category", category);
                Action.RouterPage("Categories/update_Categories.jsp", request, response);
                return;
            default:
                System.out.println("Ação não reconhecida");
        }
        
        request.setAttribute("categoriesList", categories);
        Action.RouterPage("Categories/select_Categories.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Estou no POST.");

        String description = request.getParameter("description");
    

        Categories categories = new Categories(0, description);
        
        System.out.println("------------CATEGORIES------------");
        System.out.println(categories.description);
        System.out.println("------------CATEGORIES------------");

        if (new CategoriesHandler().Insert(categories)){
            response.setStatus(HttpServletResponse.SC_OK);
            Action.RouterPage("Categories/Categories.jsp", request, response);
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

        if (new CategoriesHandler().Delete(id)){
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
