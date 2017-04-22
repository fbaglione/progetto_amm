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
                        <div class="autore">Djanni</div>
                        <div class="contenuto">"Do or do not. There is no try."</div>
                    </div>

                    <!-- sezione dei posts-->
                    <div id="listaPosts">

                        <!-- post senza allegati -->
                        <div class="post">
                            <div class="autore">
                                <img class="profilePic" alt="foto Lucio" src="../img/lucio.bmp">
                                <div class="nome">Lupo Lucio</div>
                            </div>
                            <div class="contenuto">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam non nisi volutpat, imperdiet ante vitae, dignissim dui. Aliquam imperdiet tristique mauris, vitae consequat massa consequat et. Nullam convallis, felis vitae semper luctus, mauris orci efficitur nisi, ac placerat augue arcu sit amet erat. Ut placerat at orci ac semper. Praesent sed iaculis nisi, sit amet accumsan erat. Phasellus quis porta eros. Praesent lobortis mauris ullamcorper, vestibulum tortor quis, pretium nunc. Nulla facilisi. Curabitur condimentum magna odio. Sed lacinia leo feugiat, tempus augue id, ornare nunc.</p>
                            </div>
                        </div>

                        <!-- post con immagine -->
                        <div class="post">
                            <div class="autore">
                                <img class="profilePic" alt="foto Scateni" src="../img/scateni.jpg">
                                <div class="nome">Riccardo Scateni</div>
                            </div>
                            <div class="contenuto">
                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's Djanni standard.</p>
                                <img alt="foto post 1" src="../img/gattodjanni.jpg">
                            </div>
                        </div>

                        <!-- post con link -->
                        <div class="post">
                            <div class="autore">
                                <img class="profilePic" alt="foto Spano" src="../img/spano.jpg">
                                <div class="nome">Davide Spano</div>
                            </div>
                            <div class="contenuto">
                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                                <a href="http://www.lipsum.com/">http://www.lipsum.com/</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>