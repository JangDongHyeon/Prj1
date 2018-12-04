<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
$(document).ready(function(){
	var frm=$("form[name=frmm]");
	
	$("input[type='submit']").on("click",function(e){
		e.preventDefault();
		
		
	});
});
var regex=new RegExp("(.*?)\.(zip|exe|sh|alz)$");
var maxSize=5242880;
function checkExtension(fileName,fileSize){

	 if(fileSize>=maxSize){
		 alert("파일  사이즈 초과");
	 	return false;
	 }
	    if (regex.test(fileName)) {
	        alert("해당 종류의 팔인은업로드 할수없습니다");
	        return false;
	    } 
	    return true;
} 
</script>

<article>
	<h2 style="color: black;">글등록</h2>
	<form name="frmm" method="post" enctype="multipart/form-data">
	 <div style="height: 50px; width: 100%;">
 <span>제목:</span>
 <input type="text" name="subject" size="100px;">
</div><br>
<div style="width: 100%;">
 <span>내용:</span>

<textarea rows="50" cols="100" name="content">
</textarea>
</div>
<input  type="submit" class="submit" value="저장">
<input type="reset" class="submit" value="취소">
<input type="button" class="submit" value="리스트">
<br>
<br>
<div>
<span>파일업로드</span><br>
<input type="file" name="uploadFile" multiple="multiple">
</div>
<div class="uploadResult">
 <ul>
 
 </ul>
</div>
</form>
	</article>



<%@include file="../include/footer.jsp"%>