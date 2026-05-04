<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion d'Inventaire</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="<%= request.getContextPath() %>/">Gestion d'Inventaire</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownProduits" role="button" data-toggle="dropdown">
                            Produits
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/products">Liste des Produits</a>
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/product-form">Ajouter Produit</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCategories" role="button" data-toggle="dropdown">
                            Catégories
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/categories">Liste des Catégories</a>
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/category-form">Ajouter Catégorie</a>
                        </div>
                    </li>
                    <c:if test="${sessionScope.userRole == 'ADMIN'}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsers" role="button" data-toggle="dropdown">
                                Utilisateurs
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/users">Liste des Utilisateurs</a>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/user-form">Ajouter Utilisateur</a>
                            </div>
                        </li>
                    </c:if>
                </ul>
                <ul class="navbar-nav">
                    <c:if test="${sessionScope.user != null}">
                        <li class="nav-item">
                            <span class="nav-link text-light">
                                <c:out value="${sessionScope.userName}" />
                                <c:if test="${sessionScope.userRole == 'ADMIN'}">
                                    <span class="badge badge-danger">ADMIN</span>
                                </c:if>
                                <c:if test="${sessionScope.userRole == 'USER'}">
                                    <span class="badge badge-info">USER</span>
                                </c:if>
                            </span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-warning" href="<%= request.getContextPath() %>/logout">Déconnexion</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>
<div class="container mt-4">