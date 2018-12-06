<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
	<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>
<script>
$(document).ready(function(){
	var frm=$("form[name=frmm]");
	
	$("input[type='submit']").on("click",function(e){
		e.preventDefault();
		
		
	});
	$("input[type='file']").change(function(e){

	    var formData = new FormData();
	    
	    var inputFile = $("input[name='uploadFiles']");
	    
	    var files = inputFile[0].files;
	    
	    for(var i = 0; i < files.length; i++){

	      if(!checkExtension(files[i].name, files[i].size) ){
	        return false;
	      }
	      formData.append("uploadFile", files[i]);
	      
	    }
	$.ajax({
		url:'/BFile/uploadAjaxFile',
		 processData: false, 
	      contentType: false,
	      data:  formData,
	      type: 'POST',
	      dataType:'json',
	        success: function(result){
	          console.log(result); 
			  showUploadResult(result);
	      }
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
function showUploadResult(uploadReArr){
	if(!uploadReArr||uploadReArr.length==0){return;}
	
	var uploadUL=$(".uploadResult ul");
	
	var str="";
	
	$(uploadReArr).each(function(i,obj){
		
		if(obj.image){
			var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
			str+="<li><div>";
			str+="<span>"+obj.fileName+"</span>";
			str+="<button type='button' name='bu'>삭제</button><br>";
			str+="<img src='/BFile/display?fileName="+fileCallPath+"'>";
			str+="</div>";
			str+="</li>";
		}else{
			var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName)
			 var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
			
			str+="<li><div>";
			str+="<span>"+obj.fileName+"</span>";
			str+="<button type='button' name='bu'>삭제</button><br>";
			str+="<img src='/resources/images/attach.png'></a>";
			str+="</div>";
			str+="</li>";
			
			
		}
		
	});
	uploadUL.append(str);
}
$(".uploadResult").on("click","button",function(e){
	
});

});

</script>

<article>
	<h2 style="color: black;">글등록</h2>
	<form name="frmm" method="post">
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
</form>
<br>
<br>
<div>
<span>파일업로드</span><br>
 <input type="file" name='uploadFiles' multiple>
</div>
<div class="uploadResult">
 <ul>
 
 </ul>
</div>

	</article>



<%@include file="../include/footer.jsp"%>