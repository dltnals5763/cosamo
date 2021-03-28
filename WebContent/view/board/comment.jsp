<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import ="java.util.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    pageContext.setAttribute("replaceChar", "\n");

%> 

<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="UTF-8" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path}/a00_com/jquery-3.5.1.js"></script>
<script type="text/javascript">

//

	   /*
	   
	   */
   $(document).ready(function(){
       <%-- 화면 70%, 가운데 정렬 --%>
	   $(".container").css("width","70%");
	   $(".container").css("margin","0 auto");
	   $(".container").css("text-align","center");
	   $(".second").css("display","inline-block");
	   //$("#frm").css("text-align","center");
	   // 댓글 입력 창 크기
	   $("[name=comment]").css("width","500px");
	   $("[name=comment]").css("height","100px");
	   
	   <%--
	   var str ="저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은"+
	   			" 이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다." +
			   "건전한 토론문화와 양질의 댓글 문화를 위해, " +  
			   "타인에게 불쾌감을 주는 욕설 또는 특정 계층/민족," +
			   "종교 등을 비하하는 단어들은 표시가 제한됩니다.";
	   
	   str = str.replace(/(?:\r\n|\r|\n)/g,'<br/>');
	   --%>
	   var str = "댓글 입력";
	   
	   $("[name=comment]").val(str);
	   <%--$("[name=comment]").css("align","center");--%>
	   $("[name=comment]").focus(function(){
		   $(this).val("")
	   })
	   <%--
	   $(".cm #each_commentary").each(function(index,ele){
		   $(ele).css("padding","5px 0 5px");
	   });--%>
	   $(".cm #each_commentary").css("padding","5px 0 5px");
	   $(".cm #id").css("padding","2px 0 2px 0");
	   $(".cm #date").css("font-size","10px");
	   $(".cm #date").css("padding","0 0 5px 0");
	   
	   
	   
	   $(".cm #sub_container").css("margin-left","50px");
	   $(".sub_frm").css("margin-top","10px");
	   
	   <%--
	   $(".sub_container #id").css("padding","2px 0 2px 0");
	   $(".sub_container #date").css("font-size","10px");
	   $(".sub_container #date").css("padding","0 0 5px 0"); */
	   alert($(".third form").attr('id'));
	   --%>
	   
	   
	   <%-- 답글 클릭 시 sub_frm 보이기,숨기기 --%>
	   $(".show_subfrm").on("click",function(){
				//var index = $(this).closest(".fourth").find("form").attr('id');
				var index = $(this).closest(".fourth").find("form").eq(0).attr('id');
				//var secondClosestId = $(this).parents().find("form").eq(1).attr("id");
				//var cntArray = $("input:checked + span + span + span");
				
				//alert(index);
			 if($("#"+index).css("display") == 'block'){
				$("#"+index).hide();
		   } else
			  	$("#"+index).show();
	   });
	  
	   <%--var ckInsert = "${param.comment}";
	  var ckInsert = "${fn:replace(param.comment,replaceChar,'<br/>')}";
	  var ckInsert = "${fn:replace(param.comment,replaceChar,'\\n')}";
	  
	  var ckInsert = "${param.comment}";
	 
		if( ckInsert != ""){
				alert("등록 완료");
				location.href='${path}/com.do';
		}
		 --%>	
	  	<%-- 등록 --%>
	     $("#insBtn").on("click",function(){			 
	    	 if($("[name=comment]").val() ==""){
	    		 alert("댓글 내용 입력!!");
	    		 $("[name=comment]").focus();
	    		 return false;
	    	 }
	    	 alert("등록 완료");
	    	 $("[name=proc]").val("ins");
	    	 $("#frm").submit();	// form의 모든 데이터 전송 처리
	     });
	     $(".sub_insBtn").on("click",function(){
			   //var index = $(this).closest(".fourth").find("button").attr('id');
			   var index = $(this).closest(".fourth").find("form").eq(0).attr('id');
			   if($("[name=comment]").val() ==""){
		    		 alert("댓글 내용 입력!!");
		    		 $("[name=comment]").focus();
		    		 return false;
		    	 }
			   alert("등록 완료");
			  $("[name=proc]").val("ins");
			  $("#"+index).submit();	// form의 모든 데이터 전송 처리
		 });
	     
	     <%-- 삭제 --%>
	     $(".delBtn").css("margin-left", "10px");
	     $(".delBtn").on("click",function(){
	    	 // comment_id 만 보내면됨, comment_type = 1 고정
	    	 var cnt = ($(this).val());
	    	 var msg = confirm("댓글을 삭제하시겠습니까?");
	    	 if(msg){
		    	 $("[name=proc]").val("del");
		    	 $("[name=cnt]").val(cnt);
		    	 $("#del_frm").submit();
	    	 }
	    	 else {
	    		 return false;
	    	 }
	     });
	     $(".sub_delBtn").on("click",function(){
	    	 // comment_id 만 보내면됨, comment_type = 1 고정
	    	 var cnt = ($(this).val());
	    	 var msg = confirm("댓글을 삭제하시겠습니까?");
	    	 if(msg){
		    	 $("[name=proc]").val("del");
		    	 $("[name=cnt]").val(cnt);
		    	 $("#subdel_frm").submit();
	    	 }
	    	 else {
	    		 return false;
	    	 }
	     });
	     
	     <%-- 좋아요 --%>
	     $(".likeBtn").on("click",function(){
	    	 // comment_id 만 보내면됨, comment_type = 1 고정
	    	 var cnt = ($(this).val().split(','));
	    	 <%--var id = '${id}';--%>
	    	 var id = '${member.id}';
	    	 var id2 = cnt[1];	//wid
	    	 alert("좋아요(댓글 클릭)");
	    	 if(id == id2){
	    		 alert("자신이 작성한 글에는 좋아요를 누를 수 없습니다!!");
	    		 return false;
	    	 } else{
		    	 $("[name=proc]").val("like");
		    	 $("[name=cnt]").val(cnt[0]);
		    	 $("[name=wid]").val(cnt[1]);
		    	 $("[name=type]").val("1");
		    	 $("#like_frm").submit();
	    	 }
	     });
	     $(".sub_likeBtn").on("click",function(){
	    	 // comment_id 만 보내면됨, comment_type = 1 고정
	    	 var cnt = ($(this).val().split(','));
	    	 <%--var id = '${id}';--%>
	    	 var id = '${member.id}';
	    	 var id2 = cnt[1];
	    	 alert("좋아요(대댓글 클릭)");
	    	 if(id == id2){
	    		 alert("자신이 작성한 글에는 좋아요를 누를 수 없습니다!!");
	    		 return false;
	    	 } else{
		    	 $("[name=proc]").val("like");
		    	 $("[name=cnt]").val(cnt[0]);
		    	 $("[name=wid]").val(cnt[1]);
		    	 $("[name=type]").val("1");
		    	 $("#sublike_frm").submit();
	    	 }
	     });
	     
	     
	     <%-- 글자수 --%>
	     $("#test").on("keyup",function(){
	    	$("#test_cnt").html("("+$(this).val().length+" / 200)");
	    	
	    	if($(this).val().length > 200){
	    		$(this).val($(this).val().substring(0,200));
	    		$("#test_cnt").html("(200/200)");
	    	}
	     });
	     
	     $(".test").on("keyup",function(){
	    	 var area = $(this).closest(".fifth").find("textarea").eq(0).attr('id');
	    	 var div = $(this).closest(".fifth").find("div").eq(0).attr('id');
		    	$("#"+div).html("("+$("#"+area).val().length+" / 200)");
		    	
		    	if($("#"+area).val().length > 200){
		    		$("#"+area).val($("#"+area).val().substring(0,200));
		    		$("#"+div).html("(200/200)");
		    	}
		     });
	     
	     $(".btlist").css("margin","5px");
	     $("#home").on("click",function(){
	    	 location.href="${path}/mainLeft.do";
	     })
	      $("#list").on("click",function(){
	    	 location.href="${path}/boardList.do?category="+'${cate}';
	     })
        $("#detail").on("click",function(){
	    	 location.href="${path}/boardDetail.do?num="+'${num}'+"&category="+'${cate}';
	     })
   });
</script>
</head>
<%

//	String id = request.getParameter("id");
	String table_idS = request.getParameter("table_id"); 
		if(table_idS == null) table_idS = "0";
		int table_id = Integer.parseInt(table_idS);
	String text_idS = request.getParameter("text_id"); 
		if(text_idS == null) text_idS = "0";
		int text_id = Integer.parseInt(text_idS);
		
//	if(id != null)
		session.getAttribute("id");
	if(table_id != 0)
		session.setAttribute("table_id",table_id);
	if(text_id != 0)
		session.setAttribute("text_id",text_id);
%>
<div class="container">
 <div class="second">
 <%--
  <div>	
  	<table>
  		<tr><th>제목</th><td>${content.title}</td></tr>
  		<tr><th>작성자</th><td>${content.guest_id}</td></tr>
  		<tr><th colspan="2">작성 내용</th></tr>
  		<tr><td colspan="2" style="text-align:left;">${content.content}</td></tr>
  	</table>
  </div>
   --%>
  <nav>
  	<form id="frm" method="post">
       <%--<input type="text" name="comment" value="" > --%><%-- placeholder="댓글 입력">--%>
       <input type="hidden" name="proc" value=""/>
       <%--<input type="hidden" name="id" value="${id}"/>
       <input type="hidden" name="tnum" value="${table_id}"/>
       <input type="hidden" name="wnum" value="${text_id}"/> --%>
       <input type="hidden" name="comment_type" value="1"/>
       <textarea cols="8" rows="8" name="comment" id="test" wrap="hard"></textarea>
       <div id="test_cnt">(0/200)</div>
       <button type="submit" id="insBtn">등록</button>
     </form>
  </nav>
  <%-- <h3>접속 아이디 : ${id}</h3> --%>
  <h4>접속 아이디: ${member.id}</h4>
  <h4>DB 댓글 총 갯수 : ${comList.size()}</h4>
  <h4>현재 게시글의 댓글 갯수 : ${getComTotalCnt}</h4>
  <div class="third" id="third">
  	<ul class="ul">
  	
  		<c:forEach var="c" items="${comList}" varStatus="vs">
  		<c:if test="${table_id == c.table_id && text_id == c.text_id}">
  			<c:if test="${c.comment_type == 1}">
  			<div class="fourth" id="fourth">
  			<article class="cm" style="text-align:left;">
  				<div id="each_commentary">
	  				<div id="id" >${c.guest_id}</div>
	  				<%--<div>${c.comment_id}</div> --%>
	  				<div id="date">
	  				<fmt:formatDate type="both" pattern="yyyy/MM/dd HH:mm:ss" value="${c.written_date}" />
	  				</div>
	  				<div id="content">${fn:replace(c.content,replaceChar,"<br/>")}</div>
	  			</div>
	  			<nav style="margin-bottom: 5px;">
		  		 <input type="button" id="show_subfrm" class="show_subfrm" value="답글" style="float:left;"/>
		  		 <c:if test="${member.id == c.guest_id}">
		  		 
		  		 <%--
		  		 	<input type="button" class="delBtn" value="${c.cnt}">
		  		  --%>
		  		 	<button type="button" class="delBtn" value="${c.cnt}">삭제</button>
		  		 	
		  		 </c:if>
		  		 
		  		 <span style="float:right; margin-left:10px;">${c.likes}</span>
		  		 <button type="button" class="likeBtn" value="${c.cnt},${c.guest_id}" style="float:right;" >좋아요</button>
		  		 
		  		 
		  		 
			  	</nav>
	  		</article>
	  		<br>
	  			<c:forEach var="sub" items="${comList}" varStatus="subvs">
	  			<c:if test="${table_id == sub.table_id && text_id == sub.text_id}">
		  			<c:if test="${c.comment_id == sub.comment_id  && sub.comment_type == 2}">
		  				<article class="cm" style="text-align:left;">
		  				<div id="sub_container" class="sub_container">
		  					<div id="id" >${sub.guest_id}</div>
		  					<%--<div>${c.comment_id}</div> --%>
			  				<div id="date">
			  				<fmt:formatDate type="both" pattern="yyyy/MM/dd HH:mm:ss" value="${sub.written_date}" />
			  				</div>
			  				<div id="content">
			  				${fn:replace(sub.content,replaceChar,"<br/>")}
			  				</div>
			  				<c:if test="${member.id == sub.guest_id}">
			  				
			  				<%--
					  			<input type="button" id="sub_delBtn${vs.index}_${subvs_index}" 
					  			class="sub_delBtn" value="${sub.cnt}">
					  		 --%>
								<button type="button" class="sub_delBtn" value="${sub.cnt}">삭제</button>
								
					  		</c:if>
					  		
					  		<span style="float:right; margin-left:10px;">${sub.likes}</span>
					  		<button type="button" class="sub_likeBtn" 
					  		value="${sub.cnt},${sub.guest_id}" 
					  		style="float:right;">좋아요</button>
					  		
					  		
		  				</div>
			  			</article>
			  			<br>
		  			</c:if>
		  		</c:if>
	  			</c:forEach>
	  			<div class="fifth">
	  				<form id="sub_frm${vs.index}" class="sub_frm" method="post" style="display:none;">
				       <input type="hidden" name="proc" value=""/>
				       <input type="hidden" name="comment_id" value="${c.comment_id}" />
      				   <input type="hidden" name="comment_type" value="2"/>
       				   <textarea cols="8" rows="8" name="comment" id="test${vs.index}" class="test"></textarea>
       				   <div id="test_cnt${vs.index}" class="test_cnt">(0/200)</div>
				       <button type="submit" id="sub_insBtn${vs.index}" class="sub_insBtn">등록</button>
				     </form>
				     <form id="del_frm" class="del_frm" method="post" style="display:none;">
		  		 		<input type="hidden" name="proc" value=""/>
		  		 		<input type="hidden" name="cnt" value="" />
		  		 	</form>
		  		 	<form id="subdel_frm" class="subdel_frm" method="post" style="display:none;">
		  		 		<input type="hidden" name="proc" value=""/>
		  		 		<input type="hidden" name="cnt" value="" />
		  		 	</form>
		  		 	<form id="like_frm" class="like_frm" method="post" style="display:none;">
		  		 		<input type="hidden" name="proc" value=""/>
		  		 		<input type="hidden" name="type" value="" />
		  		 		<input type="hidden" name="wid" value="" />
		  		 		<input type="hidden" name="cnt" value="" />
		  		 	</form>
		  		 	<form id="sublike_frm" class="sublike_frm" method="post" style="display:none;">
		  		 		<input type="hidden" name="proc" value=""/>
		  		 		<input type="hidden" name="type" value="" />
		  		 		<input type="hidden" name="wid" value="" />
		  		 		<input type="hidden" name="cnt" value="" />
		  		 	</form>
				</div>
				</div>
	  			</c:if>
	  	</c:if>
  		</c:forEach>
  	</ul>
  </div>
  <div class="btlist">
  <%--
  	<a href="${path}/mainLeft.do" class="btn_main">홈</a>
  	<a href="${path}/mainLeft.do" class="btn_main">게시판</a>
  	<a href="${path}/mainLeft.do" class="btn_main">이전글</a>
  --%>
  	<button id="home">홈</button>
  	<button id="list">리스트</button>
  	<button id="detail">이전페이지</button>
  </div>
<%--
	1. 테이블 id, 글 id 에 해당하는 댓글만 보여주게 설정
	2. 글자수 제한
	3. 띄어쓰기, 줄바꿈

  <table>
    <thead>
      <tr>
        <th>내용</th><th>작성시간</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="c" items="${comList}">
      	
      <tr>
        <td>${c.comment_content}</td>
        <td><fmt:formatDate type="both" value="${c.written_date}" /></td>
      </tr>
      </c:forEach>
    </tbody>
  </table>    
 --%>
 </div>
</div>
</body>
</html>