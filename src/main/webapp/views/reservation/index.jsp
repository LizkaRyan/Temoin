<%@ page import="java.util.List" %>
<%@ page import="mg.itu.ticketing.entity.vol.Reservation" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Reservation> reservations=(List<Reservation>)request.getAttribute("reservations");
    String message=(String)request.getAttribute("message");
    if(message==null){
        message="";
    }
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
<div class="container mt-3">
    <h2>Tableau de Vols</h2>
    <p style="color: red"><%=message%></p>
    <div class="table-responsive">
        <table>
            <thead>
            <tr>
                <th>Date du réservation</th>
                <th>Date du vol</th>
                <th>Prix du vol</th>
                <th>Avion</th>
                <th>Destination</th>
                <th>Annulation</th>
            </tr>
            </thead>
            <tbody>
            <% for (Reservation reservation : reservations) {%>
            <tr>
                <td><%= reservation.getDateReservation().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) %></td>
                <td><%= reservation.getVol().getDateVol().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) %></td>
                <td><%= reservation.getVol().getPrixVol() %></td>
                <td><%= reservation.getVol().getAvion().getAvion() %></td>
                <td><%= reservation.getVol().getDestination().getVille() %></td>
                <td><a href="/Ticketing/annulation?idReservation=<%= reservation.getIdReservation() %>">Annuler</a></td>
                <!--<td><span class="status completed">Terminé</span></td>-->
                <!--<td><span class="status pending">En attente</span></td>-->
                <!--<td><span class="status in-progress">En cours</span></td>-->
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <div class="footer">
        <span>Total vol: <%= reservations.size() %></span>
    </div>
</div>