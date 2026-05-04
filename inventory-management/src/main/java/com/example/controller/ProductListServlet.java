package com.example.controller;

import com.example.dao.ProductDAO;
import com.example.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() { productDAO = new ProductDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Product> products;
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productDAO.findByNameContaining(keyword);
            request.setAttribute("keyword", keyword);
        } else {
            products = productDAO.findAll();
        }
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }
}