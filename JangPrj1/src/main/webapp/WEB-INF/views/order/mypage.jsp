<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sun_mypage.jsp"%>
<script>
 $(document).ready(function(){
	 $("#show").on("click",function(){
		 var frmObj=$("form[name=formm]");
			frmObj.attr("action","${path}/shoping/catagory");
			frmObj.attr("method","get");
			frmObj.submit(); 
	 });
	 
	/*  $("#re").on("click",function(e){
		 console.log(e);
		  $.ajax({
			type:"get",
			url:'${path}/order/del/'+e,
			dataType:'text',
			success:function(data){
				if(data==="success"){
					alert("취소되었습니다");
					window.location.reload();
					 console.log(e);
				}
			}
		 });

	 }); */
	 
	 
 });
 function del(oseq){
	 $.ajax({
			type:"get",
			url:'${path}/order/del/'+oseq,
			dataType:'text',
			success:function(data){
				if(data==="success"){
					alert("취소되었습니다");
					window.location.reload();
					
				}
			}
		 });
 }
</script>
	<article>
 <c:set var = "sum" value = "0" />
	<h2 style="color: black;">주문 상세 조회</h2>
	 <form method="post" name="formm">
	 	<input type="hidden" name="keyword" value="${searchIn.keyword}">
			<input type="hidden" name="search" value="${searchIn.search}">
			<input type="hidden" name="page" value="${searchIn.page}">
			 <input	type="hidden" name="numPage" value="${searchIn.numPage}">
	 		
	 	<table class="cartList">

	 		<tr align="center" style="font-size:15px;">
	 		<th>이미지</th><th>상품명</th><th>상품번호</th><th>상품갯수</th><th>이름</th><th>가격</th>
	 		 <th>날짜</th><th>진행상황</th><th>취소</th>
	 		</tr>
	 		<c:forEach items="${list}" var="vo">
	 		 <tr>
	 		   <td>
	 		   <img width="100px" height="100px"
					src="/resources/product_images/${vo.image}">					
	 		   </td>
	 		   <td><a href="${path}/shoping/readItem?pseq=${vo.pseq}"><h3>${vo.pname}</h3></a></td>
	 		   <td>${vo.pseq}</td>
	 		   <td>${vo.quantity}</td>
	 		   <td>${vo.mname}</td>
	 		   <td><fmt:formatNumber value="${vo.price*vo.quantity}" var="price"
					pattern="###,###,###" />${price}원</td>
					<c:set var="sum" value="${sum+vo.price*vo.quantity}"/>		
					 <td><fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd"/> </td>
				<c:choose>
					<c:when test="${vo.result=='1'}">
						<td>처리중</td>
					</c:when>
					<c:otherwise>
						<td>처리완료</td>
					</c:otherwise>
				</c:choose>
			   <td><button id="re" onclick="del('${vo.oseq}')">취소</button></td>
	 		 </tr>
	 		 
	 		</c:forEach>
	 	
 

</table>
<div class="clear"></div>
<div>
<h3 style="font-size: 15px; ">총 액   <fmt:formatNumber value="${sum}" pattern="###,###,###" var="sumding"
/>${sumding}원</h3>
 <h3>주문 처리가 완료되었습니다.</h3>
</div>
<div class="clear"></div>

<div id="buttons" style="float: right">

<input type="button" id="show" value="쇼핑 계속하기" class="cancel">
	</div>
	 </form>
	</article>
<%@include file="../include/footer.jsp"%>
