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
                    <div class="frase">
                        <img class="imgProfilo" alt="foto ${user.getNome()} ${user.getCognome()}" src="${user.getUrlImmagine()}">
                        <div class="autore">${user.getNome()} ${user.getCognome()}</div>
                        <div class="contenuto">"${user.getFrase()}"</div>
                        <c:choose>
                            <c:when test="${friendship}">
                            <div class="friendship"></div>
                            </c:when>
                            <c:otherwise>
                                <div class="addFriendship"></div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                        
                    <c:if test="${friendship}">
                        <div class="nuovoPost">
                            <jsp:include page="nuovoPost.jsp"/>
                        </div>
                    </c:if>

                    
                    <!-- sezione dei posts-->
                    <div id="listaPosts">

                        <c:forEach var="post" items="${posts}">
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
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>