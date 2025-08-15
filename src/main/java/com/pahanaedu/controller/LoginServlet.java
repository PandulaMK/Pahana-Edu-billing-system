package com.pahanaedu.controller;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import com.pahanaedu.service.impl.UserServiceImpl;
import com.pahanaedu.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp); // login page
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Optional<User> userOpt = userService.login(username, password);
            if (userOpt.isPresent()) {
                User user = userOpt.get();

                HttpSession session = req.getSession(true);
                session.setAttribute("user", user.getUsername());
                session.setAttribute("role", user.getRole());

                String ctx = req.getContextPath();
                if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                    resp.sendRedirect(ctx + "/admin");           // admin dashboard
                } else {
                    resp.sendRedirect(ctx + "/dashboard.jsp");   // cashier dashboard (existing)
                }
                return;
            }

            // Invalid credentials
            req.setAttribute("error", "Invalid username or password.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);

        } catch (Exception ex) {
            req.setAttribute("error", "Login error: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
