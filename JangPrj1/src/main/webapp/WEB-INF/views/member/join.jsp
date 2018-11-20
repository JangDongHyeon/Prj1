<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
	$(document).ready(function() {
		$("#join").on("click", function() {
			$("span").empty();

			formObj = document.formm;
			if (formObj.id.value === "") {
				$("#idP").html("아이디를 입력하세요")
				return;
			}
			if (formObj.pwd.value === "") {
				$("#pwdP").html("비밀번호를 입력하세요")
				return;
			}
			if (formObj.pwdck.value === "") {
				$("#pwdP").html("비밀번호 확인을 입력해주세요")
				return;
			}
			if(formObj.pwd.value!==formObj.pwdck.value){
				$("#pwdP").html("비밀번호 확인해주세요")
				return;
			}
			if (formObj.name.value === "") {
				$("#nameP").html("이름을 입력하세요")
				return;
			}
			if (formObj.address.value === "") {
				$("#addressP").html("주소를 입력하세요")
				return;
			}
			if (formObj.email.value === "") {
				$("#emailP").html("이메일을 입력하세요")
				return;
			}
			if (formObj.phone.value === "") {
				$("#phoneP").html("휴대전화번호를 입력하세요")
				return;
			}
			if (formObj.getId.value == "") {
				$("#idP").html("중복체크를 입력하세요")
				return;
			}
			formObj.method = "post";
			formObj.action = "${path}/member/join";
			formObj.submit();

		});
		$("#login").on("click", function() {
			location.href = "${path}/member/login";

		});
		$("#useridCheck").on("click", function() {
			formObj = document.formm;
			var id = $("#userid").val();
			if (formObj.id.value === "") {
				$("#idP").html("아이디를 입력하세요")
				return;
			}
			console.log(id);
			
			$.ajax({
				type : "get",
				url : "${path}/member/checkId/"+id,
				
				success : function(result) {
					
					if($.trim(result.msg)  ==  "success") {
						console.log(result.msg);
						
						$("#idP").html("아이디를 사용할수있습니다");
						
						$('input[name=getId]').attr('value',id); 

					
						var a=$("getId").val();
						console.log(a);
					} else $("#idP").html("중복된 아이디가 있습니다");
				}

			});

		});

	});
</script>
<article>

	<h1>회원가입</h1>
	<form method="post" name="formm">
		<fieldset>
			<label>아이디</label> <input type="text" id="userid" name="id">
			<input type="hidden" name="getId" id="getId"> <span
				style="color: red; font-size: 17px; margin-left: 20px;" id="idP"></span>

			<input type="button" value="중복확인" id="useridCheck">
			<legend></legend>
			<label>비밀번호</label> <input type="password" id="pwd" name="pwd">
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="pwdP"></span>
			<legend></legend>
			<label>비밀번호확인</label> <input type="password" id="pwdck" name="pwdck">
			<legend></legend>
			<label>이름</label> <input type="text" id="name" name="name"> <span
				style="color: red; font-size: 17px; margin-left: 20px;" id="nameP"></span>
			<legend></legend>
			<label>주소</label> <input type="text" id="address" name="address">
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="addressP"></span>
			<legend></legend>
			<label>이메일</label> <input type="text" id="email" name="email">
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="emailP"></span>
			<legend></legend>
			
			<label>휴대전화번호</label> <input type="text" id="phone" name="phone">
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="phoneP"></span>

			<legend></legend>
		</fieldset>


	</form>
	<button class="submit" id="join">회원가입</button>
	<button class="cancel" id="login">로그인</button>
</article>
<%@include file="../include/footer.jsp"%>

