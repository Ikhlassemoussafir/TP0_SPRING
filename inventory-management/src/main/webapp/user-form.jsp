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

<c:if test="${user != null && user.id != null}">
    <h2>Modifier l'Utilisateur</h2>
    <form action="<%= request.getContextPath() %>/update-user" method="post">
    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
</c:if>
<c:if test="${user == null || user.id == null}">
    <h2>Ajouter un Utilisateur</h2>
    <form action="<%= request.getContextPath() %>/create-user" method="post">
</c:if>

<div class="form-group">
    <label for="firstName">Prénom</label>
    <input type="text" class="form-control" id="firstName" name="firstName" value="<c:out value='${user.firstName}' />" required>
</div>
<div class="form-group">
    <label for="lastName">Nom</label>
    <input type="text" class="form-control" id="lastName" name="lastName" value="<c:out value='${user.lastName}' />" required>
</div>
<div class="form-group">
    <label for="email">Email</label>
    <input type="email" class="form-control" id="email" name="email" value="<c:out value='${user.email}' />" required>
</div>
<div class="form-group">
    <label for="password">Mot de passe</label>
    <input type="password" class="form-control" id="password" name="password" <c:if test="${user == null || user.id == null}">required</c:if>>
    <c:if test="${user != null && user.id != null}">
        <small class="form-text text-muted">Laissez vide pour conserver le mot de passe actuel.</small>
    </c:if>
</div>
<div class="form-group">
    <label for="role">Rôle</label>
    <select class="form-control" id="role" name="role" required>
        <option value="USER" <c:if test="${user != null && user.role == 'USER'}">selected</c:if>>USER</option>
        <option value="ADMIN" <c:if test="${user != null && user.role == 'ADMIN'}">selected</c:if>>ADMIN</option>
    </select>
</div>

<button type="submit" class="btn btn-primary">Enregistrer</button>
<a href="<%= request.getContextPath() %>/users" class="btn btn-secondary">Annuler</a>
</form>

<%@ include file="footer.jsp" %>