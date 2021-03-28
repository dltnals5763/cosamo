<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import="jspexp.a03_database.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script type="text/javascript"
	src="${path}/a00_com/jquery-3.5.1.js"></script>
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script type="text/javascript">
<%--
 
 
--%>

   $(document).ready(function(){
      $(".data").on("click",function(){
    	  var num = $(this).children().eq(0).text();
    	  var cate = $(this).children().eq(1).text();
    	  $("[name=num]").val(num);
    	  $("[name=category]").val(cate);
    	  $("#com").submit();
      })
   });
</script>
</head>

<body>
<jsp:include page="../../main.jsp" flush="false"/>
<div id="mainlist">
	<!-- 게시판 메인 페이지 영역 시작 -->
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
					<c:forEach var="dto" items="${dlist }" varStatus="status">
					<c:if test="${dto.category==param.category}">
						<tr class="data">
							<td>${dto.num }</td>
							<td>${dto.category }</td>
							<td id="title">${dto.title }</td>
							<td>${dto.id }</td>
							<td>${dto.reg_date }</td>
							<td>${dto.readcount }</td>
						</tr>
					</c:if>	
					</c:forEach>
				</tbody>
			</table>
			<form class="form-inline" method="post" id="com" style="display:none"
				action="${path}/boardDetail.do">
				<input type="hidden" name="num" value=""/>
				<input type="hidden" name="category" value=""/>
			</form>
			<!-- 글쓰기 버튼 생성 -->
			<a href="write.do" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>
	<!-- 게시판 메인 페이지 영역 끝 -->
	
	<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</div>
</body>
</html>