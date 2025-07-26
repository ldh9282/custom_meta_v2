<%@page import="com.custom.met.cmmn.security.utils.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEST</title>
</head>
<body>

<%
	if (SecurityUtils.isAuthenticated()) {
		response.sendRedirect("/METSC01");
	} else {
		response.sendRedirect("/METLG01");
	}

%>

</body>
</html>