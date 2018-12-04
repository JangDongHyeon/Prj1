<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>

	var msg='${msg}';
	if(msg!=='')
		alert(msg);

$(document).ready(function(){
	$("input[name=btn_write]").on("click",function(){
		var frm=$('form[name=frm]');
		$(frm).attr("action","${path}/board/boardInsert").submit();
	});
	
	$("input[name=btn_search]").on("click",function(){
		var frm=$('form[name=frm]');
		$(frm).attr("action","${path}/board/boardSelect").submit();
	});
	
});
</script>

<article>
	<h1 align="center" style="color:black;">글 목록</h1>
	<form name="frm">
			<input type="hidden" name="page" value="${searchVO.page}">
			 <input	type="hidden" name="numPage" value="${searchVO.numPage}">
	  <table>
  <tr>
  <td width="642">
      <select name="search">
       <option value="b_al" <c:out value="${searchVO.search eq 'b_al'?'selected':''}"/>>전체</option>
       <option  value="b_ti" <c:out value="${searchVO.search eq 'b_ti'?'selected':''}"/>>제목</option>
       <option  value="b_con" <c:out value="${searchVO.search eq 'b_con'?'selected':''}"/>>내용</option>
       <option  value="b_i" <c:out value="${searchVO.search eq 'b_i'?'selected':''}"/>>작성자</option>
      </select> 
     <input type="text" name="keyword" value="${searchVO.keyword}">
     <input class="btn" type="button" name="btn_search" value="검색">
     <input class="btn" type="button" name="btn_write" value="글등록">
  </td>
  </tr>
</table>
	</form>
		<table id="boardList">
			<tr>
				<th>번호</th>
				<th width="230px">제목</th>
				<th>아이디</th>
				<th>조회수</th>
				<th>날짜</th>
				<th>추천</th>
			</tr>
			<c:forEach items="${map.boardList}" var="vo">
			  <tr>
			   <td>${vo.bno}</td>
			   <td width="230px;"><a href="${path}/board/boardDetail${map.pageMaker.makeSearch(map.pageMaker.critia.page)}&bno=${vo.bno}">${vo.subject}</a>
			   (${vo.replycnt})
			   </td>
			   <td>${vo.bid}</td>
			   <td>${vo.cnt}</td>
			   <td><fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd"/>
			   <td>${vo.love}</td>
			  </tr>
			</c:forEach>	

 <tr><td colspan="6" style="text-align: center; font-size: 10px;">
			<c:if test="${map.pageMaker.perPage}">
				<a href="${path}/board/boardSelect${map.pageMaker.makeSearch(map.pageMaker.startBlock-1)}">이전</a>
			</c:if>
			<c:forEach begin="${map.pageMaker.startBlock}"
				end="${map.pageMaker.endBlock}" var="num">
				<c:choose>
					<c:when test="${map.pageMaker.critia.page==num}">
						<span style="color: red;"> ${num}</span>
					</c:when>
					<c:otherwise>
						<a href="${path}/board/boardSelect${map.pageMaker.makeSearch(num)}">${num}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${map.pageMaker.nextPage&&map.pageMaker.endBlock>0}">
				<a href="${path}/board/boardSelect${map.pageMaker.makeSearch(map.pageMaker.endBlock+1)}">다음</a>
			</c:if>
</td></tr>
	</table>
</article>

<%@include file="../include/footer.jsp"%>