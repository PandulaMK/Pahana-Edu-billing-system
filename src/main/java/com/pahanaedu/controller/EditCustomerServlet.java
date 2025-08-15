package com.pahanaedu.controller;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.service.impl.CustomerServiceImpl;
import com.pahanaedu.dao.impl.CustomerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "EditCustomerServlet", urlPatterns = {"/customer/edit"})
public class EditCustomerServlet extends HttpServlet {

    private final CustomerService customerService =
            new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("customer_id"));
            Optional<Customer> opt = customerService.getCustomerById(id);
            if (!opt.isPresent()) {
                req.setAttribute("error", "Customer not found");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("customer", opt.get());
            req.getRequestDispatcher("/editCustomer.jsp").forward(req, resp);
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to load customer: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
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
