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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/update-user")
public class UserUpdateServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() { userDAO = new UserDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            if (role != null && !role.isEmpty()) {
                user.setRole(role);
            }

            // Si le mot de passe est vide, on garde l'ancien (pas de revalidation du password)
            if (password != null && !password.isEmpty()) {
                user.setPassword(password);
            }

            // Validation (on exclut le password si non modifié)
            List<String> errors;
            if (password == null || password.isEmpty()) {
                // Validation manuelle sans le champ password
                errors = new ArrayList<>();
                // On fait la validation complète mais le password existant est déjà valide
                errors = ValidationUtil.validate(user);
            } else {
                errors = ValidationUtil.validate(user);
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
                dispatcher.forward(request, response);
                return;
            }

            userDAO.update(user);
        }
        response.sendRedirect("users");
    }
}