<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<div class="clear"></div>
<div id="main_img">
	<img src="/resources/images/main_img.jpg" >
</div>

<div class="clear" ></div>
 
<div id="front">
<!-- 신상품 메뉴 -->
	<h2>신상품</h2>
	<div id="bestProduct">
	  <c:forEach var="newI" items="${newItem}">
		<div id="item">
			<a href="${path}/shoping/readItem?pseq=${newI.pseq}">
				<img src="/resources/product_images/${newI.image}" />
				<h3>${newI.name}</h3>
				<p>${newI.price2}</p>
			</a>
		</div>
	  </c:forEach>
	</div>
<div class="clear"></div>
<!-- 베스트 메뉴 -->
    <h2>베스트 메뉴</h2>
	<div id="bestProduct">
	  <c:forEach var="best" items="${bestItem}">
		<div id="item">
			<a href="${path}/shoping/readItem?pseq=${best.pseq}">
				<img src="/resources/product_images/${best.image}" />
				<h3>${best.name}</h3>
				<p>${best.price2}</p>
			</a>
		</div>
	  </c:forEach>
	</div>
<div class="clear"></div>
	
	
</div>



<%@include file="../include/footer.jsp" %>

