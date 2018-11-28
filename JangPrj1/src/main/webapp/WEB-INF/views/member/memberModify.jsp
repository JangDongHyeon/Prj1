<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
	$(document).ready(function() {
		$("#join").on("click", function() {
			$("span").empty();
			var regExpEmail= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			var reg_pwd = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
			var regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/;
			  var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
			  var regName = /^[가-힣]{2,4}$/;


			formObj = $("form[name='formm']");
	
			if (formObj.pwd.value === ""||formObj.pwd.value.match(reg_pwd)==null) {
				$("#pwdP").html("비밀번호를 확인하세요");
				formObj.pwd.focus();
				return;
			}
			if (formObj.pwdck.value === "") {
				$("#pwdP").html("비밀번호 확인을 확인하세요");
				formObj.pwdck.focus();
				return;
			}
			if(formObj.pwd.value!==formObj.pwdck.value){
				$("#pwdP").html("비밀번호 확인해주세요");
				formObj.pwdck.focus();
				return;
			}
			if (formObj.name.value === ""||isNaN(formObj.name.value)===false||formObj.name.value.match(regName)==null) {
				$("#nameP").html("이름을 확인하세요");
				formObj.name.focus();
				return;
			}
			if (formObj.address.value === "") {
				$("#addressP").html("주소를 확인하세요");
				formObj.address.focus();
				return;
			}
			if (formObj.email.value === ""||formObj.email.value.match(regExpEmail)==null) {
				$("#emailP").html("이메일을 확인하세요");
				formObj.email.focus();
				return;
			}
			if (formObj.phone.value === ""||formObj.phone.value.match(regExpPhone)==null) {
				$("#phoneP").html("휴대전화번호를 확인하세요");
				formObj.phoneP.focus();
				return;
			}
			if (formObj.getId.value == "") {
				$("#idP").html("중복체크를 클릭하세요");
				
				return;
			}
			formObj.method = "post";
			formObj.action = "${path}/member/join";
			formObj.submit();

		});
		$("#modify").on("click", function() {
			formObj = $("form[name='formm']");
			$(formObj).attr("action","${path}/member/memberModify").submit();

		});

	});
</script>
<article>
<h1>회원가입</h1>
	<form method="post" name="formm">
		<fieldset>
			<label>아이디</label> <input type="text" id="userid" name="id" placeholder=" 6~20자 영문자 또는 숫자"  value="${vo.id}"readonly="readonly" >
			<input type="hidden" name="getId" id="getId"> <span
				style="color: red; font-size: 17px; margin-left: 20px;" id="idP"></span>

			<input type="button" value="중복확인" id="useridCheck">
			<legend></legend>
			<label>비밀번호</label> <input type="password" id="pwd" name="pwd" placeholder="영문,숫자를 혼합하여 6~20자 이내)" value="${vo.pwd}">
			<span style="color: red; font-size: 17px; margin-left: 20px;"
				id="pwdP"></span>
			<legend></legend>
			<label>비밀번호확인</label> <input type="password" id="pwdck" name="pwdck">
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