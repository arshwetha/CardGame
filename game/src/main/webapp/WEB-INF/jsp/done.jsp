<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>done</title>
</head>
<body>
<h1>${message}</h1>
<br/>

<input type="button" value="Back" onclick="javascript:history.back()"/>
<br><br>
<a href="gamePage"> current Game page </a>
<br><br>
<a href="/game"> home page </a>
</body>
</html>
