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
import java.util.List;

@WebServlet("/categories")
public class CategoryListServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Category> categories;
        if (keyword != null && !keyword.trim().isEmpty()) {
            categories = categoryDAO.findByNameContaining(keyword);
            request.setAttribute("keyword", keyword);
        } else {
            categories = categoryDAO.findAll();
        }
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-list.jsp");
        dispatcher.forward(request, response);
    }
}
