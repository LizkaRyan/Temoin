<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form action="/Temoin/utilisateur/login" method="post">
    <div class="login-container">
        <div class="login-box">
            <h1>Connexion</h1>
            <form>
                <div class="input-group">
                    <label for="email">Adresse e-mail</label>
                    <input name="utilisateur.email" type="email" id="email" required>
                </div>
                <div class="input-group">
                    <label for="password">Mot de passe</label>
                    <input name="utilisateur.password" type="password" id="password" required>
                </div>
                <button type="submit" class="login-button">Se connecter</button>
            </form>
        </div>
    </div>
</form>