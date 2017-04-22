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

                <!-- submenu laterale -->
                <jsp:include page="submenu.jsp"/>

                <!-- contenuto principale -->
                <div class="cont">
                    <div id='profilo'>
                        <h1>Profilo</h1>
                        <div class="iconaProfilo">
                            <!-- non è una immagina decorativa ma serve all'utente a capire quale immagine usa come avatar -->
                            <img src="../img/iconaProfilo.png" alt="icona profilo" />
                        </div>
                        <form action="servlet.java" method="post">
                            <div>
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" id="nome" />
                            </div>

                            <div>
                                <label for="cognome">Cognome</label>
                                <input type="text" name="cognome" id="cognome" />
                            </div>

                            <div>
                                <label for="urlimmagine">Url immagine profilo</label>
                                <input type="url" name="urlimmagine" id="urlimmagine" />
                            </div>

                            <div>
                                <label for="frase">Frase di presentazione</label>
                                <textarea name="frase" id="frase"></textarea>
                            </div>

                            <div>
                                <label for="nascita">Data di nascita</label>
                                <input type="date" name="nascita" id="nascita" />
                            </div>

                            <div>
                                <label for="pswd">Password</label>
                                <input type="password" name="pswd" id="pswd" />
                            </div>

                            <div>
                                <label for="pswd2">Conferma password</label>
                                <input type="password" name="pswd2" id="pswd2" />
                            </div>

                            <button type="submit">Invia</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
