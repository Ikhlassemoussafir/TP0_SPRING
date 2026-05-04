<%@ include file="header.jsp" %>

<div class="alert alert-danger">
    <h3>Accès Refusé</h3>
    <p>Vous n'avez pas les permissions nécessaires pour accéder à cette page.</p>
    <p>Seuls les administrateurs peuvent accéder à la gestion des utilisateurs.</p>
    <a href="<%= request.getContextPath() %>/" class="btn btn-primary">Retour à l'accueil</a>
</div>

<%@ include file="footer.jsp" %>
