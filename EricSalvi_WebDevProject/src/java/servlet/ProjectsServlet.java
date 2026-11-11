package servlet;

import Entities.Projects;
import Entities.Users;
import Entities.Requirements;
import Handlers.ProjectsHandler;
import Handlers.RequirementsHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ProjectsServlet")
public class ProjectsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não reconhecida.");
                return;
        }

        request.setAttribute("projectsList", projects);
        Action.RouterPage("Projects/Projects.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idValue = request.getParameter("id");
        String name = request.getParameter("name");
        Users user = (Users) request.getSession().getAttribute("user");

        if (user.email == null || user.email.isEmpty()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
        }

        Projects project = new Projects(
                idValue == null || idValue.isEmpty() ? 0 : Integer.parseInt(idValue),
                name,
                user.email);

        ProjectsHandler handler = new ProjectsHandler();
        
        if (project.id != 0 && (user == null || user.role != 1)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        boolean saved = project.id == 0 ? handler.Insert(project) : handler.Update(project);

        if (!saved) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao salvar projeto.");
            return;
        }

        if (project.id == 0) {
            Projects savedProject = handler.GetByName(project.name);
            
            if (savedProject != null) {
                String[] codes = request.getParameterValues("code");
                String[] types = request.getParameterValues("type");
                String[] requirements = request.getParameterValues("requirement");
                String[] descriptions = request.getParameterValues("description");
                String[] priorities = request.getParameterValues("priority");
                String[] complexities = request.getParameterValues("complexity");
                String[] versions = request.getParameterValues("version");

                if (codes != null 
                    && types != null 
                    && requirements != null
                    && descriptions != null 
                    && priorities != null
                    && complexities != null 
                    && versions != null) {
                    RequirementsHandler requirementsHandler = new RequirementsHandler();

                    for (int i = 0; i < codes.length; i++) {
                        requirementsHandler.Insert(
                            new Requirements(
                                codes[i], 
                                types[i], 
                                requirements[i], 
                                descriptions[i],
                                Integer.parseInt(priorities[i]), 
                                Integer.parseInt(complexities[i]),
                                Integer.parseInt(versions[i]), 
                                savedProject.id)
                        );
                    }
                }
            }
        }

        request.setAttribute("a", project.id == 0 ? "getAll" : "getById");
        if (project.id != 0) {
            request.setAttribute("id", project.id);
        }
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        if (new ProjectsHandler().Delete(id)) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir projeto.");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
