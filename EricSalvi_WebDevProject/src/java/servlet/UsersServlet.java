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

@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = MD5.getMd5(request.getParameter("password"));

        System.out.println("Login: " + email + " - " + password);

        UsersHandler usersHandler = new UsersHandler();
        Users user = usersHandler.Auth(new Users(email, password));

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            System.out.println("Usuário autenticado com sucesso: " + user.email + " (Role: " + user.role + ")");
            response.sendRedirect(request.getContextPath() + "/Menu.jsp");
        } else {
            System.out.println("Falha na autenticação para: " + email);
            Action.RouterPage("Error.jsp", request, response);
        }
    }
}
