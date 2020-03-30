<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>info</title>
</head>
<body>
<h2>Available Cards with ${player.name}</h2>
<table width="50%">
    <tr>
        <td>Facevalue</td>
        <td>Suit</td>
        <td>Deck</td>
    </tr>
    <tr>
        <td>---------------</td>
        <td>--------</td>
        <td>------</td>
    </tr>
    <c:forEach items="${player.cards}" var="card" varStatus="status">
        <tr>
            <td>${card.facevalue}</td>
            <td>${card.suit}</td>
            <td>${card.deckId+1}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<input type="button" value="Back" onclick="javascript:history.back()"/>
<br><br>
<a href="gamePage"> current game page </a>
<br><br>
<a href="/game"> home page </a>
</body>
</html>
