<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script>
$(document).ready(function(){
	$("#ser").on("click",function(){
		var id=$("input[name='keyword']").val();
		location.href='/admin/orderList?keyword='+id;
		
	});
	
	$("#buy").on("click",function(){
		var length=$("input:checkbox[name='result']:checked").length;
		var arrayList=[];
		
		if(length===0){
			alert("입금확인체크해주세요");
			return false;
		}
		
		$("input[name='result']:checked").each(function(){
			arrayList.push($(this).val());
			
		});
		$.ajax({
			type:'post',
			url:'/admin/orderList',
			dataType:'text',
			data:{
				arrayList:arrayList
			},
			success:function(data){
				if(data==="success"){
					alert("변경 되었습니다");
					
				}
			}
		});
		
	});
	
});

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<article>
		<h1>주문리스트</h1>
		<form name="frm" method="post">
			<table style="float: right;">
				<tr>
					<td>주문자 아이디<input type="text" name="keyword"> <input
						class="btn" id="ser" type="button" value="검색">
					</td>
				</tr>
			</table>
			<br>
			<table id="orderList">
				<tr>
					<th>주문번호(처리여부)</th>
					<th>아이디</th>
					<th>주문자</th>
					<th>상품명</th>
					<th>수량</th>
					<th>가격</th>					
					<th>전화</th>
					<th>주문일</th>
				</tr>
				<c:forEach items="${orderList}" var="orderVO">
					<tr>
						<td><c:choose>
								<c:when test='${orderVO.result=="1"}'>
									<span style="font-weight: bold; color: blue">${orderVO.odseq}</span>        (<input
										type="checkbox" name="result" value="${orderVO.odseq}"> 미처리)        </c:when>
								<c:otherwise>
									<span style="font-weight: bold; color: red">${orderVO.odseq}</span>          (<input
										type="checkbox" checked="checked" disabled="disabled">처리완료)        </c:otherwise>
							</c:choose></td>
						<td>${orderVO.id}</td>
						<td>${orderVO.mname}</td>
						<td>${orderVO.pname}</td>
						<td>${orderVO.quantity}</td>
						<td><fmt:formatNumber value="${orderVO.price*orderVO.quantity}" /></td>						
						<td>${orderVO.phone}</td>
						<td><fmt:formatDate value="${orderVO.indate}" /></td>
					</tr>
				</c:forEach>
			</table>
			<input type="button" class="btn" style="width: 200px" id="buy"
				value="주문처리(입금확인)">
		</form>
	</article>
	<%@ include file="include/footer.jsp"%>
</body>
</html>