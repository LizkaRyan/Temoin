<%@ page import="java.util.List" %>
<%@ page import="mg.itu.ticketing.entity.avion.TypeSiege" %>
<%@ page import="mg.itu.ticketing.dto.ReservationDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mg.itu.ticketing.entity.vol.parametre.TrancheAge" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<TypeSiege> typeSieges = (List<TypeSiege>) request.getAttribute("typeSieges");
    List<TrancheAge> tranchesAges = (List<TrancheAge>) request.getAttribute("tranchesAge");
    String idVol = (String) request.getAttribute("idVol");
    List<String> messages = (List<String>) request.getAttribute("error.messages");
    ReservationDTO reservation = new ReservationDTO();
    if (messages != null) {
        reservation = (ReservationDTO) request.getAttribute("error.reservation");
    } else {
        messages = new ArrayList<String>();
    }
%>
<form action="/Ticketing/reservation" method="post" enctype="multipart/form-data">
    <div class="login-container">
        <div class="login-box">
            <h1>Insertion réservation</h1>
            <% for (String message : messages) { %>
            <p style="color: red"><%=message%>
            </p>
            <%}%>
            <div class="input-group">
                <label>Date du reservation</label>
                <input name="reservation.dateReservation" value="<%= reservation.getDateReservation() %>" type="datetime-local" required>
            </div>
            <div class="input-group">
                <label>Photo du passeport</label>
                <input name="reservation.passePortDTOs[0].photo" type="file" required>
            </div>

            <div class="input-group">
                <select name="reservation.passePortDTOs[0].idTrancheAge">
                    <% for (TrancheAge trancheAge : tranchesAges) {%>
                    <option
                            value="<%= trancheAge.getIdTrancheAge() %>"
                    ><%= trancheAge.getTrancheAge() %>
                    </option>
                    <%}%>
                </select>
            </div>
            <div class="input-group">
                <label>Photo du passeport</label>
                <input name="reservation.passePortDTOs[1].photo" type="file" required>
            </div>

            <div class="input-group">
                <select name="reservation.passePortDTOs[1].idTrancheAge">
                    <% for (TrancheAge trancheAge : tranchesAges) {%>
                    <option
                            value="<%= trancheAge.getIdTrancheAge() %>"
                    ><%= trancheAge.getTrancheAge() %>
                    </option>
                    <%}%>
                </select>
            </div>

            <div class="input-group">
                <select name="reservation.typeVol.idTypeSiege">
                    <% for (TypeSiege typeSiege : typeSieges) {%>
                    <option
                            value="<%= typeSiege.getIdTypeSiege() %>"
                            <% if(typeSiege.getIdTypeSiege().equals(reservation.getIdTypeSiege())){ %>
                            selected
                            <% } %>
                    ><%= typeSiege.getTypeSiege() %>
                    </option>
                    <%}%>
                </select>
            </div>
            <input type="hidden" name="reservation.typeVol.idVol" value="<%= idVol %>">
            <button type="submit" class="login-button">Inserer</button>
        </div>
    </div>
</form>