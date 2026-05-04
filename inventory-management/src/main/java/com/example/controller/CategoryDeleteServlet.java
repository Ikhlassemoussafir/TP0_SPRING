package com.example.controller;

import com.example.dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-category")
public class CategoryDeleteServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        categoryDAO.delete(id);
        response.sendRedirect("categories");
    }
}
