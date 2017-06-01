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
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/filter.js"></script>
    </head>

    <body>
        <div id="pagina">

            <!-- header della pagina -->
            <jsp:include page="header.jsp"/>

            <!-- corpo della pagina -->
            <div id="divBody">

                <!-- submenu laterale -->
                <jsp:include page="submenu.jsp"/>

                <!-- contenuto principale -->
                <div class="cont">
                    <div class="nuovoPost">
                    <form action="NuovoGruppo" method="post">
                        <div>
                            <label for="nome">Nome del gruppo</label>
                            <input type="text" name="nome" id="nome" required/>
                        </div>
                        <div>
                            <label for="urlImmagine">Url immagine profilo</label>
                            <input type="url" name="urlImmagine" id="urlImmagine" />
                        </div>

                        <input type="hidden" name="nuovoGruppo" value="nuovoGruppo" />
                        <button type="submit">Crea gruppo</button>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>