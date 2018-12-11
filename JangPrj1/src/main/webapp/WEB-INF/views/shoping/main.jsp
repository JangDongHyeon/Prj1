<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>


</script>
<%@include file="../include/header.jsp" %>

<div class="clear"></div>


<div align="center">
<form style="margin: 0;" action="${path}/shoping/catagory">
<select name="search" style="width: 100px; height:30px;">
	<option value="al" <c:out value="${map.search eq 'al'?'selected':'' }"/>>ALL</option>
	<option value="he" <c:out value="${map.search eq 'he'?'selected':''}"/>>Heels</option>
	<option value="bt" <c:out value="${map.search eq 'bt'?'selected':''}"/>>Boots</option>
	<option value="sd" <c:out value="${map.search eq 'sd'?'selected':''}"/>>Sandals</option>
	<option value="sk" <c:out value="${map.search eq 'sk'?'selected':''}"/>>Sneakers</option>
	<option value="sl" <c:out value="${map.search eq 'sl'?'selected':''}"/>>Sale</option>

</select>
<input style="height:23px;" type="text" name="keyword" value="${map.keyword}">
<input class="submit" type="submit" value="조회">
</form>

</div>
<div class="clear"></div>
<div id="main_img">
	<img src="/resources/images/main_img30.jpg" height="300px;">
</div>

<div class="clear" ></div>
 
<div id="front">
<!-- 신상품 메뉴 -->
	<h2 style="color: black;">신상품</h2>
	<div id="bestProduct">
	  <c:forEach var="newI" items="${newItem}">
		<div id="item">
			<a style="color:black;" href="${path}/shoping/readItem?pseq=${newI.pseq}">
				<img src="/resources/product_images/${newI.image}" />
				<h3>${newI.name}</h3>
				<p>${newI.price2}</p>
				<p style="color:red; font-size: 20px">♡:${newI.loveCh}</p>
			</a>
		</div>
	  </c:forEach>
	</div>
<div class="clear"></div>
<!-- 베스트 메뉴 -->
    <h2 style="color: black;">베스트 메뉴</h2>
	<div id="bestProduct">
	  <c:forEach var="best" items="${bestItem}">
		<div id="item">
			<a style="color:black;" href="${path}/shoping/readItem?pseq=${best.pseq}">
				<img src="/resources/product_images/${best.image}" />
				<h3>${best.name}</h3>
				<p>${best.price2}</p>
					<p style="color:red; font-size: 20px">♡:${best.loveCh}</p>
			</a>
		</div>
	  </c:forEach>
	</div>
<div class="clear"></div>
	
	
</div>



<%@include file="../include/footer.jsp" %>

