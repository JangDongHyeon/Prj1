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

.uploadResult span {
	font-size: 14px;
	color: white;
}
</style>

<script>
 $(document).ready(function(){
	 getFileList();
	$("#b_modify").on("click",function(){
		var frm=$("form[name='frm']");
		var str="";
		
		$(".uploadResult ul li").each(function(i,obj){
			
			var jobj = $(obj);
		     str += "<input type='hidden' name='boardFileList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
	          str += "<input type='hidden' name='boardFileList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
	          str += "<input type='hidden' name='boardFileList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
	          str += "<input type='hidden' name='boardFileList["+i+"].filetype' value='"+ jobj.data("type")+"'>";
	      
		});
		frm.append(str).submit();

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
	 $(".uploadResult").on("click","button",function(e){
		
		 if(confirm("파일을 삭제하시겠습니까?")){
			 var targetLi=$(this).closest("li");
			 targetLi.remove();
		 }
	 });
	});
function getFileList(){
	
var bno='${boardVO.bno}';
	
	$.getJSON("/board/getBoardFileList",{bno:bno},function(arr){
		var str="";
		$(arr).each(function(i,data){
			
			if(data.filetype=='true'){
		           var fileCallPath =  encodeURIComponent( data.uploadPath+ "/s_"+data.uuid +"_"+data.fileName);
		       
		       
		           str += "<li data-path='"+data.uploadPath+"' data-uuid='"+data.uuid+"' data-filename='"+data.fileName+"' data-type='"+data.filetype+"' ><div>";
		           str+="<span>"+data.fileName+"</span>";
					str+="<button type='button' data-file=\'"+fileCallPath+"\' data-type='image'>X</button><br><br>";
		           str += "<img src='${path}/BFile/display?fileName="+fileCallPath+"'>";
		           str += "</div>";
		           str +"</li>";
		        
			}else{
				var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/"+obj.uuid +"_"+obj.fileName)
				 var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
	             
		           str += "<li data-path='"+data.uploadPath+"' data-uuid='"+data.uuid+"' data-filename='"+data.fileName+"' data-type='"+data.filetype+"'><div>";
		           str += "<span style='font-size:15px; color:black;'> "+ data.fileName+"</span><br/>";
					str+="<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' >X</button><br><br>";
		           str += "<img src='/resources/images/attach.png'></a>";
		           str += "</div>";
		           str +"</li>";
		         }
		});
	      $(".uploadResult ul").html(str);
	});
	
	
	

	
}
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
			var fileCallPath =  encodeURIComponent(obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
			str += "<li data-path='"+obj.uploadPath+"'";
			str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"
			str +" ><div>";
			str+="<span>"+obj.fileName+"</span>";
			str+="<button type='button' data-file=\'"+fileCallPath+"\' data-type='image'>X</button><br><br>";
			str+="<img src='/BFile/display?fileName="+fileCallPath+"'>";
			str+="</div>";
			str+="</li>";
		}else{
			var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/"+obj.uuid +"_"+obj.fileName)
			 var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
			
			str += "<li "
				str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
			str+="<span>"+obj.fileName+"</span> &nbsp;&nbsp;";
			str+="<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' >X</button><br><br>";
			str+="<img src='/resources/images/attach.png'></a>";
			str+="</div>";
			str+="</li>";
			
			
		}
		
	});
	uploadUL.append(str);
}
</script>
<article>
	<h2 style="color: black;">글 수정</h2>
<form name="frm" method="post" action="/board/boardModify">
	<input type="hidden" name="keyword" value="${searchVO.keyword}">
	<input type="hidden" name="search" value="${searchVO.search}">
	<input type="hidden" name="page" value="${searchVO.page}"> <input
		type="hidden" name="numPage" value="${searchVO.numPage}"> <input
		type="hidden" name="bno" value="${boardVO.bno}">

	<div style="height: 50px; width: 100%;">
		<span>제목:</span> <input type="text" name="subject" size="100px;"
			value="${boardVO.subject}">
	</div>
	<br>
	<div style="width: 100%;">
		<span>내용:</span>

		<textarea rows="50" cols="100" name="content">${boardVO.content}
</textarea>
	</div>

	<div>
		<span>파일업로드</span><br> <input type="file" name='uploadFiles'
			multiple>
	</div>
	
	<div class="uploadResult">
		<ul>

		</ul>
	</div>
</form>
<div>
	<button class="submit" id="b_modify">수정</button>
	<button style="margin: 0" class="submit" id="b_list">리스트</button>
</div>
<div class='bigPictureWrapper'>
	<div class='bigPicture'></div>

</div>
</article>







<%@include file="../include/footer.jsp"%>