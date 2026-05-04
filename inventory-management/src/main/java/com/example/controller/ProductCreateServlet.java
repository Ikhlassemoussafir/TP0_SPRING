package com.example.controller;

import com.example.dao.CategoryDAO;
import com.example.dao.ProductDAO;
import com.example.model.Category;
import com.example.model.Product;
import com.example.util.ValidationUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/create-product")
public class ProductCreateServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stockQuantity");
        String sku = request.getParameter("sku");
        String categoryIdStr = request.getParameter("categoryId");

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);

        try {
            newProduct.setPrice(new BigDecimal(priceStr));
        } catch (Exception e) {
            newProduct.setPrice(null);
        }
        try {
            newProduct.setStockQuantity(Integer.parseInt(stockStr));
        } catch (Exception e) {
            newProduct.setStockQuantity(null);
        }
        newProduct.setSku(sku);

        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            categoryDAO.findById(Long.parseLong(categoryIdStr)).ifPresent(newProduct::setCategory);
        }

        // Validation
        List<String> errors = ValidationUtil.validate(newProduct);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("product", newProduct);
            request.setAttribute("categories", categoryDAO.findAll());
            RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
            dispatcher.forward(request, response);
            return;
        }

        productDAO.save(newProduct);
        response.sendRedirect("products");
    }
}