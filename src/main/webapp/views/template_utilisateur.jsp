<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String body=(String)request.getAttribute("body");
%>
<!DOCTYPE html>
<html lang="fr">
<link>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirVoyage - Connexion</title>
    <link rel="stylesheet" href="/Ticketing/static/style.css"/>
    <link rel="stylesheet" href="/Ticketing/static/bootstrap-5/css/bootstrap.min.css"/>
</head>
<body>
<nav>
    <a href="#" class="logo">AirVoyage</a>
    <div class="nav-links">
        <a href="/Temoin">Accueil</a>
        <a href="#">Réservation</a>
        <a href="#">Annulation réservation</a>
        <a href="#">Mes réservations</a>
    </div>
</nav>
<jsp:include page="<%=body%>"/>
</body>
</html>