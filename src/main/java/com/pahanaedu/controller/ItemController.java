package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/item")
public class ItemController extends HttpServlet {
    private final ItemService itemService = new ItemService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int id = 0;
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        Item item = new Item();
        item.setItemId(id);
        item.setItemName(request.getParameter("item_name"));
        item.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price"))));
        item.setQuantity(Integer.parseInt(request.getParameter("quantity")));


        try {
            if ("Update".equals(action)) {
                itemService.updateItem(item);
            } else {
                itemService.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Item DB error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("viewItems.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Handle delete action
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            handleDelete(request, response);
            return;
        }

        // Handle normal list view
        try {
            List<Item> itemList = itemService.getAllItems();
            request.setAttribute("items", itemList);
            request.getRequestDispatcher("viewItems.jsp").forward(request, response);
        } catch (SQLException e) {
            handleError(request, response, "Error loading items: " + e.getMessage());
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            itemService.deleteItem(id);
            response.sendRedirect("item"); // Redirect to refresh the list
        } catch (NumberFormatException e) {
            handleError(request, response, "Invalid item ID format");
        } catch (SQLException e) {
            handleError(request, response, "Error deleting item: " + e.getMessage());
        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        try {
            List<Item> itemList = itemService.getAllItems();
            request.setAttribute("items", itemList);
            request.getRequestDispatcher("viewItems.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", message + " (Additional error: " + e.getMessage() + ")");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}