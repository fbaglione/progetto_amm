<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nerdbook - Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Fabio Baglione">
        <meta name="keywords" content="social network nerd">

        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>

    <body> 
        <!-- contenuto della pagina -->
        <div id="divBody">
            <div id="login">
                <h1>Nerdbook</h1>
                <form action="Login" method="post">
                    <div>
                        <label for="username">Username</label>
                        <input type="text" name="username" id="username" />
                    </div>
                    <div>
                        <label for="pswd">Password</label>
                        <input type="password" name="pswd" id="pswd" />
                    </div>
                    <button type="submit">Login</button>
                </form>
                <c:if test="${datiErrati != null && datiErrati}">
                    <div class="datiErrati">Dati errati, riprovare!</div>
                </c:if>
            </div>
        </div>

    </body>
</html>
