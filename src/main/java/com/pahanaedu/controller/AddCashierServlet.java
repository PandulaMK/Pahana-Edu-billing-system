package com.pahanaedu.controller;

import com.pahanaedu.service.UserService;
import com.pahanaedu.service.impl.UserServiceImpl;
import com.pahanaedu.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AddCashierServlet", urlPatterns = {"/admin/cashier/add"})
public class AddCashierServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;
        if (role == null || !"ADMIN".equalsIgnoreCase(role)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Admins only");
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            userService.createCashier(username, password);
            session.setAttribute("flash", "Cashier created: " + username);
            resp.sendRedirect(req.getContextPath() + "/admin");
        } catch (Exception e) {
            String msg = "Failed to create cashier";
            if (e.getMessage() != null) msg += ": " + e.getMessage();
            // show deepest cause for clarity (e.g., duplicate username, missing column)
            Throwable c = e;
            while (c.getCause() != null) c = c.getCause();
            if (c != e && c.getMessage() != null) msg += " (" + c.getMessage() + ")";
            req.setAttribute("error", msg);
            req.getRequestDispatcher("/admin/admin-dashboard.jsp").forward(req, resp);
        }
    }
}
