<%-- 
    Document   : filter
    Created on : Jun 1, 2017, 12:39:01 PM
    Author     : DatrhiilPC
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:array>
    <c:forEach var="user" items="${listaUtenti}">
        <json:object>
            <json:property name="id" value="${user.getId()}"/>
            <json:property name="nome" value="${user.getNome()}"/>
            <json:property name="cognome" value="${user.getCognome()}"/>
            <json:property name="urlImmagine" value="${user.getUrlImmagine()}"/>
        </json:object>
    </c:forEach>
</json:array>
