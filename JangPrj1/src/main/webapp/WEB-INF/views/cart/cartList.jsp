<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sun_mypage.jsp"%>
<script>
	$(document).ready(function() {
		$("#del").on("click", function() {

			var length = $("input:checkbox[name=cseq]:checked").length;
			var charray = [];

			if (length === 0) {
				alert('삭제할 상품을 선택해주세요');
				return;
			}
			$("input[name='cseq']:checked").each(function() {
				charray.push($(this).val());

			});
			$.ajax({
				url : '${path}/cart/cartDel',
				type : 'get',
				dataType : 'text',
				data : {
					charray : charray
				},
				success : function(data) {
					if (data === "success") {
						alert("삭제가 되었습니다");
						window.location.reload();
					}

				},

			});

		});
		$("#buy").on("click", function() {
			var vo = "${list}";
			var listPseq = new Array();
			var listq = new Array();
			var listc = new Array();
			if (vo === '[]') {
				alert("상품이없습니다");
				return;
			}

			<c:forEach items="${list}"  var="item">
			listPseq.push("${item.pseq}");
			listq.push("${item.quantity}");
			listc.push("${item.cseq}");

			</c:forEach>

			$.ajax({
				url : '${path}/order/buyAll',
				type : 'post',
				dataType : 'text',

				data : {
					listPseq : listPseq,
					listq : listq,
					listc : listc
				},

				success : function(data) {
					if ($.trim(data) === "success") {
						alert("구매가 완료되었습니다");

						location.href = '${path}/order/mypage'
					}
				}
			});

		
		});

	});
</script>
<article>
	<h2 style="color: black;">Cart List</h2>
	<form name="formm" method="post">
		<c:set var="sum" value="0" />
		<c:choose>

			<c:when test="${list.size()==0}">
				<h2 style="color: black;">카드에 물건이 없습니다</h2>
			</c:when>
			<c:otherwise>
				<table>



					<tr align="center" style="font-size: 15px;">
						<th>이미지</th>
						<th>상품명</th>
						<th>수량</th>
						<th>가격</th>
						<th>주문일</th>
						<th>삭제</th>
					</tr>
					<c:forEach items="${list}" var="vo">
						<tr>
							<td><img width="100px" height="100px"
								src="/resources/product_images/${vo.image}">
							<td><a href="${path}/shoping/readItem?pseq=${vo.pseq}"><h3>${vo.pname}</h3></a></td>
							<td>${vo.quantity}</td>
							<td><fmt:formatNumber value="${vo.price*vo.quantity}"
									var="price" pattern="###,###,###" />${price}원</td>
							<c:set var="sum" value="${sum+vo.price*vo.quantity}" />


							<td><fmt:formatDate value="${vo.indate}"
									pattern="yyyy-MM-dd" /></td>
							<td><input type="checkbox" name="cseq" value="${vo.cseq}"></td>
						</tr>
					</c:forEach>



				</table>
			</c:otherwise>
		</c:choose>

		<h2 style="color: black;">총가격: ${sum}</h2>


		<input type="button" id="buy" value="구매하기" class="submit"> <input
			type="button" id="del" value="삭제" class="submit">

	</form>
</article>




<%@include file="../include/footer.jsp"%>