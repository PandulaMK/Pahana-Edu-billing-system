package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.service.impl.ItemServiceImpl;
import com.pahanaedu.dao.impl.ItemDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "EditItemServlet", urlPatterns = {"/item/edit"})
public class EditItemServlet extends HttpServlet {

    private final ItemService itemService =
            new ItemServiceImpl(new ItemDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Item> itemOpt = itemService.getItemById(id);
            if (!itemOpt.isPresent()) {
                req.setAttribute("error", "Item not found");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("item", itemOpt.get());
            req.getRequestDispatcher("/editItem.jsp").forward(req, resp);
        } catch (Exception ex) {
            req.setAttribute("error", "Failed to load item: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
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
