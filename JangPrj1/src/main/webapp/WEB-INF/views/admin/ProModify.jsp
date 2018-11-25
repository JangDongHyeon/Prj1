<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script>
$(document).ready(function(){
	$("#modify").on("click",function(){
		regNumber = /^[0-9]*$/;
		var frm=$("input[name=frm]");
		if($(this).find("input[name='name']").val()===""){
			alert("상품명을 입력하세요");
			return false;
		}
		if($(this).find("input[name='price1']").val()===""||regNumber.test($(this).find("input[name='price1']").val())){
			alert("원가를 입력하세요");
			return false;
		}
		if($(this).find("input[name='price2']").val()===""||regNumber.test($(this).find("input[name='price1']").val())){
			alert("판매가를 입력하세요");
			return false;
		}
		if($(this).find("input[name='price3']").val()===""||regNumber.test($(this).find("input[name='price1']").val())){
			alert("마진를 입력하세요");
			return false;
		}
		if($(this).find("input[name='content']").val()===""){
			alert("내용을 입력하세요");
			return false;
		}
		if(!checkExtension(files[0].name,files[0].size)){
			return false;
		}
		 frm.action='/admin/modify';
		frm.submit();
		
	});
});

var regex=new RegExp("(.*?)\.(gif|jpg|jpeg)$");
var maxSize=5242880;
function checkExtension(fileName,fileSize){

	 if(fileSize>=maxSize){
		 alert("파일  사이즈 초과");
	 	return false;
	 }
	    if (!regex.test(fileName)) {
	        alert("이미지 형식의 파일을 선택하십시오");
	        return false;
	    } 
	    return true;
} 

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<article>
		<h1>상품수정</h1>
		<form name="frm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="keyword" value="${searchIn.keyword}">
			<input type="hidden" name="search" value="${searchIn.search}">
			<input type="hidden" name="page" value="${searchIn.page}">
			 <input	type="hidden" name="numPage" value="${searchIn.numPage}">			
			<input type="hidden" name="pseq" value="${productVO.pseq}"> 
			<input type="hidden" name="image" value="${productVO.image}">
			<table id="list">
				<tr>
					<th>상품분류</th>
					<td colspan="5"><select name="kind">
							<c:forEach items="${kindList}" var="kind" varStatus="num">
								<c:choose>
									<c:when test="${productVO.kind==status.count}">
										<option value="${num.count}" selected="selected">${kind}</option>
									</c:when>
									<c:otherwise>
										<option value="${num.count}">${kind}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>상품명</th>
					<td width="343" colspan="5"><input type="text" name="name"
						size="47" maxlength="50" value="${productVO.name}"></td>
				</tr>
				<tr>
					<th>원가[A]</th>
					<td width="70"><input type="text" name="price1" size="11"
						value="${productVO.price1}"></td>
					<th>판매가[B]</th>
					<td width="70"><input type="text" name="price2" size="11"
						value="${productVO.price2}"></td>
					<th>마진</th>
					<td width="72"><input type="text" name="price3" size="11" value="${productVO.price3}"></td>
				</tr>
				<tr>
					<th>베스트상품</th>
					<td><c:choose>
							<c:when test='${productVO.bestyn=="y"}'>
								<input type="checkbox" name="bestyn" value="y" checked="checked">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="bestyn" value="n">
							</c:otherwise>
						</c:choose></td>
					<th>사용유무</th>
					<td><c:choose>
							<c:when test='${productVO.useyn=="y"}'>
								<input type="checkbox" name="useyn" value="y" checked="checked">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="useyn" value="n">
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th>상세설명</th>
					<td colspan="5"><textarea name="content" rows="8" cols="70"> ${productVO.content} </textarea>
					</td>
				</tr>
				<tr>
					<th>상품이미지</th>
					<td colspan="5"><img src="/resources/product_images/${productVO.image}"
						width="200pt"> <br> <input type="file" name="upliadFile">
					</td>
				</tr>
			</table>
			<input class="btn" id="modify" type="button" value="수정"> 
			<input 	class="btn" type="button" value="취소">
		</form>
	</article>


	<%@ include file="include/footer.jsp"%>
</body>
</html>