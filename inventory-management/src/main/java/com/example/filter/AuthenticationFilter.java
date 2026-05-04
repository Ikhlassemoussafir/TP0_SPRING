package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        // Pages accessibles sans connexion
        boolean isLoginPage = uri.equals(contextPath + "/login") || uri.equals(contextPath + "/login.jsp");
        boolean isStaticResource = uri.contains(".css") || uri.contains(".js") || uri.contains(".png")
                || uri.contains(".jpg") || uri.contains(".ico");

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn || isLoginPage || isStaticResource) {
            // Vérifier les pages admin (gestion des utilisateurs) - réservées aux ADMIN
            if (isLoggedIn && isAdminPage(uri, contextPath)) {
                String userRole = (String) session.getAttribute("userRole");
                if (!"ADMIN".equals(userRole)) {
                    request.setAttribute("errorMessage", "Accès refusé. Vous devez être administrateur.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/access-denied.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(contextPath + "/login");
        }
    }

    private boolean isAdminPage(String uri, String contextPath) {
        return uri.equals(contextPath + "/users")
                || uri.equals(contextPath + "/user-form")
                || uri.equals(contextPath + "/create-user")
                || uri.equals(contextPath + "/update-user")
                || uri.equals(contextPath + "/delete-user");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
