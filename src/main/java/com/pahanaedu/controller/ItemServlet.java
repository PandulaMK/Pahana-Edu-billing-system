package com.pahanaedu.controller;

import com.pahanaedu.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.service.impl.ItemServiceImpl;
import com.pahanaedu.dao.impl.ItemDaoImpl;

@WebServlet(name = "ItemServlet", urlPatterns = {"/item"})
public class ItemServlet extends HttpServlet {
	private final ItemService itemService = new ItemServiceImpl(new ItemDaoImpl());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("items", itemService.getAllItems());
            req.getRequestDispatcher("/item.jsp").forward(req, resp);
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to load items: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
