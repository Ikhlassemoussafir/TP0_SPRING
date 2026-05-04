<%@ include file="header.jsp" %>

<div class="jumbotron">
    <h1 class="display-4">Bienvenue dans l'application de Gestion d'Inventaire</h1>
    <p class="lead">Cette application démontre l'intégration de JSP, Servlet, Hibernate avec Jakarta EE.</p>
    <hr class="my-4">
    <p>Utilisez les liens ci-dessous pour naviguer dans l'application :</p>

    <div class="row">
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-header bg-primary text-white">Gestion des Produits</div>
                <div class="card-body">
                    <p>Gérez l'inventaire des produits</p>
                    <a class="btn btn-primary" href="<%= request.getContextPath() %>/products" role="button">Voir les Produits</a>
                    <a class="btn btn-success" href="<%= request.getContextPath() %>/product-form" role="button">Ajouter un Produit</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-header bg-success text-white">Gestion des Catégories</div>
                <div class="card-body">
                    <p>Gérez les catégories de produits</p>
                    <a class="btn btn-primary" href="<%= request.getContextPath() %>/categories" role="button">Voir les Catégories</a>
                    <a class="btn btn-success" href="<%= request.getContextPath() %>/category-form" role="button">Ajouter une Catégorie</a>
                </div>
            </div>
        </div>
        <c:if test="${sessionScope.userRole == 'ADMIN'}">
            <div class="col-md-4">
                <div class="card mb-4">
                    <div class="card-header bg-danger text-white">Gestion des Utilisateurs</div>
                    <div class="card-body">
                        <p>Gérez les utilisateurs (Admin)</p>
                        <a class="btn btn-primary" href="<%= request.getContextPath() %>/users" role="button">Voir les Utilisateurs</a>
                        <a class="btn btn-success" href="<%= request.getContextPath() %>/user-form" role="button">Ajouter un Utilisateur</a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>

<%@ include file="footer.jsp" %>