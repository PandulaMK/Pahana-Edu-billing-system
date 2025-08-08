package com.pahanaedu.controller;

import com.pahanaedu.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteCustomerServlet", urlPatterns = {"/customer/delete"})
public class DeleteCustomerServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(req.getParameter("customer_id"));
            customerService.deleteCustomer(customerId);
            resp.sendRedirect(req.getContextPath() + "/customer");
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to delete customer: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    }

    

