<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col">
    <div class="submenu">
        <!-- le img di questa sezione sono considerate informazioni e non semplici decorazioni
             poiché contengono l'immagine della persona o gruppo e consentono di identificarli tramite
             tramite una icona anziché solo tramite testo -->
        <div class="subtitle persone">Persone</div>
        <ul>
            <li><img src="img/scateni.jpg" alt="Scateni" />Riccardo Scateni</li>
            <li><img src="img/spano.jpg" alt="Spano" />Davide Spano</li>
            <li><img src="img/lucio.bmp" alt="Lucio" />Lupo Lucio</li>
        </ul>
    </div>
    <div class="submenu">
        <div class="subtitle gruppi">
            Gruppi
            <a class="iconaFunzione add" href="#"></a>
        </div>
        <ul>
            <li><img src="img/mongolfieristi.png" alt="Scateni" />Mognolfieristi</li>
            <li><img src="img/animali.bmp" alt="Scateni" />Animali</li>
        </ul>
    </div>
    <div class="submenuFooter"></div>
</div>
