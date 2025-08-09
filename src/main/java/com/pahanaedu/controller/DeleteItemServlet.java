package com.pahanaedu.controller;

import com.pahanaedu.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteItemServlet", urlPatterns = {"/item/delete"})
public class DeleteItemServlet extends HttpServlet {
    private final ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        try {
            int id = Integer.parseInt(idParam);
            itemService.deleteItem(id);

            request.getSession().setAttribute("successMessage", "Item deleted successfully.");
        } catch (NumberFormatException ex) {
            request.getSession().setAttribute("errorMessage", "Invalid item ID.");
        } catch (Exception ex) {
            request.getSession().setAttribute("errorMessage", "Error deleting item: " + ex.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/item");
    }
}

