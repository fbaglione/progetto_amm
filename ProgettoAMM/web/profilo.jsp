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
                            <img src="${user.getUrlImmagine()}" alt="icona profilo" />
                        </div>
                        <form action="Profilo" method="post">
                            <div>
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" id="nome" value="${user.getNome()}" />
                            </div>

                            <div>
                                <label for="cognome">Cognome</label>
                                <input type="text" name="cognome" id="cognome" value="${user.getCognome()}" />
                            </div>

                            <div>
                                <label for="urlImmagine">Url immagine profilo</label>
                                <input type="url" name="urlImmagine" id="urlImmagine" value="${user.getUrlImmagine()}" />
                            </div>

                            <div>
                                <label for="frase">Frase di presentazione</label>
                                <textarea name="frase" id="frase">${user.getFrase()}</textarea>
                            </div>

                            <div>
                                <label for="dataDiNascita">Data di nascita</label>
                                <input type="date" name="dataDiNascita" id="dataDiNascita" value="${user.getDataDiNascita()}" />
                            </div>

                            <div>
                                <label for="pswd">Password</label>
                                <input type="password" name="pswd" id="pswd" value="${user.getPassword()}" />
                            </div>

                            <input type="hidden" name="userID" value="${user.getId()}" />
                            
                            <button type="submit">Invia</button>

                            <c:if test="${datiAggiornati != null && datiAggiornati}">
                                <div class="datiAggiornati">Dati aggiornati!</div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
