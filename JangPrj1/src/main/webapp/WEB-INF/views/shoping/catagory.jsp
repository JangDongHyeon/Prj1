<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>

<div align="center">
<form style="margin: 0;" action="${path}/shoping/catagory">
<select name="search" style="width: 100px; height:30px;">
	<option value="al" <c:out value="${searchIn.search eq 'al'?'selected':'' }"/>>ALL</option>
	<option value="he" <c:out value="${searchIn.search eq 'he'?'selected':''}"/>>Heels</option>
	<option value="bt" <c:out value="${searchIn.search eq 'bt'?'selected':''}"/>>Boots</option>
	<option value="sd" <c:out value="${searchIn.search eq 'sd'?'selected':''}"/>>Sandals</option>
	<option value="sk" <c:out value="${searchIn.search eq 'sk'?'selected':''}"/>>Sneakers</option>
	<option value="sl" <c:out value="${searchIn.search eq 'sl'?'selected':''}"/>>Sale</option>

</select>
<input style="height:23px;" type="text" name="keyword" value="${searchIn.keyword}">
<input class="submit" type="submit" value="조회">
</form>
</div>

<article>
	<h2 style="color: black;">${map.title}</h2>
	<div style="width: 100%; overflow: hidden; margin-bottom: 25px;">
		<c:forEach items="${map.kindVO}" var="vo">
			<div id="item">
				<a href="${path}/shoping/readItem${map.pageMaker.makeSearch(map.pageMaker.critia.page)}&pseq=${vo.pseq}"> <img
					src="/resources/product_images/${vo.image}">
				</a>
				<h3>${vo.name}</h3>
				<p>${vo.price2}</p>
				<p>${vo.title}</p>
			</div>

		</c:forEach>
	</div>
	<div style="width: 100%;">
		<div align="center">
			<c:if test="${map.pageMaker.perPage}">
				<a href="${path}/shoping/catagory${map.pageMaker.makeSearch(map.pageMaker.startBlock-1)}">이전</a>
			</c:if>
			<c:forEach begin="${map.pageMaker.startBlock}"
				end="${map.pageMaker.endBlock}" var="num">
				<c:choose>
					<c:when test="${map.pageMaker.critia.page==num}">
						<span style="color: red;"> ${num}</span>
					</c:when>
					<c:otherwise>
						<a href="${path}/shoping/catagory${map.pageMaker.makeSearch(num)}">${num}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${map.pageMaker.nextPage&&map.pageMaker.endBlock>0}">
				<a href="${path}/shoping/catagory${map.pageMaker.makeSearch(map.pageMaker.endBlock+1)}">다음</a>
			</c:if>

		</div>
	</div>

</article>

<%@include file="../include/footer.jsp"%>