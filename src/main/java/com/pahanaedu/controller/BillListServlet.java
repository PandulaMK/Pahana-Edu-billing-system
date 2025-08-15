package com.pahanaedu.controller;

import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillService;
import com.pahanaedu.service.impl.BillServiceImpl;
import com.pahanaedu.dao.impl.BillDaoImpl;
import com.pahanaedu.dao.impl.CustomerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillListServlet", urlPatterns = {"/bills/list"})
public class BillListServlet extends HttpServlet {

    private final BillService billService =
            new BillServiceImpl(new BillDaoImpl(), new CustomerDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Bill> bills = billService.getAllBills();
            req.setAttribute("bills", bills);
            req.getRequestDispatcher("/bills.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Unable to load bills: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
