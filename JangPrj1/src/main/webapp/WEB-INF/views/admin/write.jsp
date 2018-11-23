<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<script>
 $(document).ready(function(){
	$("#save").on("click",function(){
		var frm=$("form[name=frm]");
		if(frm.name.value===""){
			alert("상품명을 입력하세요");
			return;
		}
		if(frm.price1.value===""){
			alert("원가를 입력하세요");
			return;
		}
		if(frm.price2.value===""){
			alert("판매가를 입력하세요");
			return;
		}
		if(frm.price3.value===""){
			alert("마진를 입력하세요");
			return;
		}
		if(frm.content.value===""){
			alert("내용을 입력하세요");
			return;
		}
		if(frm.image.value===""){
			alert("이미지를 입력하세요");
			return;
		}
	});
 });
</script>


<article>
	<h1>상품등록</h1>
	<form name="frm" method="post" enctype="multipart/form-data">
		<table id="list">
			<tr>
				<th>상품분류</th>
				<td colspan="5"><select name="kind">
						<c:forEach varStatus="num" items="${kindList}" var="kind">
							<option value="${num}">${kind}</option>
						</c:forEach>
				</select>
			<tr>
				<th>상품명</th>
				<td width="343" colspan="5"><input type="text" name="name" size="47" maxlength="50"></td>
			</tr>
			<tr>
				<th>원가[A]</th>
				<td width="70"><input type="text" name="price1" size="11"></td>
				<th>판매가[B]</th>
				<td width="70"><input type="text" name="price2" size="11"></td>
				<th>[B-A]</th>
				<td width="72"><input type="text" name="price3" size="11"></td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td colspan="5"><textarea name="content" rows="8" cols="70"></textarea>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td width="343" colspan="5"><input type="file" name="image">
				</td>
			</tr>
		</table>
		<input class="btn" id="save" type="button" value="등록">
		<input class="btn" type="button" value="취소">
	</form>
</article>
<%@ include file="include/footer.jsp"%>
</body>
</html>




