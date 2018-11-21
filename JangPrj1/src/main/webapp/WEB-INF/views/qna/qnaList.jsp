<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp"%>

<%@include file="../include/sun_mypage.jsp"%>
<article>
 <h2>1:1 고객 게시판</h2>
 <h3>1111</h3>
 <form name="formm" method="post">
 	<table class="cartList">
 	 <tr>
 	  <th>번호</th> <th>제목</th> <th>등록일</th> <th>답변 여부</th>
 	 </tr>	
 	 <c:forEach items="${list}" var="vo">
 	  <tr>
 	   <td>${vo.qseq}</td>
 	   <td><a href="${path}/qna/qnaDetail?qseq=${vo.qseq}">${vo.subject}</a></td>
 	   <td><fmt:formatDate value="${qnaVO.indate}" pattern="yyyy-MM-dd"/></td>
 	   <c:choose>
		<c:when test="${VO.rep==1}"> no </c:when>
		<c:when test="${VO.rep==2}"> yes </c:when>
		</c:choose>
 	  </tr>
 	 </c:forEach>
 	</table>
 <div class="clear"></div>
<input type="submit" value="1:1 질문하기" class="submit">
 </form>
</article>



<%@include file="../include/footer.jsp"%>