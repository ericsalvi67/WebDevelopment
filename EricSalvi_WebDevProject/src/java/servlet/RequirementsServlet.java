package servlet;

import Entities.Requirements;
import Handlers.RequirementsHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/RequirementsServlet")
public class RequirementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Requirements> requirements = new ArrayList<>();
        String action = request.getParameter("a");
        if (action == null) {
            action = (String) request.getAttribute("a");
        }
        if (action == null) {
            action = "getAll";
        }

        String criteria = request.getParameter("c");
        String value = request.getParameter("v");
        RequirementsHandler handler = new RequirementsHandler();

        switch (action) {
            case "getAll":
                requirements.addAll(handler.GetAll());
                break;
            case "getByValue":
                requirements.addAll(handler.GetByValue(criteria, value));
                break;
            case "getById":
                Requirements requirement = handler.GetById(
                        Integer.parseInt(request.getParameter("id")));
                request.setAttribute("requirement", requirement);
                Action.RouterPage("Requirements/Update.jsp", request, response);
                return;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não reconhecida.");
                return;
        }

        request.setAttribute("requirementsList", requirements);
        Action.RouterPage("Requirements/Requirements.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idValue = request.getParameter("id");

        Requirements requirement = new Requirements(
                idValue == null || idValue.isEmpty() ? 0 : Integer.parseInt(idValue),
                request.getParameter("code"),
                request.getParameter("type"),
                request.getParameter("requirement"),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("priority")),
                Integer.parseInt(request.getParameter("complexity")),
                Integer.parseInt(request.getParameter("version")),
                Integer.parseInt(request.getParameter("project_id")));

        RequirementsHandler handler = new RequirementsHandler();

        boolean saved = requirement.id == 0
                ? handler.Insert(requirement)
                : handler.Update(requirement);

        if (!saved) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Erro ao salvar requisito.");
            return;
        }

        response.sendRedirect(request.getContextPath()
                + "/ProjectsServlet?a=getById&id=" + requirement.project_id);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        if (new RequirementsHandler().Delete(id)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Erro ao excluir requisito.");
        }
    }
}
