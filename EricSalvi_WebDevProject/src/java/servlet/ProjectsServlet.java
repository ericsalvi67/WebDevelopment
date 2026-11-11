package servlet;

import Entities.Projects;
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
//        if (user == null) {
//            response.sendRedirect(request.getContextPath() + "/index.jsp");
//            return;
//        }

        ArrayList<Projects> projects = new ArrayList<>();
        String action = request.getParameter("a");
        if (action == null) {
            action = (String) request.getAttribute("a");
        }
        if (action == null) {
            action = "getAll";
        }

        String criteria = request.getParameter("c");
        String value = request.getParameter("v");
        ProjectsHandler handler = new ProjectsHandler();

        switch (action) {
            case "getAll":
                projects.addAll(handler.GetAll());
                break;
            case "getByValue":
                projects.addAll(handler.GetByValue(criteria, value));
                break;
            case "getById":
                String projectId = request.getParameter("id");
                if (projectId == null) {
                    projectId = String.valueOf(request.getAttribute("id"));
                }
                Projects project = handler.GetById(Integer.parseInt(projectId));
                if (project == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Projeto não encontrado.");
                    return;
                }
                request.setAttribute("project", project);
                request.setAttribute("requirementsList",
                        new RequirementsHandler().GetByProjectId(project.id));
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

        String idValue = request.getParameter("id");
        int id = (idValue == null || idValue.trim().isEmpty()) ? 0 : Integer.parseInt(idValue);
        String name = request.getParameter("name");

        Projects project = new Projects(id, name, user.email);
        ProjectsHandler handler = new ProjectsHandler();

        boolean saved = (id == 0) ? handler.Insert(project) : handler.Update(project);

        if (saved) {
            if (id == 0) {
                Projects savedProject = handler.GetByName(name);
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
