package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.service.impl.ItemServiceImpl;
import com.pahanaedu.dao.impl.ItemDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AddItemServlet", urlPatterns = {"/item/add"})
public class AddItemServlet extends HttpServlet {

    private final ItemService itemService =
            new ItemServiceImpl(new ItemDaoImpl());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Item item = new Item();
            item.setItemName(req.getParameter("item_name"));
            item.setPrice(new java.math.BigDecimal(req.getParameter("price")));
            item.setQuantity(Integer.parseInt(req.getParameter("quantity")));

            itemService.addItem(item);

            req.getSession().setAttribute("flash", "Item added successfully.");
            resp.sendRedirect(req.getContextPath() + "/item");
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to add item: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
