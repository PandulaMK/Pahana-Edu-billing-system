package com.pahanaedu.controller;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.BillService;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.service.impl.BillServiceImpl;
import com.pahanaedu.service.impl.CustomerServiceImpl;
import com.pahanaedu.dao.impl.BillDaoImpl;
import com.pahanaedu.dao.impl.CustomerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "GenerateBillServlet", urlPatterns = {"/bills/generate"})
public class GenerateBillServlet extends HttpServlet {

    private final BillService billService =
            new BillServiceImpl(new BillDaoImpl(), new CustomerDaoImpl());
    private final CustomerService customerService =
            new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String cid = req.getParameter("customer_id");
            if (cid == null || cid.trim().isEmpty()) {
                forwardError(req, resp, "customer_id is required");
                return;
            }

            int customerId = Integer.parseInt(cid.trim());

            // Units: use ?units= if provided, else use the saved units on the customer
            int units;
            String unitsStr = req.getParameter("units");
            if (unitsStr != null && !unitsStr.trim().isEmpty()) {
                units = Integer.parseInt(unitsStr.trim());
            } else {
                // read units from the customer record
                java.util.Optional<Customer> custOpt =
                        new CustomerServiceImpl(new CustomerDaoImpl()).getCustomerById(customerId);
                if (!custOpt.isPresent()) {
                    forwardError(req, resp, "Customer not found (id=" + customerId + ")");
                    return;
                }
                units = custOpt.get().getUnits();
            }
            if (units < 0) {
                forwardError(req, resp, "Units must be non-negative");
                return;
            }

            String generatedBy = (String) req.getSession().getAttribute("user");
            Bill bill = billService.generateBillByCustomerId(customerId, units, generatedBy);

            req.setAttribute("bill", bill);
            req.getRequestDispatcher("/printBill.jsp").forward(req, resp);

        } catch (NumberFormatException nfe) {
            forwardError(req, resp, "Invalid number format: " + nfe.getMessage(), nfe);
        } catch (Exception e) {
            forwardError(req, resp, "Failed to generate bill", e);
        }
    }


    // keep POST flow working too
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String accountNo = req.getParameter("account_no");
            int units = Integer.parseInt(req.getParameter("units"));
            String generatedBy = (String) req.getSession().getAttribute("user");

            Bill bill = billService.generateBill(accountNo, units, generatedBy);
            req.setAttribute("bill", bill);
            req.getRequestDispatcher("/printBill.jsp").forward(req, resp);
        } catch (Exception e) {
            forwardError(req, resp, "Failed to generate bill", e);
        }
    }

    private void forwardError(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("error", msg);
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }

    private void forwardError(HttpServletRequest req, HttpServletResponse resp, String msg, Throwable t) throws ServletException, IOException {
        // bubble up deepest cause to actually see DB/logic errors on the page
        Throwable root = t;
        while (root.getCause() != null) root = root.getCause();
        String full = msg + (t.getMessage() != null ? (": " + t.getMessage()) : "")
                   + (root != t && root.getMessage() != null ? " (" + root.getMessage() + ")" : "");
        req.setAttribute("error", full);
        t.printStackTrace(); // optional: log to file
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }
}
