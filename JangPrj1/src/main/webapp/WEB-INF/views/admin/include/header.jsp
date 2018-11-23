<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>run Admin</title>
<link rel="stylesheet" href="/resources/css/admin.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#logout').on("click",function(){
		location.href='admin/logout';
	});
});
</script>
<c:choose>
	<c:when test="${sessionScope.adminId!=null}">
		<script type="text/javascript">
			location.href = '/admin/login';
		</script>
	</c:when>
</c:choose>
</head>
<body>
	<div id="wrap">
		<header>
			<div id="logo">
				<a href="#">  <img width="600px" height="150px;" src="/resources/images/run_logo.png"> 
				</a>
			</div>
			<input class="btn" type="button" id="logout" value="logout" style="float: right;">
		</header>
		<div class="clear"></div>