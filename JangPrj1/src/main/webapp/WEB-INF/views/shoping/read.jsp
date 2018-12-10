<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<%@include file="../include/sub_menu.jsp"%>
<style>
	.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:70%;
  height:70%;
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
  width:300px;
}
</style>
<script>	

	$(document).ready(function() {
		 listAll(); 
		getLikeNum();
		 $("#close").click(function(){
			 console.log("gd")
			var modal = document.getElementById('myModal');
			 modal.style.display = "none";
		}); 
		
		
		$("form[name=frm]").submit(function() {
			var rid = $(this).find("input[name=rid]").val();
			var content = $(this).find("input[name=content]").val();
			 var modal = document.getElementById('myModal');
			
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
						modal.style.display = "none";
						listAll();
						
					}
				}

			});

		});
		
		 
		$("#list").on("click",function(){
			var frmObj=$("form[name=formm]");
			frmObj.attr("action","${path}/shoping/catagory");
			frmObj.attr("method","get");
			frmObj.submit();
			
		});
		
		
		$("#replyIn").on("click", function() {
			var replyContent = $("#replyContent").val();
			var pseq = ${readVO.pseq};
	
			
			$.ajax({
				type : "POST",
				url : "${path}/proReply/insert",
				headers : {
					"Content-Type" : "application/json"
				},
				dataType : "text",
				data : JSON.stringify({
					pseq : pseq,
					content : replyContent
				}),
				success : function(data) {
					if (data === "success") {
						alert("댓글 등록 완료");
						listAll();
					}
				}

			});
		});

		$("#love").on("click",function(){
			var userId='<%=(String) session.getAttribute("userId")%>'
		
			if(userId===null||userId===''||userId==='null'){
				alert("로그인을 해주세요");
				return;
			}
			$.ajax({
				type:"get",
				url:"${path}/shoping/prLoveInsert/${readVO.pseq}",
				success:function(data){
					if($.trim(data)==='Mai') $("#love").html("좋아요");
					else $("#love").html("취소");
					getLikeNum();
				}
				
				
			});
		});
	
	});
	function listAll(){
		$.getJSON("${path}/proReply/list/${readVO.pseq}",function(data){
			var userId='<%=(String)session.getAttribute("userId")%>'
			var str = "";
			$(data).each(function(){
				str += "<li><p>"+ "<input type=hidden name=rid value="+this.rid+">";
				str += "아이디:&nbsp;" + this.reply + "<br>";
				str += "내용:&nbsp;<span style='font-size: 15px;'>" +this.content	+ "</span><br>";
				str += "날짜:&nbsp;" + cangeDate(this.updatedate);
				if (userId === this.reply) {
				str += "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
					+ "<button class='cancel' id='reModify' >수정</button>&nbsp;<button class=cancel id='reDelete'>삭제</button>";
					}
				str += "</p></li>";
				str += "<hr>";
				});
			$("#pro_reply").find("ul").html(str);
			$('#reModify').click(function() {			
				var modal = document.getElementById('myModal');
				var rid = $(this).parent().find("input[name=rid]").val();
				var content = $(this).parent().find("span").text();
				$("#reContent").val(content);
				$("#modalRid").val(rid);
			
					modal.style.display = "block";
					
				
				
		});
			$("#reDelete").on("click",function(){
				var rid=$(this).parent().find("input[name=rid]").val();
				if(confirm("정말 삭제하시겠습니까??") == true){

				
				$.ajax({
					type:"get",
					url:"${path}/proReply/delete/"+rid,
					success:function(data){
						if (data === "success") {
							alert("삭제가 되었습니다");
						
						
							listAll();
						}
					}
				});
				}
			});	
		
	
		
	});
	}
	
	
	<%-- function listAll() {
		var userId='<%=(String) session.getAttribute("userId")%>';

		var str = "";
		$.ajax({
		type : "get",
		url : "${path}/proReply/list/${readVO.pseq}",
		success : function(data) {
		for ( var i in data) {
		str += "<li><p>"+ "<input type=hidden name=rid value="+data[i].rid+">";
		str += "아이디:&nbsp;" + data[i].reply + "<br>";
		str += "내용:&nbsp;<span style='font-size: 15px;'>" + data[i].content	+ "</span><br>";
		str += "날짜:&nbsp;" + cangeDate(data[i].updatedate);
		if (userId === data[i].reply) {
		str += "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
			+ "<button class='cancel' id='reModify' >수정</button>&nbsp;<button class=cancel id='reDelete'>삭제</button>";

					                  }
			str += "</p></li>";
			str += "<hr>";
						}

						$("#pro_reply").find("ul").html(str);

						$('#reModify').click(function() {			
							var modal = document.getElementById('myModal');
							var rid = $(this).parent().find("input[name=rid]").val();
							var content = $(this).parent().find("span").text();
							$("#reContent").val(content);
							$("#modalRid").val(rid);
						
								modal.style.display = "block";
							
							
					});
						
						
						$("#reDelete").on("click",function(){
							var rid=$(this).parent().find("input[name=rid]").val();
							if(confirm("정말 삭제하시겠습니까??") == true){

							
							$.ajax({
								type:"get",
								url:"${path}/proReply/delete/"+rid,
								success:function(data){
									if (data === "success") {
										alert("삭제가 되었습니다");
										listAll();
									}
								}
							});
							}
						});	
						

				}
		
	});
	} --%>

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

	function getLikeNum() {
		$.ajax({
			type : "get",
			url : "${path}/shoping/prLoveCount/${readVO.pseq}",
			success : function(data) {

				$("#loveP").html(data + "개 입니다");
			}

		});
	}
	$(document).ready(function(){
		$("#cart").on("click",function(){
			var userId='<%=(String)session.getAttribute("userId")%>'
				if(userId==='null'){
					alert("로그인을 해주세요");
					return;
				}
			
			var formObj=$("form[name=formm]");
			formObj.attr("action","${path}/cart/cartInsert");
			formObj.submit();
		});
		
		$("#buy").on("click",function(){
			var userId='<%=(String)session.getAttribute("userId")%>'
				if(userId==='null'){
					alert("로그인을 해주세요");
					return;
				}
			var formObj=$("form[name=formm]");
			formObj.attr("action","${path}/order/buy");
			formObj.submit();
		});
		
		
		
	});
		
	$(document).ready(function(){
		$("#picl").on("click",function(){
			var img='${readVO.image}';
			  $(".bigPictureWrapper").css("display","flex").show();
			    
			    $(".bigPicture")
			    .html("<img src='/resources/product_images/"+img+"' >")
			    .animate({width:'100%', height: '100%'}, 1000);
		});
		$(".bigPictureWrapper").on("click", function(e){
		    $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
		    setTimeout(function(){
		      $('.bigPictureWrapper').hide();
		    }, 1000);
		  });
	});
	
	
</script>

<div class='bigPictureWrapper'>
  <div class='bigPicture'>
  </div>
</div>
<article>
	<h1>상품 상세</h1>
	<div id="itemdetail">
		<form name="formm" method="post">
			<input type="hidden" name="keyword" value="${searchIn.keyword}">
			<input type="hidden" name="search" value="${searchIn.search}">
			<input type="hidden" name="page" value="${searchIn.page}">
			 <input	type="hidden" name="numPage" value="${searchIn.numPage}">
			<input type="hidden" name="image" value="${readVO.image}">
			<input type="hidden" name="pseq" value="${readVO.pseq}">
			
			
			<fieldset>
			
				<span style="float: left;"> <img id="picl"
					src="/resources/product_images/${readVO.image}">
				</span>
				<h2 style="color: black;">${readVO.name}</h2>
				<label>가격</label>

				<fmt:formatNumber value="${readVO.price2}" var="price"
					pattern="###,###,###" />
				<p>${price}원
					<legend></legend>
				</p>
					<label>날짜</label>
				<p>
					<fmt:formatDate value="${readVO.indate}" pattern="yyyy-MM-dd" />
				<legend></legend>
				</p>

			
			
				<label>수 량:</label>
			
			<p>
					<select name="quantity">
						<c:forEach begin="1" end="10" var="num" step="1">
							<option value="${num}">${num}</option>
								
						</c:forEach>
					
					</select>
						<legend></legend>
				</p>
					<legend></legend>
					<label>좋아요:</label>
				<p id="loveP" style="font-size: 15px;"></p>

				<button type="button" id="love" class="cancel">좋아요</button>
			</fieldset>
			<div class="clear"></div>
			<button type="button" id="buy" class="submit">구매하기</button>
			<button type="button" id="cart" class="submit">장바구니</button>
			<button type="button" id="list" class="submit">목록</button>
		</form>
	</div>
	<br>
	<hr>
	<h2 align="center" style="color: black;">평가</h2>
	<div id="pro_reply">

		<ul style="list-style: none;">

		</ul>
		<c:if test="${sessionScope.userId!=null}">
			<label>댓글 내용</label>
			<input type="text" name="replyContent" id="replyContent">
			<button type="button" id="replyIn">댓글 입력</button>
		</c:if>
	</div>


</article>

<div id="myModal" class="modal">

	<div class="modal-content">
		<form method="post" name="frm">
			
			<input type="hidden" name="rid" id="modalRid">
			<input type="text" name="content" id="reContent"> <br> <input
				type="submit" value="수정">
			<button type="button" id="close">닫기</button>
		</form>
	</div>
</div>


<%@include file="../include/footer.jsp"%>