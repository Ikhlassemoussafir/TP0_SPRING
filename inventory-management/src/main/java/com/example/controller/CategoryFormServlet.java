package com.example.controller;

import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/category-form")
public class CategoryFormServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Optional<Category> category = categoryDAO.findById(id);
            category.ifPresent(c -> request.setAttribute("category", c));
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
        dispatcher.forward(request, response);
    }
}
