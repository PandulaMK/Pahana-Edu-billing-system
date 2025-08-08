package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "EditItemServlet", urlPatterns = {"/item/edit"})
public class EditItemServlet extends HttpServlet {
    private final ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Item item = itemService.getItemById(id)
                        .orElse(null); // returns Optional<Item>
                if (item != null) {
                    req.setAttribute("item", item);
                    req.getRequestDispatcher("/editItem.jsp").forward(req, resp);
                    return;
                }
            } catch (Exception e) {
                req.setAttribute("error", "Invalid item ID");
            }
        }
        req.setAttribute("error", "Item not found");
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Item item = new Item();
            item.setItemId(Integer.parseInt(req.getParameter("id")));
            item.setItemName(req.getParameter("item_name"));
            item.setPrice(new java.math.BigDecimal(req.getParameter("price")));
            item.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            itemService.updateItem(item);

            req.getSession().setAttribute("flash", "Item updated successfully.");
            resp.sendRedirect(req.getContextPath() + "/item");
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to update item: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
