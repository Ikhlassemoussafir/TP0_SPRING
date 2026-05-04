package com.example.controller;

import com.example.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-product")
public class ProductDeleteServlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() { productDAO = new ProductDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        productDAO.delete(id);
        response.sendRedirect("products");
    }
}