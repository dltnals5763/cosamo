<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import="jspexp.a03_database.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>  
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="UTF-8" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/bootstrap.min.css" >
<link rel="stylesheet" href="${path}/a00_com/jquery-ui.css" >
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<style>

#wrap_category {  float:left; padding:10px;} 
.category { margin-bottom:10px; }
.uppercase { text-transform:uppercase!important; }

</style>
<script type="text/javascript">

<%--
 
 
--%>
$(document).ready(function(){
	 var xhr = new XMLHttpRequest();	
	 ajaxFun();	
		$(".category").change(function(){
				ajaxFun();			
		});

	 function ajaxFun(){
	    var catVal = $("[name=category]").val();	
	    var qstr = "category="+catVal;

	    xhr.open("post","list_data.jsp?",true);
	    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
	    xhr.onreadystatechange = function(){
	       if(xhr.readyState == 4 && xhr.status==200){
	      	 $(".list").html(xhr.responseText);
	      	 var listData = JSON.parse(xhr.responseText);
	       //  var show = "<h4>"+listData.tot+"</h4>";\
	       		var show = "";
				show+="<table id='post-all-tab' class='table table-striped'><tr><th>번호</th><th>카테고리</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>"
	      		 $(listData.categoryList).each(function(idx, listData){
	      			show+="<tr>";
	      			show+="<td>"+(idx+1)+"</td>";
	      			show+="<td class='uppercase'>"+listData.category+"</td>";
	      			show+="<td>"+listData.title+"</td>";
	      			show+="<td>"+listData.id+"</td>";
	      			show+="<td>"+listData.re
	      			date+"</td>";
	      			show+="<td>"+listData.readcount+"</td>";
	      			show+="</tr>";
	      		});
				show+="</table>"
				$(".list").html(show);
	       }
	    };
	    xhr.send(qstr);
	 }
   });
</script>
</head>

<body>
<jsp:include page="/main.jsp" />

	<!-- 게시판 메인 페이지 영역 시작 -->
	<div id="wrap_category">
		<div class="row">
			<div class="category">
			<select name="category" id="catSelect">
				<option value="">전체</option>
				<option value="notice">공지사항</option>
				<option value="free">자유게시판</option>
				<option value="JAVA">JAVA</option>
				<option value="HTML/CSS">HTML/CSS</option>
				<option value="JavaScript">JavaScript</option>
				<option value="JQUERY">JQUERY</option>
			</select>
			</div>
			<div class="list">
		
			</div>
			
			<!-- 글쓰기 버튼 생성 -->
			<a href="write.do" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>
	<!-- 게시판 메인 페이지 영역 끝 -->
	
	<!-- 부트스트랩 참조 영역 -->

	<script src="js/bootstrap.js"></script>
</body>
</html>