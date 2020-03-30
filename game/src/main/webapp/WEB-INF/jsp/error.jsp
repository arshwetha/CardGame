<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<h1>Error: ${message}</h1>
<br/>

<input type="button" value="Back" onclick="javascript:history.back()"/>
<br><br>
<a href="gamePage"> current game page </a>
<br><br>
<a href="/game"> home page </a>
</body>
</html>
