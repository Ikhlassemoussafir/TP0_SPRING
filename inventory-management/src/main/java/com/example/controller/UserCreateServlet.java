package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.util.ValidationUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/create-user")
public class UserCreateServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() { userDAO = new UserDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User newUser = new User(firstName, lastName, email, password);
        if (role != null && !role.isEmpty()) {
            newUser.setRole(role);
        }

        // Validation
        List<String> errors = ValidationUtil.validate(newUser);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", newUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            dispatcher.forward(request, response);
            return;
        }

        userDAO.save(newUser);
        response.sendRedirect("users");
    }
}