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
<h2> Cards Per Suit Count</h2>
<table width="30%">
    <tr>
        <td>Suit</td>
        <td>Count</td>
    </tr>
    <tr>
        <td>-----------</td>
        <td>--------</td>
    </tr>
    <c:forEach var="entry" items="${cardsPerSuit}">
        <tr>
            <td>
                <c:out value="${entry.key}"/>
            </td>
            <td>
                <c:out value="${entry.value}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<h1></h1>
<input type="button" value="Back" onclick="javascript:history.back()"/>
<br>
<a href="gamePage"> current game page </a>
<br><br>
<a href="/game"> home page </a>
<br>
</body>
</html>
