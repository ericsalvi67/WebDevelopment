package servlet;

import Entities.Users;
import Handlers.UsersHandler;
import Support.MD5;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("a");

        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        if ("register".equals(action)) {
            Action.RouterPage("Users/Register.jsp", request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        if (action == null || action.isBlank()) {
            action = "getAll";
        }

        String criteria = request.getParameter("c");
        String value = request.getParameter("v");
        UsersHandler usersHandler = new UsersHandler();
        ArrayList<Users> users = new ArrayList<>();

        switch (action) {
            case "getAll":
                if (user.role == 1) {
                    users.addAll(usersHandler.GetAll());
                } else {
                    Users current = usersHandler.GetById(user.id);
                    if (current != null) {
                        users.add(current);
                    }
                }
                break;

            case "getByValue":
                if (user.role == 1) {
                    users.addAll(usersHandler.GetByValue(criteria, value));
                } else {
                    Users current = usersHandler.GetById(user.id);
                    if (current != null) {
                        users.add(current);
                    }
                }
                break;

            case "getById":
                int targetId;
                try {
                    targetId = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de usuário inválido.");
                    return;
                }

                if (user.role != 1 && user.id != targetId) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Usuário não autorizado.");
                    return;
                }

                Users targetUser = usersHandler.GetById(targetId);
                if (targetUser == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuário não encontrado.");
                    return;
                }

                request.setAttribute("userEdit", targetUser);
                Action.RouterPage("Users/Update.jsp", request, response);
                return;

            default:
                System.out.println("Ação não reconhecida: " + action);
        }

        request.setAttribute("usersList", users);
        Action.RouterPage("Users/Users.jsp", request, response);
    }

    private void Login(HttpServletRequest request, HttpServletResponse response, String email, String password)
            throws IOException, ServletException {

        UsersHandler usersHandler = new UsersHandler();
        
        if (email == null || password == null) {
            Action.RouterPage("Error.jsp", request, response);
            return;
        }

        Users user = usersHandler.Auth(new Users(email, password));

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/Menu.jsp");
        } else {
            Action.RouterPage("Error.jsp", request, response);
        }
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idInput = request.getParameter("id");
        String email = request.getParameter("email");
        String password = MD5.getMd5(request.getParameter("password"));
        String confirmPassword =  MD5.getMd5(request.getParameter("confirm_password"));
        UsersHandler usersHandler = new UsersHandler();

        // Login
        if (idInput == null) {
            Login(request, response, email, password);
            return;
        }

        // Cadastro
        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            return;
        }

        Users newUser = new Users(0, email.trim(), password, 2);
        if (usersHandler.Insert(newUser)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            Action.RouterPage("Error.jsp", request, response);
        }
        return;
    }

    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idInput = request.getParameter("id");
        String email = request.getParameter("email");
        String password = MD5.getMd5(request.getParameter("password"));
        String confirmPassword =  MD5.getMd5(request.getParameter("confirm_password"));
        String oldPassword = request.getParameter("old_password");
        UsersHandler usersHandler = new UsersHandler();

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        int id;
        int role = user.role;
        try {
            id = Integer.parseInt(idInput);
            role = Integer.parseInt(request.getParameter("role"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID/Role inválido.");
            return;
        }

        if (user.role != 1 && user.id != id) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Usuário não autorizado.");
            return;
        }

        Users updatedUser = new Users(id, email.trim(), password, role);
        if (usersHandler.Update(updatedUser)) {
            if (user.id == id) {
                session.setAttribute("user", updatedUser);
            }
            response.sendRedirect(request.getContextPath() + "/UsersServlet?a=getAll");
        } else {
            Action.RouterPage("Error.jsp", request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Users user = session != null ? (Users) session.getAttribute("user") : null;

        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autenticado.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            return;
        }

        if (user.role != 1 && user.id != id) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Usuário não autorizado a excluir este registro.");
            return;
        }

        if (new UsersHandler().Delete(id)) {
            if (user.id == id) {
                session.invalidate();
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir usuário.");
        }
    }
}
