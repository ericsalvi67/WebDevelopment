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

@WebServlet("/RequirementsServlet")
public class RequirementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String projectIdParam = request.getParameter("project_id");
        if (projectIdParam == null || projectIdParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/ProjectsServlet?a=getAll");
            return;
        }

        int projectId;
        try {
            projectId = Integer.parseInt(projectIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de projeto inválido.");
            return;
        }

        Projects project = new ProjectsHandler().GetById(projectId);
        if (project == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Projeto não encontrado.");
            return;
        }

        ArrayList<Requirements> requirements = new RequirementsHandler().GetByProjectId(project.id);

        request.setAttribute("project", project);
        request.setAttribute("requirementsList", requirements);
        Action.RouterPage("Requirements/Update.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;

        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autenticado.");
            return;
        }

        if (user.role != 1) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Apenas administradores podem salvar requisitos.");
            return;
        }

        int requirementId;
        int projectId;
        int priority;
        int complexity;
        int version;

        try {
            requirementId = Integer.parseInt(request.getParameter("id"));
            projectId = Integer.parseInt(request.getParameter("project_id"));
            priority = Integer.parseInt(request.getParameter("priority"));
            complexity = Integer.parseInt(request.getParameter("complexity"));
            version = Integer.parseInt(request.getParameter("version"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dados numéricos inválidos.");
            return;
        }

        Requirements requirement = new Requirements(
                requirementId,
                request.getParameter("code"),
                request.getParameter("type"),
                request.getParameter("requirement"),
                request.getParameter("description"),
                priority,
                complexity,
                version,
                projectId);

        RequirementsHandler handler = new RequirementsHandler();

        boolean saved = requirement.id == 0
                ? handler.Insert(requirement)
                : handler.Update(requirement);

        if (!saved) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao salvar requisito.");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/RequirementsServlet?project_id=" + projectId);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;

        if (user == null || user.role != 1) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Apenas administradores podem excluir requisitos.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de requisito inválido.");
            return;
        }

        if (new RequirementsHandler().Delete(id)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir requisito.");
        }
    }
}
