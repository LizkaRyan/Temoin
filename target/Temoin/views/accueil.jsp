<%@ page import="java.util.List" %>
<%@ page import="mg.itu.temoin.entity.avion.Modele" %><%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 15/02/2025
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%
    Modele modele=(Modele)request.getAttribute("modele");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%= modele.getModele() %>
    Accueil
</body>
</html>
