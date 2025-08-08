package com.pahanaedu.controller;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AddCustomerServlet", urlPatterns = {"/customer/add"})
public class AddCustomerServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accountNo = req.getParameter("account_no");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        String unitsStr = req.getParameter("units");

        try {
            int units = (unitsStr == null || unitsStr.isEmpty()) ? 0 : Integer.parseInt(unitsStr);
            Customer c = new Customer();
            c.setAccountNo(req.getParameter("account_no"));
            c.setName(req.getParameter("name"));
            c.setAddress(req.getParameter("address"));
            c.setPhone(req.getParameter("telephone"));
            c.setUnits(Integer.parseInt(req.getParameter("units")));
            customerService.addCustomer(c); // DAO inserts and returns generated key

            
            req.getSession().setAttribute("flash", "Customer added successfully.");
            resp.sendRedirect(req.getContextPath() + "/customer");
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to add customer: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
