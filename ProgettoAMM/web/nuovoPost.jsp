<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="NuovoPost" method="post">
    <div>
        <label for="testoPost">Testo del post</label>
        <textarea name="testoPost" id="testoPost"></textarea>
    </div>
    <div>
        <label for="allegatoPost">Allegato (opzionale)</label>
        <input type="url" name="allegatoPost" id="allegatoPost" />
    </div>
    <div>
        <input type="radio" name="tipologiaPost" value="TEXT" checked="checked" /> Testo
        <input type="radio" name="tipologiaPost" value="IMAGE" /> Immagine
        <input type="radio" name="tipologiaPost" value="LINK" /> Link
    </div>
    
    <input type="hidden" name="autore" value="${loggedUser.getId()}" />
    <c:if test="${group == null}">
        <input type="hidden" name="userID" value="${user.getId()}" />
    </c:if>
    <c:if test="${group != null}">
        <input type="hidden" name="groupID" value="${group.getId()}" />
    </c:if>
    
    <button type="submit">Crea post</button>
</form>
