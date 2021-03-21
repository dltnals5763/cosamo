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
<style type="text/css">
.container { float:left; width:830px; margin:10px 10px 0; box-sizing:border-box;}
</style>
<script type="text/javascript"
	src="${path}/a00_com/jquery-3.5.1.js"></script>
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script type="text/javascript">
<%--
 
 
--%>
//
   $(document).ready(function(){
      $(".data").on("click",function(){
    	  var num = $(this).children().eq(0).text();
    	  location.href="${path}/noticeDeatil.do?num="+num;
      })
      var category = document.getElementById("");
   });
</script>
</head>

<body>
	<!-- 게시판 메인 페이지 영역 시작 -->
	<jsp:include page="/main.jsp"></jsp:include>
	<div class="container">
		<div class="row">
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
					<c:forEach var="dto" items="${nlist }">
						<tr class="data">
							<td>${dto.num }</td>	
							<td>공지사항</td>
							<td>${dto.title }</td>
							<td>${dto.id }</td>
							<td>${dto.reg_date }</td>
							<td>${dto.readcount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 글쓰기 버튼 생성 -->
			<c:if test="${id eq 'admin' }">
			<a href="writeNotice.do" class="btn btn-primary pull-right">글쓰기</a>
			</c:if>
		</div>
	</div>
	<!-- 게시판 메인 페이지 영역 끝 -->
	
	<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>