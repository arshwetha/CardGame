<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>PlayersInfo for the selected game</title>
</head>
<body>
<h2>Players List</h2>
<table width="50%">
    <tr>
        <td>Player</td>
        <td>AddedValue</td>
    </tr>
    <tr>
        <td>---------------</td>
        <td>---------------</td>
    </tr>
    <c:forEach items="${players}" var="player" varStatus="status">
        <tr>
            <td>${player.name}</td>
            <td>${player.addedValue}</td>
        </tr>
    </c:forEach>
</table>
<br>
<input type="button" value="Back" onclick="javascript:history.back()"/>
<br><br>
<a href="gamePage"> current game page </a>
<br><br>
<a href="/game"> home page </a>
</body>
</html>
