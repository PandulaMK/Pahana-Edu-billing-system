package com.pahanaedu.controller;

import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillListServlet", urlPatterns = {"/bills"})
public class BillListServlet extends HttpServlet {

    private final BillService billService = new BillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // Retrieve all bills from DB
            List<Bill> bills = billService.getAllBills();

            // Pass bills list to JSP
            req.setAttribute("bills", bills);

            // Forward to bills.jsp
            req.getRequestDispatcher("/bills.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Unable to load bills: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
