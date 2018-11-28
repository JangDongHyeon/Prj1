<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function go_view(qseq) {

		location.href='/admin/qnaDetail?qseq='+qseq;
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<article>
		<h1>qna 리스트</h1>
		<form name="frm" method="post">
			<input type="hidden" name="qseq">
			<table id="orderList">
				<tr>
					<th>번호(답변여부)</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach items="${qnaList}" var="qnaVO">
					<tr>
						<td>${qnaVO.qseq}<c:choose>
								<c:when test='${qnaVO.rep=="1"}'>(미처리)</c:when>
								<c:otherwise>(답변처리완료)</c:otherwise>
							</c:choose>
						</td>
						<td><a href="#" onClick="javascript:go_view('${qnaVO.qseq}')">
								${qnaVO.subject} </a></td>
						<td>${qnaVO.id}</td>
						<td><fmt:formatDate value="${qnaVO.indate}" /></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</article>
<!-- 	<div id="myModal" class="modal">

		<div class="modal-content">
			<form method="post" name="formm">
				<h2 style="color: black;">QNA 게시판</h2>
				제목:<input type="text" name="subject"> <br> 등록일:
				
				<br> 내용:<input type="text" name="con">
				<hr>
				<textarea name="content" rows="3" cols="50"></textarea>
				<input type="button" class="btn" value="저장">
			</form>
		</div>
	</div> -->
	<%@ include file="include/footer.jsp"%>

</body>
</html>