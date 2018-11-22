<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
$(document).ready(function(){
	$("#sub").on("click",function(){
		var frmObj=$("form[name=formm]");
		frmObj.attr("action","${path}/qna/qnaModify");
		frmObj.attr("method","post");
		frmObj.submit(); 
	});
	$("#re").on("click",function(){
		var frmObj=$("form[name=formm]");
		frmObj.attr("action","${path}/qna/qnaInsert");
		frmObj.attr("method","get");
		frmObj.submit(); 
	});
	$("#del").on("click",function(){
		var qseq=$(this).parent().parent().find("input[name=qseq]").val();
		var indent=$(this).parent().parent().find("input[name=bIndent]").val();
		var bGroup=$(this).parent().parent().find("input[name=bGroup]").val();
		if(indent==0)location.href='${path}/qna/qnaDelAll?bGroup='+bGroup;
		else location.href='${path}/qna/qnaDel?qseq='+qseq;
	});
});

</script>

<article>
<h2> 1:1 고객 게시판 </h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
<form name="formm" method="post">
<input type="hidden" name="qseq" value="${vo.qseq}" />
<input type="hidden" name="bGroup" value="${vo.bGroup}" />
<input type="hidden" name="bStep" value="${vo.bStep}" />
<input type="hidden" name="bIndent" value="${vo.bIndent}" />
<fieldset>
<legend></legend>
<label>Title</label>
<input type="text" name="subject" size="77" value="${vo.subject}" <c:out value="${sessionScope.userId eq vo.id ? '':'readonly' }"/>><br>
<label>Content</label>
<textarea rows="8" cols="65" name="content"  <c:out value="${sessionScope.userId eq vo.id ?'':'readonly' }"/>>${vo.content}</textarea><br>

</fieldset>
<div class="clear"></div>
<div id="buttons" style="float:right">
<input type="button" id="sub" value="수정" class="submit">
<button type="button" id="del" class="cancel">삭제</button>
<button type="button" id="re" class="cancel">답변달기</button>
</div>
</form>
</article>

<%@include file="../include/footer.jsp"%>