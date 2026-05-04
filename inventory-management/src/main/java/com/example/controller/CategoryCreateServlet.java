package com.example.controller;

import com.example.dao.CategoryDAO;
import com.example.model.Category;
import com.example.util.ValidationUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/create-category")
public class CategoryCreateServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Category newCategory = new Category(name, description);

        // Validation
        List<String> errors = ValidationUtil.validate(newCategory);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("category", newCategory);
            RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
            dispatcher.forward(request, response);
            return;
        }

        categoryDAO.save(newCategory);
        response.sendRedirect("categories");
    }
}
