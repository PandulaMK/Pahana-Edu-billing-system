package com.pahanaedu.controller;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {

    private final CustomerService customerService = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        int id = 0;
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        Customer c = new Customer(
                id,
                request.getParameter("accountNo"),
                request.getParameter("name"),
                request.getParameter("address"),
                request.getParameter("phone"),
                Integer.parseInt(request.getParameter("units"))
        );

        try {
            if ("Update".equals(action)) {
                customerService.updateCustomer(c);
            } else {
                customerService.addCustomer(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return; // avoid redirect if error occurs
        }

        response.sendRedirect("viewCustomers.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Handle delete action
        if ("delete".equals(request.getParameter("action"))) {
            handleDelete(request, response);
            return;
        }

        // Handle normal list view
        showCustomerList(request, response);
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            customerService.deleteCustomer(id);
            request.getSession().setAttribute("successMessage", "Customer deleted successfully");
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Delete failed: " + e.getMessage());
        }
        response.sendRedirect("customer");
    }

    private void showCustomerList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Error loading customers: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}