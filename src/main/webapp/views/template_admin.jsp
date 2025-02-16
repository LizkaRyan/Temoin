<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String body=(String)request.getAttribute("body");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirVoyage - Connexion</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), center/cover fixed;
            display: flex;
            flex-direction: column;
        }

        nav {
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.8);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            color: white;
            font-size: 24px;
            font-weight: bold;
            text-decoration: none;
        }

        .nav-links {
            display: flex;
            gap: 30px;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s;
        }

        .nav-links a:hover {
            color: #00a8ff;
        }

        .login-container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .login-box {
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            width: 100%;
            max-width: 400px;
        }

        .login-box h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
            font-size: 28px;
        }

        .input-group {
            margin-bottom: 20px;
        }

        .input-group label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: 500;
        }

        .input-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        .input-group input:focus {
            outline: none;
            border-color: #00a8ff;
        }

        .login-button {
            width: 100%;
            background-color: #00a8ff;
            color: white;
            padding: 15px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .login-button:hover {
            background-color: #0088cc;
        }

        .login-footer {
            margin-top: 20px;
            text-align: center;
        }

        .login-footer a {
            color: #00a8ff;
            text-decoration: none;
            transition: color 0.3s;
        }

        .login-footer a:hover {
            color: #0088cc;
        }

        .remember-me {
            display: flex;
            align-items: center;
            gap: 8px;
            margin-bottom: 20px;
        }

        .remember-me input[type="checkbox"] {
            width: auto;
        }

        @media (max-width: 768px) {
            nav {
                flex-direction: column;
                gap: 20px;
            }

            .login-box {
                padding: 30px;
            }
        }
    </style>
</head>
<body>
<nav>
    <a href="#" class="logo">AirVoyage</a>
    <div class="nav-links">
        <a href="/Temoin">Accueil</a>
        <a href="#">Insertion vol</a>
        <a href="#">Liste Vol</a>
        <a href="#">Réservation</a>
        <a href="#">Annulation réservation</a>
        <a href="#">Mes réservations</a>
    </div>
</nav>
<jsp:include page="<%=body%>"/>
</body>
</html>