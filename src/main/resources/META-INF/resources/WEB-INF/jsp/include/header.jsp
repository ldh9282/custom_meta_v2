<%@page import="com.custom.met.cmmn.security.utils.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<header id="header" class="header fixed-top d-flex align-items-center">
	  <div class="d-flex align-items-center justify-content-between">
	    <a href="${pageContext.request.contextPath}/" class="logo d-flex align-items-center">
	      <span class="d-none d-lg-block">메타관리시스템</span>
	    </a>
	    <i class="bi bi-list toggle-sidebar-btn"></i>
	  </div><!-- End Logo -->

	  <nav class="header-nav ms-auto">
	    <ul class="d-flex align-items-center">

	      <li class="nav-item dropdown pe-3">

	        <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
	          	<img src="${pageContext.request.contextPath}/resources/cmmn/img/user-profile/user-profile-a.png" width="30" height="30" alt="Profile" class="rounded-circle">
	          	<span id="name" class="d-none d-md-block dropdown-toggle ps-2"><%= SecurityUtils.getUsername() %></span>
	        </a><!-- End Profile Image Icon -->

	        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
	          <li class="dropdown-header">

	          </li>
			  <li>
	          	<hr class="dropdown-divider">
	          </li>
	          <li>
	            <a class="dropdown-item d-flex align-items-center" href="javascript:logout();">
	              <i class="bi bi-box-arrow-right"></i>
	              <span>로그아웃</span>
	            </a>
	          </li>


	        </ul><!-- End Profile Dropdown Items -->
	      </li><!-- End Profile Nav -->

	    </ul>
	  </nav><!-- End Icons Navigation -->

	</header>