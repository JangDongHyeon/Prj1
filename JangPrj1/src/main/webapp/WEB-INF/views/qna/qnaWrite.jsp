<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp"%>

<%@include file="../include/sun_mypage.jsp"%>
<script>
$(document).ready(function(){
	$("form[name=formm]").submit(function() {
		
		var vo=$(this).find("input[name=bGroup]").val();
	
		if(vo==='0')$(this).attr("action","${path}/qna/qnaNewInsert");
		
		
		frmObj.attr("method","post");
		frmObj.submit(); 
	
	});
});

</script>

 <article>
<h2> 1:1 고객 게시판 </h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
<form name="formm" method="post" action="${path}/qna/qnaInsert">
<input type="hidden" name="bGroup" value="${vo.bGroup}" />
<input type="hidden" name="bStep" value="${vo.bStep}" />
<input type="hidden" name="bIndent" value="${vo.bIndent}" />
<fieldset>
<legend></legend>
<label>Title</label>
<input type="text" name="subject" size="77" ><br>

<label>Content</label><br><br>
<textarea rows="8" cols="65" name="content"></textarea><br>
</fieldset>
<div class="clear"></div>
<div id="buttons" style="float:right">
<input type="submit" value="글쓰기" class="submit">
<input type="reset" value="취소" class="cancel">
</div>
</form>
</article>


<%@include file="../include/footer.jsp"%>