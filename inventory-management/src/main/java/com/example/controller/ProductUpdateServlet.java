package com.example.controller;

import com.example.dao.CategoryDAO;
import com.example.dao.ProductDAO;
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
import java.util.Optional;

@WebServlet("/update-product")
public class ProductUpdateServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stockQuantity");
        String sku = request.getParameter("sku");
        String categoryIdStr = request.getParameter("categoryId");

        Optional<Product> optionalProduct = productDAO.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(name);
            product.setDescription(description);

            try {
                product.setPrice(new BigDecimal(priceStr));
            } catch (Exception e) {
                product.setPrice(null);
            }
            try {
                product.setStockQuantity(Integer.parseInt(stockStr));
            } catch (Exception e) {
                product.setStockQuantity(null);
            }
            product.setSku(sku);

            if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                categoryDAO.findById(Long.parseLong(categoryIdStr)).ifPresent(product::setCategory);
            } else {
                product.setCategory(null);
            }

            // Validation
            List<String> errors = ValidationUtil.validate(product);
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("product", product);
                request.setAttribute("categories", categoryDAO.findAll());
                RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
                dispatcher.forward(request, response);
                return;
            }

            productDAO.update(product);
        }
        response.sendRedirect("products");
    }
}