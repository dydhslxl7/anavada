<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>anavada error page</title>
</head>
<body style="text-align:center;">
<h1><br/><br/>에러페이지입니다.</h1>
<%= request.getAttribute("message") %>
</body>
</html>