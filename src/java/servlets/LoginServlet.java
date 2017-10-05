/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain_classes.User;
import domain_classes.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 715060
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.equals("") || password.equals("")) {

            request.setAttribute("noInput", "Please ensure all values are filled");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            UserService userService = new UserService();
            User user;
            user = userService.login(username, password);
            if (user == null) {
                request.setAttribute("username", username);

                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } else {

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

}
