<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<script>
	$(document).ready(
			function() {
				getReplyList();
				$("#loveChk").on("click", function() {

				});
				$("#b_modify").on(
						"click",
						function() {
							$("form[name=frm]").attr("action",
									"${path}/board/boardModify").submit();
						});
				$("#b_delete").on(
						"click",
						function() {
							if (confirm("정말 삭제하시겠습니까?") === true)
								$("form[name=frm]").attr("action",
										"${path}/board/boardDelete").submit();
							else
								return;
						});

				$("#b_list").on(
						"click",
						function() {
							$("form[name=frm]").attr("action",
									"${path}/board/boardSelect").submit();
						});
				
				$("#replyIn").on("click",function(){
					var content=$("#replyContent");
					var contentVal=content.val();
					var bno=$("input[name='bno']").val();
					$.ajax({
						type:"post",
						url:"${path}/bReply/replyNewInsert",
						headers:{
							"Content-type":"application/json",
							
						},
						dataType:"text",
						data:JSON.stringify({
							bno:bno,
							r_content:contentVal
						}),
						success:function(data){
							if(data==="success"){
								alert("댓글이 등록 완료!!");
							}
							getReplyList();
							content.val("");
						}
					
						
						
					});
					$("#replyLi").find("li").html(str);
				});
				
				
			});
	function getReplyList(){
		var bno=$("input[name='bno']").val();		
		$.getJSON("/bReply/replyList/"+bno,function(data){
			var str="";
			$(data.boardList).each(function(){
				str += "<li><p>";if(this.bIndent>0){
				for(var i=0;i<this.bIndent;i++){str+="&nbsp; &nbsp;&nbsp;"}};
			 	str += "<input type=hidden name=r_no value="+this.r_no+">";
				str += "아이디:&nbsp;" + this.r_id + "<br>";
				str += "내용:&nbsp;<span style='font-size: 15px;'>" +this.r_content	+ "</span><br>";
				str += "날짜:&nbsp;" + cangeDate(this.updatedate);
				if (userId === this.r_id) {
				str += "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
					+ "<button id='reModify'>수정</button>&nbsp;<button id='reDelete'>삭제</button>"+
					"<button id='reinser'>댓글달기</button>"	
				}
				str += "</p></li>";
				str += "<hr>";
			});
			
		});
	}
	function cangeDate(date) {
		data = new Date(parseInt(date));
		year = data.getFullYear();
		month = data.getMonth();
		day = data.getDate();
		hour = data.getHours();
		minute = data.getMinutes();
		second = data.getSeconds();
		strDate = year + "-" + month + "-" + day + " " + hour + ":" + minute
				+ ":" + second;
		return strDate;

	}
	
</script>
<form name="frm" method="get">
	<input type="hidden" name="keyword" value="${searchVO.keyword}">
	<input type="hidden" name="search" value="${searchVO.search}">
	<input type="hidden" name="page" value="${searchVO.page}"> <input
		type="hidden" name="numPage" value="${searchVO.numPage}"> <input
		type="hidden" name="bno" value="${boardVO.bno}">
</form>

<article>
	<h2 style="color: black;"></h2>
	<div style="height: 80px; width: 100%;">
		<span style="padding-bottom: 10px; margin-bottom: 20px;">제목:${boardVO.subject}</span><br>
		<div style="float: left; font-size: 17px; margin-top: 10px;">사용자:${boardVO.bid}</div>
		<div style="float: left; font-size: 17px; margin-top: 10px;">
			&nbsp;|&nbsp;시간:
			<fmt:formatDate value="${boardVO.updatedate}"
				pattern="yy-MM-dd HH:mm:ss" />
		</div>
		<div style="float: right; font-size: 17px; margin-top: 10px;">&nbsp;|&nbsp;조회${boardVO.cnt}</div>
		<div style="float: right; font-size: 17px; margin-top: 10px;">&nbsp;&nbsp;추천${boardVO.love}</div>
		<%--   <p style="float: right: ;">조회${boardVO.cnt}</p> --%>

	</div>
	<hr>
	<div style="height: 400px; width: 100%; font-size: 13px;">
		${boardVO.content}</div>
	<hr>
	<div>
		<button class="submit" id="loveChk">추천</button>
		<button class="submit" id="b_modify">수정</button>
		<button class="submit" id="b_delete">삭제</button>
		<button style="margin: 0" class="submit" id="b_list">리스트</button>

	</div>
	<h2 align="center" style="color: black;">댓글</h2>
	<div id="replyLi">
		<ul style="list-style: none;">
			
		</ul>
		<c:if test="${sessionScope.userId!=null}">
			<label>댓글 내용</label>
			<input type="text" name="r_content" id="replyContent">
			<button type="button" id="replyIn">댓글 입력</button>
		</c:if>

	</div>
</article>


<%@include file="../include/footer.jsp"%>