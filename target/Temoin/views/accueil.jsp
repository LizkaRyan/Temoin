<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirVoyage - Réservez vos billets d'avion</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        header {
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('/api/placeholder/1920/1080') center/cover;
            height: 100vh;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            padding: 0 20px;
        }

        nav {
            position: fixed;
            top: 0;
            width: 100%;
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

        .hero-text {
            margin-bottom: 30px;
        }

        h1 {
            font-size: 48px;
            margin-bottom: 20px;
        }

        .subtitle {
            font-size: 24px;
            margin-bottom: 40px;
        }

        .cta-button {
            background-color: #00a8ff;
            color: white;
            padding: 15px 40px;
            border-radius: 30px;
            text-decoration: none;
            font-size: 18px;
            transition: background-color 0.3s;
        }

        .cta-button:hover {
            background-color: #0088cc;
        }

        .features {
            padding: 80px 20px;
            background-color: #f5f5f5;
            text-align: center;
        }

        .features h2 {
            color: #333;
            margin-bottom: 50px;
        }

        .features-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 40px;
            max-width: 1200px;
            margin: 0 auto;
        }

        .feature-card {
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .feature-card h3 {
            color: #333;
            margin: 20px 0;
        }

        .feature-card p {
            color: #666;
            line-height: 1.6;
        }

        @media (max-width: 768px) {
            nav {
                flex-direction: column;
                gap: 20px;
            }

            h1 {
                font-size: 36px;
            }

            .subtitle {
                font-size: 20px;
            }
        }
    </style>
</head>
<body>
<nav>
    <a href="#" class="logo">AirVoyage</a>
    <div class="nav-links">
        <a href="/Temoin/">Accueil</a>
        <a href="#">Réservation</a>
        <a href="#">Annulation réservation</a>
    </div>
</nav>

<header>
    <div class="hero-text">
        <h1>Voyagez vers vos rêves</h1>
        <p class="subtitle">Découvrez le monde avec nos meilleures offres de vols</p>
        <a href="/Temoin/utilisateur/login" class="cta-button">Réserver maintenant</a>
    </div>
</header>

<section class="features">
    <h2>Pourquoi nous choisir ?</h2>
    <div class="features-grid">
        <div class="feature-card">
            <h3>Meilleurs prix garantis</h3>
            <p>Nous vous proposons les tarifs les plus compétitifs du marché pour toutes vos destinations.</p>
        </div>
        <div class="feature-card">
            <h3>Flexibilité maximale</h3>
            <p>Modifiez ou annulez votre réservation sans frais jusqu'à 24h avant le départ.</p>
        </div>
        <div class="feature-card">
            <h3>Service client 24/7</h3>
            <p>Notre équipe est disponible jour et nuit pour répondre à toutes vos questions.</p>
        </div>
    </div>
</section>
</body>
</html>