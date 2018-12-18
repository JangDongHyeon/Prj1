<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
	$(document).ready(function() {
		$("#btnLogin").on("click", function() {
			$("#idP").empty()
			$("#pwdP").empty();
			var formObj = $("form[name='formm']");			
			if ($(formObj).find("input[name='id']").val()=== "") {

				$("#idP").html("아이디를 입력하세요")
				return;
			} 
			if ($(formObj).find("input[name='pwd']").val()=== "") {

				$("#pwdP").html("비밀번호를 입력하세요")
				return;
			}
			
			$(formObj).attr("method","post");
			$(formObj).attr("action","${path}/member/login").submit();
		

		});
		$("#btnJoin").on("click", function() {
			location.href = "${path}/member/join";
		});
		
	$("#btnFind").on("click",function(){
	
		var modal=$("#myModal");
	
		$(modal).css("display","block");
	
	
		

	
		
		
		
	});
	$("#findId").on("click",function(){
		var email=$("form[name=frm]").find("input[name=email]").val();
		if(email===""){
			alert("이메일을 입력하세요");
			return;
		}
		$("#memMsg").empty();
		$("#dv").find("button").remove();
		$.ajax({
			type:"get",
			url:"${path}/member/memberEmFind/"+email,
			success:function(data){
				
				if(data!==""){
				 $("#memMsg").html("회원의 아이디는  "+data+" 입니다");
			
				 
				 }else $("#memMsg").html("가입된 아이디가 없습니다");
			
			}
			
		});
	});
	$("#close").on("click",function(){
		
		var modal=$("#myModal");
	
		$(modal).css("display","none");
		$("#memMsg").empty();
		$("input[name=email]").val("");
	
	
		
	});

		
	});
</script>



<article>
	<h1 style="color: black;">로그인</h1>
	<form name="formm" method="post">
		<fieldset>

			<label>아이디:</label> 
			<input type="text" name="id" value="${id}">
				<span style="color: red; font-size: 17px; margin-left: 20px;" id="idP"></span>
			<legend></legend>
			<label>비밀번호:</label> <input type="password"  name="pwd">
			<span id="pwdP" style="color: red; font-size: 17px; margin-left: 20px;"></span>
			
		</fieldset>
	</form>
	<c:choose>
		<c:when test="${msg=='fail'}">
			<span style="color: red; font-size: 17px;">로그인이 실패했습니다 아이디와
				비번을 확인해주세요!!</span>
			<p></p>
		</c:when>
		<c:when test="${msg=='logout'}">
			<span style="color: red;">로그아웃했습니다!</span>
			<p></p>
		</c:when>
	</c:choose>
	<button class="submit" type="button" id="btnLogin">로그인</button>
	<button class="submit" type="button" id="btnJoin">회원가입</button>
	<button class="submit" type="button" id="btnFind">아이디찿기</button>


</article>
<div id="myModal" class="modal">

	<div class="modal-content">
		<form method="post" name="frm">
			<h2 style="color: black;">아이디 찿기</h2>
			이메일 입력:<input type="text" name="email" id="email"> <br>
			<div id="dv"><span id="memMsg" style="color: red; font-size: 17px;"></span></div> <br>
			<button type="button" id="findId" >아이디 찿기</button>
			<button type="button" id="close">닫기</button>
		</form>
	</div>
</div>




<%@include file="../include/footer.jsp"%>