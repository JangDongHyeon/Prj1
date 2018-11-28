<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		$("#save").on("click", function() {
			var content = $("textarea[name='content']").val();
			var frm = $("form[name='frm']");
			if (content === "") {
				alert("내용을 입력해주세여");
				return false;
			}
			frm.attr("action", "/admin/adModifyQna").submit();
		});
	});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<article>
		<h1>Q&amp;A 게시판</h1>
		<form name="frm" method="post">
			<input type="hidden" name="qseq" value="${qnaVO.qseq}"> <input
				type="hidden" name="bGroup" value="${qnaVO.bGroup}" /> <input
				type="hidden" name="bStep" value="${qnaVO.bStep}" /> <input
				type="hidden" name="bIndent" value="${qnaVO.bIndent}" />

			<table id="orderList">
				<tr>
					<th width="20%">제목</th>
					<td>${qnaVO.subject}</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td><fmt:formatDate value="${qnaVO.indate}" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${qnaVO.content}</td>
				</tr>
			</table>
			<c:choose>
				<c:when test='${qnaVO.rep=="1"}'>
					<table id="orderList">
						<tr>
							<td colspan="2"><textarea name="reply" rows="3" cols="50"></textarea>
								<input type="button" id="save" class="btn" value="저장"></td>
						</tr>
					</table>
					<br>
				</c:when>
				<c:otherwise>
					<tr>
						<th>댓글</th>
						<td>${qnaVO.reply}</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<input type="button" class="btn" value="목록">
		</form>
	</article>
	<%@ include file="include/footer.jsp"%>
</body>
</html>