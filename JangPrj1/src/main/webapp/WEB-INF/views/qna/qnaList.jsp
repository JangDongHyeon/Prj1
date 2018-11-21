<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp"%>
<script>
$(document).ready(function(){
	$("#sal").on("click",function(){
		console.log("aaa")
		location.href='${path}/qna/qnaInsert';
	});
		
	
});

</script>
<%@include file="../include/sun_mypage.jsp"%>
<article>
 <h2>1:1 고객 게시판</h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
 <c:choose>
 <c:when test="${!empty list}">
 <form name="formm" method="get" action="${path}/qna/qnaInsert">
 	<table class="cartList" width="500px">
 	 <tr align="center">
 	  <th>번호</th> <th>제목</th> <th>등록일</th> <th>답변 여부</th>
 	 </tr>	
 	 <c:forEach items="${list}" var="vo">
 	 <tr>
 	  <td>
 	 <c:if test="${vo.bIndent>0}">
 	  <c:forEach begin="0" end="${vo.bIndent}">
 	    &nbsp; &nbsp;&nbsp;
 	  </c:forEach>
 	   RE:
 	  </c:if>
 	
 	${vo.qseq}</td>
 	   <td><a href="${path}/qna/qnaDetail?qseq=${vo.qseq}">${vo.subject}</a></td>
 	   <td><fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd" />
 	   </td>
 	   <td>
 	   <c:choose>
		<c:when test="${vo.rep==1}"> no </c:when>
		<c:when test="${vo.rep==2}"> yes </c:when>
		</c:choose>
		</td>
 	  </tr>
 	 </c:forEach>
 	</table>
 <div class="clear"></div>
 </form>
 </c:when>
 <c:otherwise>
 	<h1 style="color:red;">등록된 QNA가 없습니다</h1>
 </c:otherwise>
 </c:choose>
  <button type="button" id="sal"  class="submit">1:1 질문하기</button>
</article>



<%@include file="../include/footer.jsp"%>