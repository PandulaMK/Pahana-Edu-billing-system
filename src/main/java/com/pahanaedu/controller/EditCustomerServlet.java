package com.pahanaedu.controller;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.dao.DaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "EditCustomerServlet", urlPatterns = {"/customer/edit"})
public class EditCustomerServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("customer_id");
        if (idParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing customer_id");
            return;
        }

        try {
            int customerId = Integer.parseInt(idParam);
            Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);

            if (optionalCustomer.isPresent()) {
                req.setAttribute("customer", optionalCustomer.get());
                req.getRequestDispatcher("/editCustomer.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customer_id");
        } catch (DaoException e) {
            throw new ServletException("Database error retrieving customer", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Customer c = new Customer();
            c.setCustomerId(Integer.parseInt(req.getParameter("customer_id")));
            c.setAccountNo(req.getParameter("account_no"));
            c.setName(req.getParameter("name"));
            c.setAddress(req.getParameter("address"));
            c.setPhone(req.getParameter("phone"));
            c.setUnits(Integer.parseInt(req.getParameter("units")));

            customerService.updateCustomer(c);

            req.getSession().setAttribute("flash", "Customer updated successfully.");
            resp.sendRedirect(req.getContextPath() + "/customer");
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to update customer: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
