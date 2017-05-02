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
                    <div class="nuovoPost">
                        <div class="datiUtenti">
                            Post sulla bacheca di ${user.getNome()} ${user.getCognome()}
                        </div>
                        <form action="NuovoPost" method="post">
                            <div>
                                <div class="label">Autore</div>
                                <div class="content">${post.getUser().getNome()} ${post.getUser().getCognome()}</div>
                            </div>
                            <div>
                                <div class="label">Testo del post</div>
                                <div class="content">${post.getText()}</div>
                            </div>
                            <div>
                                <div class="label">Allegato (opzionale)</div>
                                <div class="content">${post.getContent()}</div>
                            </div>
                            <div class="label">Tipologia</div>
                            <div class="content">${post.getPostType()}</div>

                            <input type="hidden" name="testoPost" value="${post.getText()}" />
                            <input type="hidden" name="allegatoPost" value="${post.getContent()}"/>
                            <input type="hidden" name="tipologiaPost" value="${post.getPostType()}" />
                            <input type="hidden" name="autore" value="${post.getUser().getId()}" />
                            <input type="hidden" name="userID" value="${user.getId()}" />
                            <input type="hidden" name="conferma" value="true" />

                            <button type="submit">Conferma post</button>
                        </form>
                            <c:if test="${confermaInvio != null}">
                                <div class="datiAggiornati">${confermaInvio}</div>
                            </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>