<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<style>
.uploadResult {
  width:100%;
  background-color: pink;
}
.uploadResult ul{
  display:flex;
  flex-flow: row;
  justify-content: center;
  align-items: center;
}
.uploadResult ul li {
  list-style: none;
  padding: 10px;
  align-content: center;
  text-align: center;
}
.uploadResult ul li img{
  width: 100px;
}
.uploadResult ul li span {
  color:white;
}
.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: gray; 
  z-index: 100;
  background:rgba(255,255,255,0.5);
}
.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}

.bigPicture img {
  width:600px;
}

</style>
<script>
	$(document).ready(function() {
		getBoardFileList();
		
		var replyPageNum=1;
				getReplyList(replyPageNum);
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
							getReplyList(replyPageNum);
							content.val("");
						}
					
						
						
					});
					
				});
				
				$("form[name=formm]").submit(function() {
					var r_no=$("input[name='r_no']").val();
					var r_content=$("#reContent").val();
					var userId='<%=(String) session.getAttribute("userId")%>';
					var modal = document.getElementById('myModal');
				
					$.ajax({
						type:'post',
						url:'${path}/bReply/replyUpdate',
						headers:{
							"Content-Type" : "application/json;charset=UTF-8"
						},
						dataType : "text",
						data : JSON.stringify({
							r_content : r_content,
							r_no : r_no,
							r_id:userId
						}),
						success:function(data){
							if(data==="success"){
								alert("수정완료");
								modal.style.display = "none";
								getReplyList(replyPageNum);
							}
						}
					});
					
					
					
					
				});
				
				$("#r_delete").on("click",function(){
					var modal = document.getElementById('myModal');
					var rno=$("input[name='r_no']").val();
					$.ajax({
						type:'get',
						url:'${path}/bReply/replyDelete/'+rno,
						dataType : "text",
						success:function(data){
							if($.trim(data)==="success"){
								alert("삭제완료");
								modal.style.display = "none";
								getReplyList(replyPageNum);
							}
						}
						
						
					});
				});
				
				$("#replyId").on("click",".replyCl #reinser",function(){
					var modal = document.getElementById('mymol');
					var bGroup=$(this).parent().find("input[name='bGroup']").val();
					var bIndent=$(this).parent().find("input[name='bIndent']").val();
					var bStep=$(this).parent().find("input[name='bStep']").val();
					
					$("#bG").val(bGroup);
					$("#bI").val(bIndent);
					$("#bS").val(bStep);
					
					modal.style.display = "block";
					
				});
				$("#replyId").on("click",".replyCl #reModify",function(){
					var modal = document.getElementById('myModal');
					var r_no = $(this).parent().find("input[name='r_no']").val();
					var content = $(this).parent().find(".replyText").text();
					
					$("#modalRno").val(r_no);
					$("#reContent").val(content);
					
					modal.style.display = "block";
				});
				
				$("#close").on("click",function(){
					var modal = document.getElementById('myModal');					
					modal.style.display = "none";
				});
				
				$("#rclose").on("click",function(){
					var modal = document.getElementById('mymol');					
					modal.style.display = "none";
				});
				
				$("#r_Inbtn").on("click",function() {
					var modal = document.getElementById('mymol');
					var bGroup=$("#bG").val();
					var bIndent=$("#bI").val();
					var bStep=$("#bS").val();
					var rcontent=$("#re_rContent");
					var r_content=$("rcontent").val();
					var bno=$("input[name='bno']").val();
					var userId='<%=(String) session.getAttribute("userId")%>';
					$.ajax({
						type:'post',
						url:"${path}/bReply/replyInsert/"+bno,
						headers:{
							"Content-type":"application/json",
							
						},
						dataType:"text",
						data:JSON.stringify({
							bGroup:bGroup,
							bIndent:bIndent,
							bStep:bStep,
							r_id:userId,
							r_content:r_content
							
						}),
						success:function(data){
							if(data==="success"){
								alert("댓글이 등록 완료!!");
							}
							getReplyList(replyPageNum);
							rcontent.val("");
						}
						
					})
					
					
				});
				
				$(".pageTe").on("click","li a",function(event){
					event.preventDefault();
					replyPageNum=$(this).attr("href");
					getReplyList(replyPageNum);
				});
			
				
				
				
			});
	function getReplyList(page){
		var bno=$("input[name='bno']").val();		
		$.getJSON("/bReply/replyList/"+bno+"/"+page,function(data){
			var userId='<%=(String) session.getAttribute("userId")%>';
			
			var str="";
			$(data.boardList).each(function(){
				str += "<li><p class='replyCl'>";if(this.bIndent>0){
				for(var i=0;i<this.bIndent;i++){str+="<span>RE</span>"}};
			 	str += "<input type=hidden name=r_no value="+this.r_no+">";
			 	str +="<input type=hidden name=bGroup value="+this.bGroup+">";
			 	str +="<input type=hidden name=bIndent value="+this.bIndent+">";
			 	str +="<input type=hidden name=bStep value="+this.bStep+">";
				str += "아이디:&nbsp;<span style='font-size: 15px;'>" + this.r_id + "</span><br>";
				str += "내용:&nbsp;<span style='font-size: 15px;'class='replyText'>" +this.r_content	+ "</span><br>";
				str += "날짜:&nbsp;" + cangeDate(this.updatedate);
				if (userId === this.r_id) {
				str += "&emsp;&emsp;"
					+ "<button id='reModify'>수정</button>&nbsp;";
					
				}
				if(userId!=='null'){
					str += "&emsp;";
					str+="<button id='reinser'>댓글달기</button>";
				}
				str += "</p></li>";
				str += "<hr>";
			
			});
			
			$("#replyLi").find("ul").html(str);
			printPagenum(data.pageMaker)
		});
	}
	function printPagenum(pageMaker){
		var str="";
		if(pageMaker.perPage){
		str+="<li style=float:left;><a href='"+(pageMaker.startBlock-1)+"'>이전</a></li>";	
		}
	for(var i=pageMaker.startBlock,len=pageMaker.endBlock;i<=len;i++){
		if(pageMaker.critia.page==i){
			str+="<li style=float:left;><span style=color: red;>"+i+"</span></li>";
		}else
			str+="<li style=float:left;><a href='"+i+"'>"+i+"</a></li>";
	}
	if(pageMaker.nextPage&&pageMaker.endBlock>0){
		str+="<li style=float:left;><a href='"+(pageMaker.endBlock+1)+"'>다음</a></li>";	
		
	}
	
		$(".pageA").find("ul").html(str);
		
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
	function getBoardFileList(){
		var bno='${boardVO.bno}';
		var iomg=$("#iomg");
		$.getJSON("/board/getBoardFileList",{bno:bno},function(arr){
			var str="";
			var strI="";
			$(arr).each(function(i,data){
			
				if(data.filetype=='true'){
			           var fileCallPath =  encodeURIComponent( data.uploadPath+ "/s_"+data.uuid +"_"+data.fileName);
			           var fileCallPathImg =  encodeURIComponent( data.uploadPath+ "/"+data.uuid +"_"+data.fileName);
			       
			           str += "<li data-path='"+data.uploadPath+"' data-uuid='"+data.uuid+"' data-filename='"+data.fileName+"' data-filetype='"+data.filetype+"' ><div>";
			           str += "<img src='${path}/BFile/display?fileName="+fileCallPath+"'>";
			           str += "</div>";
			           str +"</li>";
			        
				}else{
		             
			           str += "<li data-path='"+data.uploadPath+"' data-uuid='"+data.uuid+"' data-filename='"+data.fileName+"' data-filetype='"+data.filetype+"'><div>";
			           str += "<span style='font-size:15px; color:black;'> "+ data.fileName+"</span><br/>";
			           str += "<img src='/resources/images/attach.png'></a>";
			           str += "</div>";
			           str +"</li>";
			         }
			});
		      $(".uploadResult ul").html(str);
		});
		
		
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
			&nbsp;&nbsp;시간:
			<fmt:formatDate value="${boardVO.updatedate}"
				pattern="yy-MM-dd HH:mm:ss" />
		</div>
		<div style="float: right; font-size: 17px; margin-top: 10px;">&nbsp;s&nbsp;조회${boardVO.cnt}</div>
		<div style="float: right; font-size: 17px; margin-top: 10px;">&nbsp;&nbsp;추천${boardVO.love}</div>
		<%--   <p style="float: right: ;">조회${boardVO.cnt}</p> --%>

	</div>
	<hr>
	<div style="height: 400px; width: 100%; font-size: 13px;">
	<img id="iomg">
		${boardVO.content}</div>
	<hr>
	<div>
		<button class="submit" id="loveChk">추천</button>
		<button class="submit" id="b_modify">수정</button>
		<button class="submit" id="b_delete">삭제</button>
		<button style="margin: 0" class="submit" id="b_list">리스트</button>

	</div>
	
	<div class='bigPictureWrapper'>
  <div class='bigPicture'>
  </div>
</div>
<div class="uploadResult">
 <ul>
 
 </ul>
</div>



	
	<h2 align="center" style="color: black;">댓글</h2>
	<div id="replyLi">
		<ul style="list-style: none;" id="replyId">

		</ul>
		
		<c:if test="${sessionScope.userId!=null}">
			<div align="center">
			댓글 내용
			<input type="text" name="r_content" id="replyContent">
			<button type="button" id="replyIn">댓글 입력</button>
			</div>
		</c:if>

	</div>
	<h3>파일</h3>
	<div class="pageA" align="center">
	 <ul style="list-style:none; margin:1px;" class="pageTe">
	
	 </ul>
	</div>
</article>



<div id="myModal" class="modal">

	<div class="modal-content">
		<form method="post" name="formm">

			<input type="hidden" name="r_no" id="modalRno"> 내용:<input
				type="text" name="r_content" id="reContent"> <br> <input
				type="submit" value="수정">
			<button type="button" id="r_delete">삭제</button>
			<button type="button" id="close">닫기</button>
		</form>
	</div>
</div>

<div id="mymol" class="modal">

	<div class="modal-content">
		<form name="frrm">
			<input type="hidden" name="bGroup" id="bG"> <input
				type="hidden" name="bIndent" id="bI"> <input type="hidden"
				name="bStep" id="bS"> 내용:<input type="text" name="r_content"
				id="re_rContent"> <br>
			<button id="r_Inbtn">등록</button>
			<button type="button" id="rclose">닫기</button>
		</form>
	</div>
</div>


<%@include file="../include/footer.jsp"%>