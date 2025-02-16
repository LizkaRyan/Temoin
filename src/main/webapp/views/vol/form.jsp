<%@ page import="mg.itu.temoin.entity.avion.Avion" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.temoin.entity.vol.Ville" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Avion> avions=(List<Avion>)request.getAttribute("avions");
    List<Ville> villes=(List<Ville>)request.getAttribute("villes");
%>
<form action="/Temoin/vol" method="post">
    <div class="login-container">
        <div class="login-box">
            <h1>Insertion vol</h1>
            <form>
                <div class="input-group">
                    <label>Date du vol</label>
                    <input name="vol.dateVol" type="date" required>
                </div>
                <div class="input-group">
                    <label for="prix">Prix du vol</label>
                    <input name="vol.prixVol" type="number" required>
                </div>
                <div class="input-group">
                    <select name="vol.idVille">
                        <% for (Ville ville:villes){%>
                        <option name="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                        <%}%>
                    </select>
                </div>
                <div class="input-group">
                    <select name="vol.idAvion">
                        <% for (Avion avion:avions){%>
                        <option name="<%= avion.getIdAvion() %>"><%= avion.getDateFabrication() %></option>
                        <%}%>
                    </select>
                </div>
                <button type="submit" class="login-button">Inserer</button>
            </form>
        </div>
    </div>
</form>