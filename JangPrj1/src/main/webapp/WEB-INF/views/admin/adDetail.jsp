<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(document).ready(function(){
	$("#modify").on("click",function(){
		var frm=$("form[name=frm]");
		$(frm).attr("action","/admin/modify");
		$(frm).attr("method","get");
		$(frm).submit();
		
	});
 	 $("#listA").on("click",function(){
	var frm=$("form[name=frm]");
	
		$(frm).attr("action","/admin/productList");
		$(frm).attr("method","get");
		$(frm).submit();
		
		 
	}); 
 	 $("#delete").on("click",function(){
 		var frm=$("form[name=frm]");
 		
		$(frm).attr("action","/admin/delete");
		$(frm).attr("method","get");		
		$(frm).submit();
 	 });
	
});
	

</script>
</head>
<body>
	<article>
		<h1>상품 상세 보기</h1>
		<form name="frm" method="post">
			<input type="hidden" name="keyword" value="${searchIn.keyword}">
			<input type="hidden" name="search" value="${searchIn.search}">
			<input type="hidden" name="page" value="${searchIn.page}">
			 <input	type="hidden" name="numPage" value="${searchIn.numPage}">			 
			<input type="hidden" name="image" value="${vo.image}">			
			<input type="hidden" name="pseq" value="${vo.pseq}">
			<table id="list">
				<tr>
					<th>상품분류</th>
					<td colspan="5">${vo.title}</td>
				</tr>
				<tr>
					<th align="center">상품 명</th>
					<td colspan="5">${vo.name}</td>
				</tr>
				<tr>
					<th>원가 [A]</th>
					<td width="60">${vo.price1}</td>
					<th>판매가 [B]</th>
					<td width="60">${vo.price2}</td>
					<th>마진</th>
					<td>${vo.price3}</td>
				</tr>
				<tr>
					<th>상세설명</th>
					<td colspan="5">${vo.content}</td>
				</tr>
				<tr>
					<th>상품이미지</th>
					<td colspan="5" align="center"><img
						src="/resources/product_images/${vo.image}" width="200pt"></td>
				</tr>
			</table>
			<input class="btn" id="modify" type="button" value="수정"> 
			<input	class="btn" id="listA" type="button" value="목록">
			<input	class="btn" id="delete" type="button" value="삭제">
			
		</form>
	
	</article>



	<%@ include file="include/footer.jsp"%>
</body>
</html>