<%@ page import="mg.itu.ticketing.entity.avion.Avion" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.ticketing.entity.vol.Ville" %>
<%@ page import="mg.itu.ticketing.dto.VolDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Avion> avions = (List<Avion>) request.getAttribute("avions");
    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    List<String> messages = (List<String>) request.getAttribute("error.messages");
    VolDTO volDTO = new VolDTO();
    if (messages != null) {
        volDTO = (VolDTO) request.getAttribute("error.vol");
    }
    else{
        messages=new ArrayList<String>();
    }
%>
<form action="/Ticketing/vol" method="post">
    <div class="login-container">
        <div class="login-box">
            <h1>Insertion vol</h1>
            <% for(String message:messages){ %>
            <p style="color: red"><%=message%></p>
            <%}%>
            <div class="input-group">
                <label>Date du vol</label>
                <input name="vol.dateVol" type="datetime-local" value="<%= volDTO.getDateVol() %>" required>
            </div>
            <div class="input-group">
                <label for="prix">Prix du vol</label>
                <input name="vol.prixVol" type="number" value="<%= volDTO.getPrixVol() %>" required>
            </div>
            <div class="input-group">
                <select name="vol.idVille">
                    <% for (Ville ville : villes) {%>
                    <option
                            value="<%= ville.getIdVille() %>"
                            <% if (volDTO.getIdVille() == ville.getIdVille()) { %>
                            selected
                            <%}%>
                    ><%= ville.getVille() %>
                    </option>
                    <%}%>
                </select>
            </div>
            <div class="input-group">
                <select name="vol.idAvion">
                    <% for (Avion avion : avions) {%>
                    <option
                            value="<%= avion.getIdAvion() %>"
                            <% if (volDTO.getIdAvion() == avion.getIdAvion()) { %>
                            selected
                            <%}%>
                    ><%= avion.getAvion() %>
                    </option>
                    <%}%>
                </select>
            </div>
            <button type="submit" class="login-button">Inserer</button>
        </div>
    </div>
</form>