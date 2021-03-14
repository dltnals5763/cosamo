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
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script type="text/javascript">
<%--
 
 
--%>
//
   $(document).ready(function(){
      
   });
</script>
</head>
<body>
<h1>상세화면</h1>
<form name="form1" method="post">
<table border="1" width="700px">
	<tr>
		<td width="10%" align="center">날짜</td>
		<td width="10%" align="center">${dto.reg_date }</td>
		<td width="10%" align="center">조회수</td>
		<td width="10%" align="center">${dto.readcount }</td>
	</tr>
	<tr>
		<td align="center">이름</td>
		<td colspan="3">${dto.writer }</td>
		<td align="center">제목</td>
		<td colspan="3">${dto.title }</td>
		<td align="center">본문</td>
		<td colspan="3">${dto.content }</td>
	</tr>
	<tr>
		<td align="center">첨부파일</td>
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
</form>
</body>
</html>