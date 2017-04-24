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
                    <div id='confermaPost'>
                        <h1>Conferma post</h1>
                        <h2>Mittente</h2>
                        <p>Nerdbook è rivolto a tutte le persone e animali della facoltà di Cagliari.</p>
                        <h2>Proprietario bacheca</h2>
                        <p>Tutte le persone possono partecipare ma solo se sono "nerd".</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>