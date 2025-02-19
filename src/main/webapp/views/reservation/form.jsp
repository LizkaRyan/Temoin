<%@ page import="mg.itu.temoin.entity.avion.Avion" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.temoin.entity.vol.Ville" %>
<%@ page import="mg.itu.temoin.entity.avion.TypeSiege" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<TypeSiege> typeSieges=(List<TypeSiege>)request.getAttribute("typeSieges");
  String idVol=(String)request.getAttribute("idVol");
%>
<form action="/Temoin/reservation" method="post" enctype="multipart/form-data">
  <div class="login-container">
    <div class="login-box">
      <h1>Insertion vol</h1>
      <form>
        <div class="input-group">
          <label>Date du reservation</label>
          <input name="reservation.dateReservation" type="datetime-local" required>
        </div>
        <div class="input-group">
          <label>Photo du passeport</label>
          <input name="passeport" type="file" required>
        </div>
        <div class="input-group">
          <select name="reservation.idTypeSiege">
            <% for (TypeSiege typeSiege:typeSieges){%>
            <option value="<%= typeSiege.getIdTypeSiege() %>"><%= typeSiege.getTypeSiege() %></option>
            <%}%>
          </select>
        </div>
        <input type="hidden" name="reservation.idVol" value="<%= idVol %>">
        <button type="submit" class="login-button">Inserer</button>
      </form>
    </div>
  </div>
</form>