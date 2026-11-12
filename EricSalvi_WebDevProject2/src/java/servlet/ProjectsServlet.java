package servlet;

import Entities.Projects;
import Entities.Requirements;
import Entities.Users;
import Handlers.ProjectsHandler;
import Handlers.RequirementsHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ProjectsServlet")
public class ProjectsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;
        
       if (user == null) {
           response.sendRedirect(request.getContextPath() + "/index.jsp");
           return;
       }

        ArrayList<Projects> projects = new ArrayList<>();
        String action = request.getParameter("a");
        
        if (action == null || action.isBlank()) {
            action = "getAll";
        }

        String criteria = request.getParameter("c");
        String value = request.getParameter("v");
        ProjectsHandler projectHandler = new ProjectsHandler();

        switch (action) {
            case "getAll":
                projects.addAll(projectHandler.GetAll());
                break;

            case "getByValue":
                projects.addAll(projectHandler.GetByValue(criteria, value));
                break;

            case "getById":
                int projectId = Integer.parseInt(request.getParameter("id"));
                
                Projects project = projectHandler.GetById(projectId);

                if (project == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Projeto não encontrado.");
                    return;
                }
                
                ArrayList<Requirements> requirements =  new RequirementsHandler().GetByProjectId(project.id);
                
                request.setAttribute("project", project);
                request.setAttribute("requirementsList", requirements);

                Action.RouterPage("Projects/Update.jsp", request, response);
                return;
            default:
                System.out.println("Ação não reconhecida: " + action);
        }

        request.setAttribute("projectsList", projects);
        Action.RouterPage("Projects/Projects.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;

        if (user == null || user.role != 1) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado.");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Projects project = new Projects(id, name, user.email);
        ProjectsHandler projectHandler = new ProjectsHandler();

        boolean saved = (id == 0) ? projectHandler.Insert(project) : projectHandler.Update(project);

        if (saved) {
            if (id == 0) {
                Projects savedProject = projectHandler.GetByName(name);
                int newId = savedProject != null ? savedProject.id : id;
                response.sendRedirect(request.getContextPath() + "/ProjectsServlet?a=getById&id=" + newId + "&tab=req");
            } else {
                response.sendRedirect(request.getContextPath() + "/ProjectsServlet?a=getById&id=" + id);
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao salvar projeto.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;

        if (user == null || user.role != 1) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Apenas administradores podem excluir projetos.");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        
        if (new ProjectsHandler().Delete(id)) {
            System.out.println("Projeto excluído com sucesso.");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Erro ao excluir projeto.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir projeto.");
        }
    }
}
