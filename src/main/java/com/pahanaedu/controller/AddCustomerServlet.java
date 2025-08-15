package com.pahanaedu.controller;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.service.impl.CustomerServiceImpl;
import com.pahanaedu.dao.impl.CustomerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AddCustomerServlet", urlPatterns = {"/customer/add"})
public class AddCustomerServlet extends HttpServlet {

    private final CustomerService customerService =
            new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Customer c = new Customer();
            c.setAccountNo(req.getParameter("account_no"));
            c.setName(req.getParameter("name"));
            c.setAddress(req.getParameter("address"));
            c.setPhone(req.getParameter("phone")); 

            c.setUnits(Integer.parseInt(req.getParameter("units")));

            customerService.addCustomer(c);

            req.getSession().setAttribute("flash", "Customer added successfully.");
            resp.sendRedirect(req.getContextPath() + "/customer");
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to add customer: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
