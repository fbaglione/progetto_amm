<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nerdbook</title>

        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Fabio Baglione">
        <meta name="keywords" content="social network nerd">

        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>

    <body>
        <div id="pagina">

            <!-- header della pagina -->
            <jsp:include page="header.jsp"/>

            <!-- corpo della pagina -->
            <div id="divBody">

                <!-- contenuto principale -->
                <div class="cont">
                    <h1>Cos'è Nerdbook?</h1>
                    <h2>Sommario</h2>
                    <ol>
                        <li><a href="#sezioneAChi">A chi è rivolto</a></li>
                        <li><a href="#sezioneIscriversi">Come iscriversi</a></li>
                        <li><a href="#sezioneCosto">Quanto costa</a></li>
                    </ol>

                    <h2 id="sezioneAChi">A chi è rivolto</h2>
                    <h3>Soggetti</h3>
                    <p>Nerdbook è rivolto a tutte le persone e animali della facoltà di Cagliari.</p>
                    <h3>Persone</h3>
                    <p>Tutte le persone possono partecipare ma solo se sono "nerd".</p>
                    <h3>Animali</h3>
                    <p>Gli animali possono partecipare ma solo se amici degli iscritti, come Djanni.</p>

                    <h2 id="sezioneIscriversi">Come iscriversi</h2>
                    <h3>Pagina</h3>
                    <p>Per iscriversi è necessario usare la pagina apposita.</p>
                    <h3>Amministratore</h3>
                    <p>Se necessario è possibile contattare l'amministratore e richiedere l'iscrizione.</p>

                    <h2 id="sezioneCosto">Quanto costa</h2>
                    <h3>Contributo</h3>
                    <p>Per iscriversi è possibile offrire un contributo che verrà utilizzato per ampliare e mantenere Nerdbook.</p>
                </div>
            </div>
        </div>
    </body>
</html>