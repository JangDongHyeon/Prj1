<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Run Admin</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/css/admin.css">
<script type="text/javascript">
$(document).ready(function(){
		 $("form").submit(function(){
			
			if($(this).find("input[name=id]").val()===""){
				$("#idP").html("아이디를 입력해주세요");
				return false;
			}
			if($(this).find("input[name=pwd]").val()===""){
				$("#pwP").html("비밀번호를 입력해주세요");
				return false;
			}
			
			$(this).attr("action","/admin/login");
			return true;
		}); 
		
		
	});


</script>
<body>
	<div id="wrap">
		<header>
			<div id="logo">
				<a href="admin/main.jsp"> <img width="600px" height="150px;" src="/resources/images/run_logo.png"
					> 
				</a>
			</div>
		</header>
		<div class="clear"></div>
		<article>
			<div id="loginform">
				<form name="frm" method="post">
					<table>
						<tr>
							<td>아 이 디</td>
							<td><input type="text" name="id" size="10"></td><td><span id="idP"></span></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="pwd" size="10"></td><td><span id="pwP"></span></td>
						</tr>
						<tr align="center">
							<td colspan="2">
							<input class="btn" type="submit" value="업무 로그인"><br><br> 
								<h4 style="color: red">${msg}</h4>
								</td>
						</tr>
					</table>
				</form>
			</div>
		</article>
	</div>
</body>
</html>

