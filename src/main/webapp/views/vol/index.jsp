<%@ page import="mg.itu.temoin.entity.vol.Vol" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.temoin.entity.vol.Ville" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Vol> vols = (List<Vol>) request.getAttribute("vols");
    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    String idVille=(String)request.getAttribute("idVille");
    LocalDateTime dateMin=(LocalDateTime)request.getAttribute("dateMin");
    LocalDateTime dateMax=(LocalDateTime)request.getAttribute("dateMax");
%>
<style>
    .container {
        width: 90%;
        max-width: 1000px;
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        padding: 25px;
        overflow: hidden;
    }

    h2 {
        color: #2c3e50;
        font-weight: 600;
        margin-bottom: 25px;
        padding-bottom: 10px;
        border-bottom: 1px solid #e0e6ed;
    }

    .table-responsive {
        overflow-x: auto;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
    }

    thead {
        background-color: #3498db;
        color: white;
    }

    th, td {
        padding: 12px 15px;
        text-align: left;
        border-bottom: 1px solid #e0e6ed;
    }

    th {
        font-weight: 600;
        letter-spacing: 0.5px;
        text-transform: uppercase;
        font-size: 0.85em;
    }

    tr:nth-child(even) {
        background-color: #f9fafb;
    }

    tr:hover {
        background-color: #f0f4f8;
    }

    td:first-child {
        font-weight: 500;
    }

    .status {
        display: inline-block;
        padding: 5px 10px;
        border-radius: 4px;
        font-size: 0.85em;
        font-weight: 500;
    }

    .completed {
        background-color: #e8f5e9;
        color: #2e7d32;
    }

    .in-progress {
        background-color: #fff8e1;
        color: #ff8f00;
    }

    .pending {
        background-color: #efebe9;
        color: #6d4c41;
    }

    .footer {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
        padding-top: 15px;
        border-top: 1px solid #e0e6ed;
        color: #7f8c8d;
        font-size: 0.9em;
    }
</style>
<form action="/Temoin/vol" method="get">
    <div class="login-container">
        <div class="login-box">
            <h1>Recherche multi critère</h1>
            <form>
                <div class="input-group">
                    <label>Date du vol minimum</label>
                    <input name="vol.dateTimeMin" type="datetime-local" value="<%= dateMin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) %>">
                </div>
                <div class="input-group">
                    <label>Date du vol maximum</label>
                    <input name="vol.dateTimeMax" type="datetime-local" value="<%= dateMax.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) %>">
                </div>
                <div class="input-group">
                    <select name="vol.idVille">
                        <option value="Tous">Tous</option>
                        <% for (Ville ville : villes) {%>
                        <option
                                value="<%= ville.getIdVille() %>"
                                <% if(idVille.equals(ville.getIdVille())) {%> selected <%}%>>
                            <%= ville.getVille() %></option>
                        <%}%>
                    </select>
                </div>
                <button type="submit" class="login-button">Inserer</button>
            </form>
        </div>
    </div>
</form>
<div class="container mt-3">
    <h2>Tableau de Vols</h2>
    <div class="table-responsive">
        <table>
            <thead>
            <tr>
                <th>Date du vol</th>
                <th>Prix du vol</th>
                <th>Avion</th>
                <th>Destination</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <% for (Vol vol : vols) {%>
            <tr>
                <td><%= vol.getDateVol().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) %></td>
                <td><%= vol.getPrixVol() %></td>
                <td><%= vol.getAvion().getAvion() %></td>
                <td><%= vol.getDestination().getVille() %></td>
                <td><span class="status completed">Terminé</span></td>
                <!--<td><span class="status pending">En attente</span></td>-->
                <!--<td><span class="status in-progress">En cours</span></td>-->
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <div class="footer">
        <span>Total vol: <%= vols.size() %></span>
    </div>
</div>