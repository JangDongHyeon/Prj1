<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$("#ser").on("click",function(){
		var keyword=$("input[name='keyowd']").val();
	  	location.href='/admin/memberList?keyword='+keyword;
	});
});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<article>
		<h1>회원리스트</h1>
		<form name="frm" method="post">
			<table style="float: right;">
				<tr>
					<td>회원 아이디 <input type="text" name="keyowd"> <input
						class="btn" type="button" value="검색" id="ser">
					</td>
				</tr>
			</table>
			<br>
			<table id="orderList">
				<tr>
					<th>아디디(탈퇴여부)</th>
					<th>이름</th>
					<th>이메일</th>
					<th>주소</th>
					<th>전화</th>
					<th>가입일</th>
					<th>탈퇴여부</th>
				</tr>
				<c:forEach items="${memberVO}" var="vo">
					<tr>
						<td>${vo.id}</td>
						<td>${vo.name}</td>
						<td>${vo.email}</td>
						<td>${vo.address}</td>
						<td>${vo.phone}</td>
						<td><fmt:formatDate value="${vo.indate}" /></td>
						<td>
						<c:if test='${vo.useyn!="y"}'>
							<span style="color: red;">탈퇴</span>
						</c:if>
						
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</article>
	<%@ include file="include/footer.jsp"%>
</body>
</html>