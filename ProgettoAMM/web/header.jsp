<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>

    <!-- sezione titolo -->
    <div class="title">
        Nerdbook
    </div>

    <!-- sezione di navigazione -->
    <nav>
        <ul>
            <c:if test="${loggedUser != null}">
                <li <c:if test="${page == 'profilo'}">class="active"</c:if>>
                    <a href="Profilo">Profilo</a>
                </li>
                <li <c:if test="${page == 'bacheca'}">class="active"</c:if>>
                    <a href="Bacheca">Bacheca</a>
                </li>
            </c:if>
        </ul>
    </nav>

    <!-- sezione sessione -->
    <div class="logout">
        <c:choose>
            <c:when test="${loggedUser != null}">
                <div class="user">${loggedUser.getNome()}</div>
                <a href="Logout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="Login">Login</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>
