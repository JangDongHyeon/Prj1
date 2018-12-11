<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp"%>

<%@include file="../include/sun_mypage.jsp"%>
<script>
	$(document).ready(function() {
		console.log('${msg}');
		if('${msg}'!==""){
			alert("수정완료");
		}
		$("#modify").on("click", function() {
			$("span").empty();
			var regExpEmail= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			var reg_pwd = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
			var regExpPhone = /^\d{3}\d{3,4}\d{4}$/;
			  var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
			  var regName = /^[가-힣]{2,4}$/;


			formObj = $("form[name='formm']");
			
			if ($(formObj).find("input[name='pwd']").val()=== ""||$(formObj).find("input[name='pwd']").val().match(reg_pwd)==null) {
				$("#pwdP").html("비밀번호를 확인하세요");
				formObj.pwd.focus();
				return;
			}
			if ($(formObj).find("input[name='pwdck']").val() === "") {
				$("#pwdP").html("비밀번호 확인을 확인하세요");
				formObj.pwdck.focus();
				return;
			}
			if($(formObj).find("input[name='pwd']").val()!==$(formObj).find("input[name='pwdck']").val()){
				$("#pwdP").html("비밀번호 확인해주세요");
				formObj.pwdck.focus();
				return;
			}
			if ($(formObj).find("input[name='name']").val() === ""||isNaN($(formObj).find("input[name='name']").val())===false||$(formObj).find("input[name='name']").val().match(regName)==null) {
				$("#nameP").html("이름을 확인하세요");
				formObj.name.focus();
				return;
			}
			if ($(formObj).find("input[name='address']").val() === "") {
				$("#addressP").html("주소를 확인하세요");
				formObj.address.focus();
				return;
			}
			if ($(formObj).find("input[name='email']").val() === ""||$(formObj).find("input[name='email']").val().match(regExpEmail)==null) {
				$("#emailP").html("이메일을 확인하세요");
				formObj.email.focus();
				return;
			}
			if ($(formObj).find("input[name='phone']").val()=== ""||$(formObj).find("input[name='phone']").val().match(regExpPhone)==null) {
				$("#phoneP").html("휴대전화번호를 확인하세요");
				formObj.phone.focus();
				return;
			}
			$(formObj).attr("action","${path}/member/memberModify").submit();
	

		});
		$("#login").on("click", function() {
			location.href='${path}/member/login';

		});

	});
</script>
<article>
<h1>회원수정</h1>
	<form method="post" name="formm">
		<fieldset>
			<label>아이디</label> <input type="text" id="userid" name="id" placeholder=" 6~20자 영문자 또는 숫자"  value="${vo.id}"readonly="readonly" >
			<input type="hidden" name="getId" id="getId"> <span
				style="color: red; font-size: 17px; margin-left: 20px;" id="idP"></span>

			
			<legend></legend>
			<label>비밀번호</label> <input type="password" id="pwd" name="pwd" placeholder="영문,숫자를 혼합하여 6~20자 이내)" value="${vo.pwd}">
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="pwdP"></span>
			<legend></legend>
			<label>비밀번호확인</label> <input type="password" id="pwdck" name="pwdck" value="${vo.pwd}">
			<legend></legend>
			<label>이름</label> <input type="text" id="name" name="name" value="${vo.name}"> <span
				style="color: red; font-size: 17px; margin-left: 20px;" id="nameP"></span>
			<legend></legend>
			<label>주소</label> <input type="text" id="address" name="address" value="${vo.address}">
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="addressP"></span>
			<legend></legend>
			<label>이메일</label> <input type="text" id="email" name="email" value="${vo.email}">
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="emailP"></span>
			<legend></legend>
			
			<label>휴대전화번호</label> <input type="text" id="phone" name="phone" value="${vo.phone}" >
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="phoneP"></span>

			<legend></legend>
		</fieldset>


	</form>
	<button class="submit" id="modify">수정</button>
	<button class="cancel" id="login">로그인</button>
</article>
<%@include file="../include/footer.jsp"%>