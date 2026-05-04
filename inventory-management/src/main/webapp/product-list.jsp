<%@ include file="header.jsp" %>

<h2>Liste des Produits</h2>
<hr>

<div class="row mb-3">
    <div class="col-md-6">
        <a href="<%= request.getContextPath() %>/product-form" class="btn btn-success">Ajouter un Produit</a>
    </div>
    <div class="col-md-6">
        <form action="<%= request.getContextPath() %>/products" method="get" class="form-inline float-right">
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
        <th>Catégorie</th>
        <th>Prix</th>
        <th>Stock</th>
        <th>SKU</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><c:out value="${product.id}" /></td>
            <td><c:out value="${product.name}" /></td>
            <td>
                <c:if test="${product.category != null}">
                    <span class="badge badge-info"><c:out value="${product.category.name}" /></span>
                </c:if>
                <c:if test="${product.category == null}">
                    <span class="badge badge-secondary">Aucune</span>
                </c:if>
            </td>
            <td><c:out value="${product.price}" /> &euro;</td>
            <td><c:out value="${product.stockQuantity}" /></td>
            <td><c:out value="${product.sku}" /></td>
            <td>
                <a href="<%= request.getContextPath() %>/product-form?action=edit&id=<c:out value='${product.id}' />" class="btn btn-primary btn-sm">Modifier</a>
                <a href="<%= request.getContextPath() %>/delete-product?id=<c:out value='${product.id}' />" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="footer.jsp" %>