<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Cards info</title>
</head>
<body>
<h2> Same cards Count</h2>
<table width="30%">
    <tr>
        <td>Suit</td>
        <td>Facevalue</td>
        <td>Count</td>
    </tr>
    <tr>
        <td>-----------</td>
        <td>-------------</td>
        <td>----------</td>
    </tr>
    <c:forEach var="entry" items="${sameCardsCount}">
        <tr>
            <td>
                <c:out value="${entry.key.suit}"/>
            </td>
            <td>
                <c:out value="${entry.key.facevalue}"/>
            </td>
            <td>
                <c:out value="${entry.value}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<input type="button" value="Back" onclick="javascript:history.back()"/>
<br><br>
<a href="gamePage"> current game page </a>
<br>
<a href="/game"> home page </a>
</body>
</html>
