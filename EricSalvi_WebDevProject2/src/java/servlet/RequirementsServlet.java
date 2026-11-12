package servlet;

import Entities.Requirements;
import Entities.Users;
import Handlers.RequirementsHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

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

        String projectId = request.getParameter("project_id");
        if (projectId != null && !projectId.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/ProjectsServlet?a=getById&id=" + projectId);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/ProjectsServlet?a=getAll");
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
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Apenas administradores podem cadastrar requisitos.");
            return;
        }

        int requirementId = Integer.parseInt(request.getParameter("id"));
        int projectId =  Integer.parseInt(request.getParameter("project_id"));

        Requirements requirement = new Requirements(
                requirementId,
                request.getParameter("code"),
                request.getParameter("type"),
                request.getParameter("requirement"),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("priority")),
                Integer.parseInt(request.getParameter("complexity")),
                Integer.parseInt(request.getParameter("version")),
                projectId);

        RequirementsHandler handler = new RequirementsHandler();

        boolean saved = requirement.id == 0
                ? handler.Insert(requirement)
                : handler.Update(requirement);

        if (!saved) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao salvar requisito.");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/ProjectsServlet?a=getById&id=" + projectId);
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

        int id = Integer.parseInt(request.getParameter("id"));
        
        if (new RequirementsHandler().Delete(id)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir requisito.");
        }
    }
}
