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
import javax.servlet.http.Cookie;
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
            return;
        }

        UserService userService = new UserService();
        User user;
        user = userService.login(username, password);
        if (user == null) {
            request.setAttribute("username", username);
            request.setAttribute("invalidLogin", "Invalid username or password");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        //Remmeber me selected
        if (request.getParameter("remember") != null) {
            Cookie cookie = new Cookie("usernameCookie", username);
            cookie.setMaxAge(20 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } //If remember me is not checked
        //See if there is a matching cookie and remove it 
        else {
            Cookie[] cookieArray = request.getCookies();
            for (Cookie cookie : cookieArray) {
                if (cookie.getName().equals("usernameCookie")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }

            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        response.sendRedirect("home");
    }
}
