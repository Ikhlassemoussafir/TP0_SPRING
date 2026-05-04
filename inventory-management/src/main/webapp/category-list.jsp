<%@ include file="header.jsp" %>

<h2>Liste des Catégories</h2>
<hr>

<div class="row mb-3">
    <div class="col-md-6">
        <a href="<%= request.getContextPath() %>/category-form" class="btn btn-success">Ajouter une Catégorie</a>
    </div>
    <div class="col-md-6">
        <form action="<%= request.getContextPath() %>/categories" method="get" class="form-inline float-right">
            <input type="text" class="form-control mr-2" name="keyword" placeholder="Rechercher..." value="${keyword}">
            <button type="submit" class="btn btn-primary">Rechercher</button>
        </form>
    </div>
</div>

<table class="table table-bordered">
    <thead class="thead-dark">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td><c:out value="${category.id}" /></td>
            <td><c:out value="${category.name}" /></td>
            <td><c:out value="${category.description}" /></td>
            <td>
                <a href="<%= request.getContextPath() %>/category-form?action=edit&id=<c:out value='${category.id}' />" class="btn btn-primary btn-sm">Modifier</a>
                <a href="<%= request.getContextPath() %>/delete-category?id=<c:out value='${category.id}' />" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="footer.jsp" %>
