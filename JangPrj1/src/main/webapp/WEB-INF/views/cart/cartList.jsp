<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp"%>

<%@include file="../include/sun_mypage.jsp"%>
<script>
$(document).ready(function(){
	$("#del").on("click",function(){
	
		var length=$("input:checkbox[name=cseq]:checked").length;
		var charray=[];
		
		if(length===0){
			alert('삭제할 상품을 선택해주세요');
			return;
		}
	$("input[name='cseq']:checked").each(function(){
		charray.push($(this).val());
		
	});
	$.ajax({
		url:'${path}/cart/cartDel',
		type:'get',
		dataType:'text',
		data:{
			charray:charray
		},
		success:function(data){
			if(data==="success"){
				alert("삭제가 되었습니다");
				window.location.reload();
			}
			
		}
	});
	
	
	});
	
});
	



</script>
<article>
 <h2>Cart List</h2>
 <form name="formm" method="post">
 	<c:choose>
 		<c:when test="${list.size()==0}">
 			<h2>카드에 물건이 없습니다</h2>
 		</c:when>
 	    <c:otherwise>
	      <table>
	           <c:set var = "sum" value = "0" />


		    <tr align="center" style="font-size:15px;">
		   <th>이미지</th><th>상품명</th><th>수량</th><th>가격</th><th>주문일</th><th>삭제</th>
		     </tr>
		     <c:forEach items="${list}" var="vo"> 
		     <tr> 
		     <td> <img width="100px" height="100px"
					src="/resources/product_images/${vo.image}">
			
		     <td><a href="${path}/shoping/readItem?pseq=${vo.pseq}"><h3>${vo.pname}</h3></a></td>
		   	 <td>${vo.quantity}</td>
		   	 <td><fmt:formatNumber value="${vo.price*vo.quantity}" var="price"
					pattern="###,###,###" />${price}원</td>
			 <c:set var="sum" value="${sum+vo.price*vo.quantity}"/>		
		   	 <td><fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd"/> </td>
		   	 <td><input type="checkbox" name="cseq" value="${vo.cseq}"></td>
		    </tr>
		   </c:forEach>		
	
		
		    
	     </table>
	   </c:otherwise>
 </c:choose>
<h2 style="color: black;" align="center">총가격: ${sum}</h2>
 <input type="button" id="buy" value="구매하기" class="submit">
 <input type="button" id="del" value="삭제" class="submit"> 

 </form>
</article>




<%@include file="../include/footer.jsp"%>