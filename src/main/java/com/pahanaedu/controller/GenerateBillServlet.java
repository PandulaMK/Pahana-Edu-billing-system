package com.pahanaedu.controller;

import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Bill;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.service.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "GenerateBillServlet", urlPatterns = {"/bills/generate"})
public class GenerateBillServlet extends HttpServlet {

    private final CustomerService customerService = new CustomerService();
    private final BillService billService = new BillService();

    private static final BigDecimal RATE_PER_UNIT = new BigDecimal("50.00"); // Common rate

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String customerIdParam = req.getParameter("customer_id");
        if (customerIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/customer");
            return;
        }

        try {
            int customerId = Integer.parseInt(customerIdParam);

            Customer customer = customerService.getCustomerById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            // Calculate bill
            Bill bill = billService.createBill(
                    customerId,
                    customer.getUnits(),
                    RATE_PER_UNIT
            );

            req.setAttribute("bill", bill);
            req.getRequestDispatcher("/printBill.jsp").forward(req, resp);


        } catch (Exception e) {
            req.setAttribute("error", "Failed to generate bill: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

}
