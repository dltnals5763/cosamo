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
<script type="text/javascript">
<%--
 
 
--%>

   $(document).ready(function(){
		$(".category").change(function(){
			var catText = $("option:selected").text();				
				$("#catSelect").val(catText);
				ajaxFun();			
		});
		
	 var xhr = new XMLHttpRequest();
	 function ajaxFun(){
	    var catVal = $("[name=category]").val();
	
	    var qstr = "category="+catVal;
	    if(catVal=="전체") catVal = "";
	    xhr.open("post","list_data.jsp?",true);
	    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
	    xhr.onreadystatechange = function(){
	       if(xhr.readyState == 4 && xhr.status==200){
	          // z09_empList.jsp에서 전송해오는 결과 html 내용을
	          // 현재 화면의 div에 출력 처리한다.
	      	 $(".list").html(xhr.responseText);
	      	 var listData = JSON.parse(xhr.responseText);
	         var show = "<h4>"+listData.tot+"</h2>";
				show+="<table class='table table-striped'><tr><th>번호</th><th>카테고리</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>"
	      		 $(listData.categoryList).each(function(idx, listData){
	      			show+="<tr>";
	      			show+="<td>"+listData.num+"</td>";
	      			show+="<td>"+listData.category+"</td>";
	      			show+="<td>"+listData.title+"</td>";
	      			show+="<td>"+listData.writer+"</td>";
	      			show+="<td>"+listData.reg_date+"</td>";
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
	<!-- 게시판 메인 페이지 영역 시작 -->
	<div class="container">
		<div class="row">
			<div class="category">
			<select name="category" id="catSelect">
				<option>전체</option>
				<option>JAVA</option>
				<option>HTML/CSS</option>
				<option>JavaScript</option>
				<option>JQUERY</option>
			</select>
			</div>
			<div class="list">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">카테고리</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						<th style="background-color: #eeeeee; text-align: center;">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${dlist }">
						<tr>
							<td>${dto.num }</td>	
							<td>${dto.category }</td>
							<td>${dto.title }</td>
							<td>${dto.writer }</td>
							<td>${dto.reg_date }</td>
							<td>${dto.readcount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
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