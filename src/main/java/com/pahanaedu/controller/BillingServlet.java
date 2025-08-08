package com.pahanaedu.controller;

import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillingServlet", urlPatterns = {"/bills"})
public class BillingServlet extends HttpServlet {

    private final BillService billService = new BillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Bill> bills = billService.getAllBills();
            req.setAttribute("bills", bills);
            req.getRequestDispatcher("/bills.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Failed to load bills: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
