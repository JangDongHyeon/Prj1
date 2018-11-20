<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
	$(document).ready(function() {
		$("#btnLogin").on("click", function() {
			$("#idP").empty()
			$("#pwdP").empty();
			var formObj = document.frm;
			if (formObj.id.value === "") {

				$("#idP").html("아이디를 입력하세요")
				return;
			}
			if (formObj.pwd.value === "") {

				$("#pwdP").html("비밀번호를 입력하세요")
				return;
			}
			formObj.method = "post";
			formObj.action = "${path}/member/login";
			formObj.submit();

		});
		$("#btnJoin").on("click", function() {
			location.href = "${path}/member/join";
		});
		$("#btnFind").on("click", function() {
			location.href = "${path}/member/join";
		});

	});
</script>



<article>
	<h1 style="color: black;">로그인</h1>
	<form name="frm" method="post" action="">
		<fieldset>

			<label>아이디:</label> <input type="text" id="userId" name="id"
				value="${id}"><span
				style="color: red; font-size: 17px; margin-left: 20px;" id="idP"></span>
			<legend></legend>
			<label>비밀번호:</label> <input type="password" id="pwd" name="pwd"><span
				id="pwdP" style="color: red; font-size: 17px; margin-left: 20px;"></span>
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
	<button class="submit" type="button" id="btnFind">아이디/비밀번호찿기</button>


</article>



<%@include file="../include/footer.jsp"%>