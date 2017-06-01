$(document).ready(function () {

    var elemUser = function (user) {
        
        var $elem = $("<li></li>");
        var $imgProfilo = $("<img />").attr({
            "src": user.urlImmagine,
            "alt": "foto " + user.nome + " " + user.cognome});
        var $link = $("<a></a>")
                .attr({"href": "Bacheca?user=" + user.id})
                .text(user.nome + " " + user.cognome);

        $imgProfilo.appendTo($elem);
        $link.appendTo($elem);
        
        return $elem;
    };

    var funRicerca = function () {

        var ricerca = $("#ricUtentiInput")[0].value;

        $.ajax({
            url: "filter.json",
            data: {
                cmd: "search",
                q: ricerca
            },
            dataType: "json",
            success: function (data, textStatus, jqXHR) {

                var $ulUtenti = $("#ricUtentiRes > ul");

                // Svuota la lista degli utenti
                $ulUtenti.empty();

                // Controlla i dati ottenuti
                if (data.length !== 0) {
                    
                    // Cicla ogni utente creando il contenuto apposito
                    data.forEach(function (user) {
                        elemUser(user).appendTo($ulUtenti);
                    });
                } else {
                    $ulUtenti.html("<li>Nessun elemento trovato</li>");
                }

                console.log(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
    };

    $("#ricUtentiButton").click(funRicerca);
    $("#ricUtentiInput").keyup(funRicerca);
    //$("#ricUtentiInput").change(funRicerca);
});

