<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
	$(document).ready(function() {
		listAll();
		$("#replyIn").on("click", function() {
			var replyContent = $("#replyContent").val();
			var pseq = $
			{
				readVO.pseq
			}
			;
			$.ajax({
				type : "post",
				url : "${path}/proReply/insert",
				headers : {
					"Content-Type" : "application/json"
				},
				dataType : "text",
				data : JSON.stringify({
					pseq : pseq,
					content : replyContent
				}),
				success : function(data) {
					if (data === "success") {
						alert("댓글 등록 완료");
						listAll();
					}
				}

			});
		});

	});
	function listAll() {
		var str = "";
		$.ajax({
			type : "get",
			url : "${path}/proReply/list/${readVO.pseq}",
			success : function(data) {
				/* 	$("#pro_reply").each(data,function(){
						str=		
					}); */
				for ( var i in data) {
					str += "<li>";
					str += "이름:" + data[i].reply;
					+"<br>"
					str += "내용:" + data[i].content;
					+"<br>"
					str += "날짜:" + data[i].updatedate;
					str += "</li>";

				}
				$("#pro_reply").find("ul").html(str);
			}

		});

	}
</script>


<article>
	<h1>상품 상세</h1>
	<div id="itemdetail">
		<form name="formm" method="post">
			<fieldset>
				<legend> Item detail Info</legend>
				<span style="float: left;"> <img
					src="/resources/product_images/${readVO.image}">
				</span>
				<h2>${readVO.name}</h2>
				<label>가격</label>

				<fmt:formatNumber value="${readVO.price2}" var="price"
					pattern="###,###,###" />
				<p>${price}원
					<legend></legend>
				<p>
					<label>날짜</label>
				<p>
					<fmt:formatDate value="${readVO.indate}" pattern="yyyy-MM-dd" />
				</p>

				<legend></legend>
				<label>수 량:</label>
				<p>
					<select name="amount">
						<c:forEach begin="1" end="10" var="num" step="1">
							<option value="num">${num}</option>
						</c:forEach>
					</select>
				<p>
					<legend></legend>
					<label>좋아요:</label>
				<p></p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="love" class="cancel">좋아요</button>
			</fieldset>
			<div class="clear"></div>
			<button type="button" id="buy" class="submit">구매하기</button>
			<button type="button" id="cart" class="submit">장바구니</button>
			<button type="button" id="list" class="submit">목록</button>
		</form>
	</div>
	<br>
	<hr>
	<h2 align="center" style="color: black;">평가</h2>
	<div id="pro_reply">
		<ul>
		</ul>
		<c:if test="${sessionScope.userid!=null}">
			<label>댓글 내용</label>
			<input type="text" name="replyContent" id="replyContent">
			<button type="button" id="replyIn">댓글 입력</button>
		</c:if>
	</div>
</article>

<%@include file="../include/footer.jsp"%>