<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="/resources/css/shopping.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrap">

 <header>

   <div id="logo">
	 <a href="${path}/shoping/main.do">
	  <img src="/resources/images/run_logo.png" width="180px" height="100px">
	 </a>
   </div>
   <nav id="catagory_menu">
   <ul>
   	 <c:choose>
  		<c:when test="${sessionScope.userId==null}">
  			<li><a href="${path}/member/login">LOGIN</a></li>
  			<li><a href="${path}/member/join">JOIN</a></li>
  		</c:when> 	 
   	 <c:otherwise>
   	 	<li><p>${sessionScope.userName}님 반갑습니다</p></li>
   	 	<li><a href="${path}/member/logout">LOGOUT</a></li>
   	 </c:otherwise>
   	
   	 
   	 </c:choose>
   	  	<li><a href="${path}/shoping/cart.do">CART</a></li>
   	  	<li><a href="${path}/member/mypage.do">MY PAGE</a>
   	  	<li><a href="#">QNA</a>
   	  	<li><a href="${path}/board/list.do">자유게시판</a>
   	</ul>
   </nav>
   
   <nav id="top_menu">
   	<ul>
   		<li><a href="${path}/shoping/catagory?kind=1">Heels</a></li>
   		<li><a href="${path}/shoping/catagory?kind=2">Boots</a></li>
   		<li><a href="${path}/shoping/catagory?kind=3">Sandals</a></li>
   		<li><a href="${path}/shoping/catagory?kind=4">Sneakers</a></li>
   		<li><a href="${path}/shoping/catagory?kind=5">On Sale</a></li>		
   	</ul>
   </nav>
	<div class="clear"></div>
<hr>
</header>
</body>
</html>