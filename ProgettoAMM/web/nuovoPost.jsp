<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="NuovoPost" method="post">
    <div>
        <label for="testoPost">Testo del post</label>
        <textarea name="testoPost"></textarea>
    </div>
    <div>
        <label for="allegatoPost">Allegato (opzionale)</label>
        <input type="text" name="allegatoPost" />
    </div>
    <div>
        <input type="radio" name="tipologiaPost" value="TEXT" checked="checked" /> Testo
        <input type="radio" name="tipologiaPost" value="IMAGE" /> Immagine
        <input type="radio" name="tipologiaPost" value="LINK" /> Link
    </div>
    
    <input type="hidden" name="autore" value="${loggedUser.getId()}" />
    <input type="hidden" name="userID" value="${user.getId()}" />

    <button type="submit">Crea post</button>
</form>
