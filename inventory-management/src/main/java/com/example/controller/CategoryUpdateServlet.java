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
import java.util.Optional;

@WebServlet("/update-category")
public class CategoryUpdateServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Optional<Category> optionalCategory = categoryDAO.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(name);
            category.setDescription(description);

            // Validation
            List<String> errors = ValidationUtil.validate(category);
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("category", category);
                RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
                dispatcher.forward(request, response);
                return;
            }

            categoryDAO.update(category);
        }
        response.sendRedirect("categories");
    }
}
