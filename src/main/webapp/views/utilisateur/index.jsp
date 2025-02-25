<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%
    List<String> messages = (List<String>) request.getAttribute("error.messages");
    if (messages == null) {
        messages = new ArrayList<String>();
    }
%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form action="/Temoin/utilisateur/login" method="post">
    <div class="login-container">
        <div class="login-box">
            <h1>Connexion</h1>
            <% for (String message : messages) { %>
            <p style="color: red"><%=message%>
                <%}%>
            </p>
            <form>
                <div class="input-group">
                    <label for="email">Adresse e-mail</label>
                    <input name="email" type="text" id="email" required>
                </div>
                <div class="input-group">
                    <label for="password">Mot de passe</label>
                    <input name="password" type="password" id="password" required>
                </div>
                <button type="submit" class="login-button">Se connecter</button>
            </form>
        </div>
    </div>
</form>