package com.pahanaedu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Invalidate the current session if it exists
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Optional: Add a logout confirmation message
        req.setAttribute("message", "You have been logged out successfully.");

        // Redirect back to the login page
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
