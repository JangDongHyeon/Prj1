<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/submenu.jsp"%>
<script>
 $(document).ready(function(){
	$("form[name=frm]").submit(function(e){
		var regNumber=new RegExp("/^[0-9]*$/");
		var inputfile=$("input[name='uploadFile']");
		var files=inputfile[0].files;
	
		
		
		
		if($(this).find("input[name='name']").val()===""){
			alert("상품명을 입력하세요");
			return false;
		}
		if($(this).find("input[name='price1']").val()===""||regNumber.test($(this).find("input[name='price1']").val())){
			alert("원가를 입력하세요");
			return false;
		}
		if($(this).find("input[name='price2']").val()===""||regNumber.test($(this).find("input[name='price1']").val())){
			alert("판매가를 입력하세요");
			return false;
		}
		if($(this).find("input[name='price3']").val()===""||regNumber.test($(this).find("input[name='price1']").val())){
			alert("마진를 입력하세요");
			return false;
		}
		if($(this).find("input[name='content']").val()===""){
			alert("내용을 입력하세요");
			return false;
		}
		if(inputfile.val()===""){
			alert("이미지를 업로드하세요");
			return false;
		}
		if(!checkExtension(files[0].name,files[0].size)){
			return false;
		}
		 frm.action='/admin/adminInsert';
		frm.method='post';
		
		
	});
	
	 var regex=new RegExp("(.*?)\.(gif|jpg|jpeg)$");
	 var maxSize=5242880;
	function checkExtension(fileName,fileSize){

		 if(fileSize>=maxSize){
			 alert("파일  사이즈 초과");
		 	return false;
		 }
		    if (!regex.test(fileName)) {
		        alert("이미지 형식의 파일을 선택하십시오");
		        return false;
		    } 
		    return true;
	 } 
	
	$("input[type=file]").change(function(e){
		var formData=new FormData();
		var inputFile=$("input[name='uploadFile']");
		var files =inputFile[0].files;
		
		for(var i=0;i<files.length;i++){
			if(!checkExtension(files[0].name,files[i].size)){
				return false;
			}
		formData.append("uploadFile",files[i]);
			
			
		}
		$.ajax({
			url:'/BFile/uploadAjaxFile',
			processData:false,
			contentType:false,
			data:formData,
			type:'post',
			dataType:'json',
			success:function(data){
				console.log(data);
			}
		});
	});
	
 });


</script>


<article>
	<h1>상품등록</h1>
	<form name="frm" method="post" enctype="multipart/form-data">
		<table id="list">
			<tr>
				<th>상품분류</th>
				<td colspan="5"><select name="kind">
						<c:forEach varStatus="num" items="${kindList}" var="kind">
							<option value="${num.count}">${kind}</option>
						</c:forEach>
				</select>
			<tr>
				<th>상품명</th>
				<td width="343" colspan="5"><input type="text" name="name" size="47" maxlength="50"></td>
			</tr>
			<tr>
				<th>원가[A]</th>
				<td width="70"><input  type="text" name="price1" size="11"></td>
				<th>판매가[B]</th>
				<td width="70"><input type="text" name="price2" size="11"></td>
				<th>마진</th>
				<td width="72"><input type="text" name="price3" size="11"></td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td colspan="5"><textarea name="content" rows="8" cols="70"></textarea>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td width="343" colspan="5"><input type="file" name="uploadFile">
				</td>
			</tr>
		</table>
		<input class="btn" id="save" type="submit" value="등록">
		<input class="btn" type="button" value="취소">
	</form>
</article>
<%@ include file="include/footer.jsp"%>
</body>
</html>




