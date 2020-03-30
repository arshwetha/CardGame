<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>done</title>
</head>
<body>
<h1>${message}</h1>
<br>
<c:choose>
    <c:when test="${empty gamesList}">
    </c:when>
    <c:otherwise>
        Game ids List:
        <c:forEach var="entry" items="${gamesList}">
            <p><c:out value="${entry}"/></p>
        </c:forEach>
    </c:otherwise>
</c:choose>
<br>
<input type="button" value="Back" onclick="javascript:history.back()"/>
<br><br>
<a href="/game"> home page </a>
</body>
</html>

