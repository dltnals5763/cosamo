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
      $("#btnList").on("click",function(){
    	  location.href="${path}/board_servlet/list.do";
      });
      $("#btnUpdate").on("click",function(){
    	  document.form1.action = "${path}/board_servlet/update.do";
    	  document.form1.submit();
      });
      $("#btnDelete").on("click",function(){
    	  if(confirm("삭제하시겠습니까?")){
    		  document.form1.action = "${path}/board_servlet/delete.do";
    		  document.form1.submit();
    	  }
      });
   });
</script>
</head>
<body>
<h1>상세화면</h1>
<form name="form1" method="post" enctype="multipart/form-date">
<table border="1" width="700px">
	<tr>
		<td width="10%" align="center">날짜</td>
		<td width="10%" align="center">${dto.reg_date }</td>
		<td width="10%" align="center">조회수</td>
		<td width="10%" align="center">${dto.readcount }</td>
	</tr>
	<tr>
		<td align="center">이름</td>
		<td colspan="3"><input name="writer" id="writer" value="${dto.writer }"></td>
	</tr>
	<tr>
		<td align="center">제목</td>
		<td colspan="3"><input name="title" id="title" value="${dto.title }"></td>
	</tr>
	<tr>
		<td align="center">본문</td>
		<td colspan="3"><input name="content" id="content" value="${dto.content }"></td>
	</tr>
	<tr>
		<td align="center">첨부파일</td>
		<td colspan="3">
			<c:if test="${dto.filesize>0 }">
				${dto.filename }
				(${dto.filesize } bytes)
				<input type="checkbox" name="fileDel">첨부파일 삭제
			</c:if>
			<input type="file" name="file1">
		</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="num" value="${dto.num }">
			<input type="button" value="수정" id="btnUpdate">
			<input type="button" value="삭제" id="btnDelete">
			<input type="button" value="목록" id="btnList">
		</td>
	</tr>
</table>
</form>
</body>
</html>



