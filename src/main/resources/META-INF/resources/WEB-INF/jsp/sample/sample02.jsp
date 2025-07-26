<%@page import="java.util.Date"%>
<%@page import="com.custom.met.cmmn.utils.DateUtils"%>
<%@page import="com.custom.met.cmmn.security.utils.SecurityUtils"%>
<%@page import="org.slf4j.MDC"%>
<%@page import="com.custom.met.cmmn.utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String url = StringUtils.NVL(MDC.get("identifier"), "");
	String username = StringUtils.NVL(SecurityUtils.getUsername(), "");
	request.setAttribute("serverTime", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	request.setAttribute("loginTime", StringUtils.NVL((String) session.getAttribute("loginTime"), ""));

%>    
<!DOCTYPE html>
<html>
<head>
	<title><%= url %> | 메타관리시스템</title>
	<jsp:include page="/WEB-INF/jsp/include/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/scriptHeader.jsp"></jsp:include>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/WEB-INF/jsp/include/sidebar.jsp"></jsp:include>
	<!-- End Sidebar-->
	
	<main id="main" class="main">
	 	<div class="card">
	 		<div class="card-body">
				<div class="row">
					<div class="col-md-3">
						<h3 class="card-title">회원아이디: <%= username %></h3>
					</div>
					<div class="col-md-9">
						<h3 class="card-title">회원권한목록: <%= SecurityUtils.getAuthorities()%></h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<h3 class="card-title">서버시간: <%= request.getAttribute("serverTime") %></h3>
					</div>
					<div class="col-md-9">
						<h3 class="card-title">접속시간: <%= request.getAttribute("loginTime") %></h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 text-end">
						<button class="btn btn-primary" onclick="logout();">로그아웃</button>
					</div>
				</div>
			</div>
	 	</div>
	</main>
	
	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>
	
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>