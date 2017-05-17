<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col">
    <div class="submenu">
        <!-- le img di questa sezione sono considerate informazioni e non semplici decorazioni
             poiché contengono l'immagine della persona o gruppo e consentono di identificarli tramite
             tramite una icona anziché solo tramite testo -->
        <div class="subtitle persone">Persone</div>
        <ul>
            <c:forEach var="user" items="${users}">
                <li>
                    <img src="${user.getUrlImmagine()}" alt="foto ${user.getNome()} ${user.getCognome()}" />
                    <a href="Bacheca?user=${user.getId()}">${user.getNome()} ${user.getCognome()}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="submenu">
        <div class="subtitle gruppi">
            Gruppi
            <a class="iconaFunzione add" href="#"></a>
        </div>
        <ul>
            <c:forEach var="group" items="${groups}">
                <li>
                    <img src="${group.getUrlImmagine()}" alt="foto ${group.getNome()}" />
                    <a href="Gruppo?group=${group.getId()}">${group.getNome()}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="submenuFooter"></div>
</div>
