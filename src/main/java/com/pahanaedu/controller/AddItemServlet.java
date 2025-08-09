package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AddItemServlet", urlPatterns = {"/item/add"})
public class AddItemServlet extends HttpServlet {
    private final ItemService itemService = new ItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("item_name");
        String priceStr = req.getParameter("price");
        String qtyStr = req.getParameter("quantity");

        try {
            double price = Double.parseDouble(priceStr);
            int qty = Integer.parseInt(qtyStr);
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
