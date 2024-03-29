<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import="jspexp.a03_database.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="UTF-8" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/bootstrap.min.css" >
<link rel="stylesheet" href="${path}/a00_com/jquery-ui.css" >
<style>
    #contentForm {
      width: 40%;
      margin: 0 auto;
      padding-top: 12%;
    }
 
    .table > thead > tr > th, .table > tbody > tr > th {
      background-color: #e6ecff;
      text-align: center;
    }
</style>
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script type="text/javascript">

<%--
 
 
--%>
//
   $(document).ready(function(){
		$("#btnEdit").on("click",function(){
			$(location).attr("href","${path}/writeDelete.do");
		});
   });
</script>
</head>
<body>
<h1>상세화면(${param.num })</h1>
<div class="container">
<form name="form1" method="post">
	<div class="row">
	<table class="table table-striped" style=" border: 1px solid #dddddd">
		<tr>
			<td width="20%" style="background-color: #eeeeee; text-align: center;">날짜</td>
			<td width="30%" align="center">${dto.reg_date }</td>
			<td width="20%" style="background-color: #eeeeee; text-align: center;">조회수</td>
			<td width="30%" align="center">${dto.readcount }</td>
		</tr>
		<tr>
			<td style="background-color: #eeeeee; text-align: center;">아이디</td>
			<td>${dto.id }</td>
			<td style="background-color: #eeeeee; text-align: center;">제목</td>
			<td>${dto.title }</td>
			<td style="background-color: #eeeeee; text-align: center;">본문</td>
			<td>${dto.content }</td>
		</tr>
		<tr>
			<td style="background-color: #eeeeee; text-align: center;">첨부파일</td>
			<td colspan="3">
				<c:if test="${dto.filesize>0 }">
				<a href="${path }/board_servlet/download.do?num=${dto.num}">
					${dto.filename }
					(${dto.filesize } bytes)
				</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="num" value="${dto.num }">
				<input type="button" value="수정/삭제" id="btnEdit">
				<input type="button" value="답변" id="btnReply">
				<input type="button" value="목록" id="btnList">
			</td>
		</tr>
	</table>
	</div>
	</form>
</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>