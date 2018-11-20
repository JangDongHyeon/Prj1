<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
	function pageList(num) {
		location.href = "${path}/shoping/catagory?kind=${map.kindVO.get(0).kind}&page="
				+ num;

	}
</script>



<article>
	<h2 style="color: black;">${map.title}</h2>
	<div style="width: 100%; overflow: hidden; margin-bottom: 25px;">
		<c:forEach items="${map.kindVO}" var="vo">
			<div id="item">
				<a href="${path}/shoping/readItem?pseq=${vo.pseq}"> <img
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
				<a href="javascript:pageList('${map.pageMaker.startBlock-1}')">이전</a>
			</c:if>
			<c:forEach begin="${map.pageMaker.startBlock}"
				end="${map.pageMaker.endBlock}" var="num">
				<c:choose>
					<c:when test="${map.pageMaker.page==num}">
						<span style="color: red;"> ${num}</span>
					</c:when>
					<c:otherwise>
						<a href="javascript:pageList('${num}')">${num}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${map.pageMaker.nextPage}">
				<a href="javascript:pageList('${map.pageMaker.endBlock+1}')">다음</a>
			</c:if>

		</div>
	</div>

</article>

<%@include file="../include/footer.jsp"%>