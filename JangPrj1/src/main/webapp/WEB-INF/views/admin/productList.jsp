<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<script>
$(document).ready(function(){
	$("input[name=btn_write]").on("click",function(){
		location.href='/admin/adminInsert';
	});
	$("input[name='btn_search']").on("click",function(){
		var keyword=$("input[name='keyword']").val();
		location.href='/admin/productList?keyword='+keyword;
	});
	$("input[name='btn_total']").on("click",function(){
		
		location.href='/admin/productList';
	});
});

</script>

<article>
<h1>상품리스트</h1>	
<form name="frm" method="post">
<table>
  <tr>
  <td width="642">
      상품명 
     <input type="text" name="keyword">
     <input class="btn" type="button" name="btn_search" value="검색">
     <input class="btn" type="button" name="btn_total" value="전체보기 ">
     <input class="btn" type="button" name="btn_write" value="상품등록">
  </td>
  </tr>
</table>
<table id="productList">
    <tr>
        <th>번호</th><th>상품명</th><th>원가</th><th>판매가</th><th>등록일</th><th>사용유무</th>
    </tr>
    <c:choose>
    <c:when test="${list.size()<=0}">
    <tr>
      <td width="100%" colspan="7" align="center" height="23">
        등록된 상품이 없습니다.
      </td>      
    </tr>
    </c:when>
	<c:otherwise>
	<c:forEach items="${list}" var="vo">
    <tr>
      <td height="23" align="center" >${vo.pseq}</td>
      <td style="text-align: left; padding-left: 50px; padding-right: 0px;">   
        <a href="/admin/adDetail${pageMaker.makeSearch(pageMaker.critia.page)}&pseq=${vo.pseq}">
    	 ${vo.name}     
   		</a>
   	  </td>
      <td><fmt:formatNumber value="${vo.price1}"/></td>
      <td><fmt:formatNumber value="${vo.price2}"/></td>
      <td><fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd"/></td>
      <td>
      	<c:choose>
   	 		<c:when test='${vo.useyn=="1"}'>미사용</c:when>
   	 		<c:otherwise>사용</c:otherwise>   	 		
   	 	</c:choose>	 
   	  </td> 
    </tr>
    </c:forEach>
    <tr><td colspan="6" style="text-align: center; font-size: 15px;">
  
			<c:if test="${pageMaker.perPage}">
				<a href="/admin/productList${pageMaker.makeSearch(pageMaker.startBlock-1)}">이전</a>
			</c:if>
			<c:forEach begin="${pageMaker.startBlock}"
				end="${pageMaker.endBlock}" var="num">
				<c:choose>
					<c:when test="${pageMaker.critia.page==num}">
						<span style="color: red;"> ${num}</span>
					</c:when>
					<c:otherwise>
						<a href="/admin/productList${pageMaker.makeSearch(num)}">${num}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageMaker.nextPage&&pageMaker.endBlock>0}">
				<a href="/admin/productList${pageMaker.makeSearch(pageMaker.endBlock+1)}">다음</a>
			</c:if>

    </td></tr>
	</c:otherwise>	
</c:choose>  
</table>
</form> 
</article>
<%@ include file="include/footer.jsp"%>
</body>
</html>