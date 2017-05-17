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
                    <div class="post">
                        <div class="autore">
                            <img class="profilePic" alt="foto ${post.getAutore().getNome()} ${post.getAutore().getCognome()}" src="${post.getAutore().getUrlImmagine()}">
                            <div class="nome">${post.getAutore().getNome()} ${post.getAutore().getCognome()}</div>
                        </div>
                        <div class="contenuto">
                            <c:if test="${post.postType == 'TEXT'}">
                                <p>${post.text}</p>
                            </c:if>
                            <c:if test="${post.postType == 'IMAGE'}">
                                <p>${post.text}</p>
                                <img alt="Post con foto" src="${post.content}">
                            </c:if>
                            <c:if test="${post.postType == 'LINK'}">
                                <p>${post.text}</p>
                                <a href="${post.content}">${post.content}</a>
                            </c:if>
                        </div>
                    </div>
                    <div class="nuovoPost">
                        <div class="datiUtenti">
                            Post sulla bacheca di ${user.getNome()} ${user.getCognome()}
                        </div>
                        <form action="NuovoPost" method="post">

                            <input type="hidden" name="testoPost" value="${post.getText()}" />
                            <input type="hidden" name="allegatoPost" value="${post.getContent()}"/>
                            <input type="hidden" name="tipologiaPost" value="${post.getPostType()}" />
                            <input type="hidden" name="autore" value="${post.getAutore().getId()}" />

                            <c:if test="${group == null}">
                                <input type="hidden" name="userID" value="${user.getId()}" />
                            </c:if>
                            <c:if test="${group != null}">
                                <input type="hidden" name="groupID" value="${group.getId()}" />
                            </c:if>
                            
                            <input type="hidden" name="conferma" value="true" />

                            <button type="submit">Invia post</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>