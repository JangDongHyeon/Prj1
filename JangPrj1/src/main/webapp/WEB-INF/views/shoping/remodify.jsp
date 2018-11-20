<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<link href="/resources/css/shopping.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	$(document).ready(function() {
		$("form[name=frm]").submit(function() {
			var content = $(this).find("input[name=content]").val();
			var rid = $(this).find("input[name=rid]").val();

			$.ajax({
				type : "POST",
				url : "${path}/proReply/modify",
				headers : {
					"Content-Type" : "application/json;charset=UTF-8"
				},
				dataType : "text",
				data : JSON.stringify({
					content : content,
					rid : rid
				}),
				success : function(data) {

					if (data === "success") {
						alert("댓글이 수정되었습니다");

						
					}
				}

			});

		});

		$("#close").on("click",function(){
			window.close();
		});
		
	});
</script>
</head>
<body>
	<form method="post" name="frm">
		<input type="hidden" name="rid" value="${re.rid}"> <input
			type="text" name="content" value="${re.content}"> <br> <input
			type="submit" value="전송">
		<button type="button" id="close">닫기</button>
	</form>
</body>
</html>