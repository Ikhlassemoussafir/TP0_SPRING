<%@ include file="header.jsp" %>

<c:if test="${not empty errors}">
    <div class="alert alert-danger">
        <ul>
            <c:forEach var="error" items="${errors}">
                <li><c:out value="${error}" /></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<c:if test="${category != null && category.id != null}">
    <h2>Modifier la Catégorie</h2>
    <form action="<%= request.getContextPath() %>/update-category" method="post">
    <input type="hidden" name="id" value="<c:out value='${category.id}' />" />
</c:if>
<c:if test="${category == null || category.id == null}">
    <h2>Ajouter une Catégorie</h2>
    <form action="<%= request.getContextPath() %>/create-category" method="post">
</c:if>

<div class="form-group">
    <label for="name">Nom de la catégorie</label>
    <input type="text" class="form-control" id="name" name="name" value="<c:out value='${category.name}' />" required>
</div>
<div class="form-group">
    <label for="description">Description</label>
    <textarea class="form-control" id="description" name="description" rows="3"><c:out value='${category.description}' /></textarea>
</div>

<button type="submit" class="btn btn-primary">Enregistrer</button>
<a href="<%= request.getContextPath() %>/categories" class="btn btn-secondary">Annuler</a>
</form>

<%@ include file="footer.jsp" %>
